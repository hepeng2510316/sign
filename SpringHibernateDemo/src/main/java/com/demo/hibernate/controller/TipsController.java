package com.demo.hibernate.controller;

import com.demo.hibernate.entities.Tips;
import com.demo.hibernate.exceptions.PrivateException;
import com.demo.hibernate.service.TipsService;
import com.demo.hibernate.utils.ResUtils;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TipsController {

    private final TipsService tipsService;

    @Autowired
    public TipsController(TipsService tipsService) {
        this.tipsService = tipsService;
    }

    @GetMapping(value = "/getTips")
    public Map getTips() throws Exception {
        Tips tips = tipsService.getRandomTips();
        if (tips != null) {
            return ResUtils.success(tips);
        }
        throw new PrivateException("获取tips失败");
    }
}
