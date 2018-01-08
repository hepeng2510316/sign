package com.demo.hibernate.controller;

import com.demo.hibernate.entities.Tips;
import com.demo.hibernate.service.TipsService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TipsController {

    private final TipsService tipsService;

    @Autowired
    public TipsController(TipsService tipsService) {
        this.tipsService = tipsService;
    }

    @RequestMapping(value = "/getTips", method = RequestMethod.GET)
    public JsonObject getTips() {

        Tips tips = tipsService.getRandomTips();
        if (tips != null) {

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("code", 1000);
            jsonObject.addProperty("data", tips.getContent());

            return jsonObject;
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("code", 1001);

        return jsonObject;

    }
}
