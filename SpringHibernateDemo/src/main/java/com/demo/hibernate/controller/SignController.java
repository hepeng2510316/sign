package com.demo.hibernate.controller;

import com.demo.hibernate.beans.SignRecordResult;
import com.demo.hibernate.entities.Comment;
import com.demo.hibernate.entities.Like;
import com.demo.hibernate.entities.SignRecord;
import com.demo.hibernate.entities.Tips;
import com.demo.hibernate.exceptions.LikeException;
import com.demo.hibernate.exceptions.PrivateException;
import com.demo.hibernate.exceptions.SignException;
import com.demo.hibernate.service.SignService;
import com.demo.hibernate.service.TipsService;
import com.demo.hibernate.utils.ResUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SignController {

    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/sign")
    public Map sign(@Valid @RequestBody SignRecord signRecord, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new PrivateException(result.getAllErrors().get(0).getDefaultMessage());
        }
        signService.sign(signRecord, signRecord.getOpenId());
        return ResUtils.success("签到成功！");
    }

    @PostMapping("/getSignRecordList")
    public Map getSignRecordList(@RequestParam(value = "openId") String openId) {
        List<SignRecordResult> signRecordList = signService.getSignRecordList(openId);
        return ResUtils.success(signRecordList);

    }

    @PostMapping("/addLike")
    public Map addLike(@Valid @RequestBody Like like, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new PrivateException(result.getAllErrors().get(0).getDefaultMessage());
        }
        signService.addLike(like);
        return ResUtils.success();

    }

    @PostMapping("/addComment")
    public Map addComment(@Valid @RequestBody Comment comment, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            throw new PrivateException(result.getAllErrors().get(0).getDefaultMessage());
        }
        signService.addComment(comment);
        return ResUtils.success();
    }

}
