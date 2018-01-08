package com.demo.hibernate.controller;

import com.demo.hibernate.entities.Account;
import com.demo.hibernate.entities.Tips;
import com.demo.hibernate.exceptions.AccountException;
import com.demo.hibernate.service.AccountService;
import com.demo.hibernate.service.TipsService;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public JsonObject addUser(@RequestBody Account account) {

        JsonObject jsonObject = new JsonObject();
        try {
            accountService.addUser(account);
            jsonObject.addProperty("code", 1000);
            jsonObject.addProperty("msg", "成功");
        } catch (AccountException e){
            jsonObject.addProperty("code", 1001);
            jsonObject.addProperty("msg", e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            jsonObject.addProperty("code", 1001);
        }
        return jsonObject;

    }
}
