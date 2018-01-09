package com.demo.hibernate.controller;

import com.demo.hibernate.beans.SignRecordResult;
import com.demo.hibernate.entities.Comment;
import com.demo.hibernate.entities.Like;
import com.demo.hibernate.entities.SignRecord;
import com.demo.hibernate.entities.Tips;
import com.demo.hibernate.exceptions.LikeException;
import com.demo.hibernate.exceptions.SignException;
import com.demo.hibernate.service.SignService;
import com.demo.hibernate.service.TipsService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SignController {

    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public JsonObject sign(@RequestBody SignRecord signRecord) {
        JsonObject jsonObject = new JsonObject();
        try {
            signService.sign(signRecord, signRecord.getOpenId());
            jsonObject.addProperty("code", 1000);
            jsonObject.addProperty("msg", "签到成功!");
        } catch (SignException e) {
            e.printStackTrace();
            jsonObject.addProperty("code", 1001);
            jsonObject.addProperty("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("code", 1001);
            jsonObject.addProperty("msg", "签到失败!");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/getSignRecordList", method = RequestMethod.POST)
    public JsonObject getSignRecordList(String openId) {
        JsonObject jsonObject = new JsonObject();
        try {
            List<SignRecordResult> signRecordList = signService.getSignRecordList(openId);
            jsonObject.addProperty("code", 1000);
            jsonObject.addProperty("msg", "成功");
            jsonObject.add("data", new Gson().toJsonTree(signRecordList));
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("code", 1001);
            jsonObject.addProperty("msg", "获取签到列表失败!");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/addLike", method = RequestMethod.POST)
    public JsonObject addLike(@RequestBody Like like) {
        JsonObject jsonObject = new JsonObject();
        try {
            signService.addLike(like);
            jsonObject.addProperty("code", 1000);
            jsonObject.addProperty("msg", "成功");
        } catch (LikeException e) {
            jsonObject.addProperty("code", 1001);
            jsonObject.addProperty("msg", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("code", 1001);
            jsonObject.addProperty("msg", "点赞失败!");
        }
        return jsonObject;
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.POST)
    public JsonObject addComment(@RequestBody Comment comment) {
        JsonObject jsonObject = new JsonObject();
        try {
            signService.addComment(comment);
            jsonObject.addProperty("code", 1000);
            jsonObject.addProperty("msg", "成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("code", 1001);
            jsonObject.addProperty("msg", "评论失败!");
        }
        return jsonObject;
    }

}
