package com.demo.hibernate.controller;

import com.demo.hibernate.entities.Account;
import com.demo.hibernate.exceptions.PrivateException;
import com.demo.hibernate.service.AccountService;
import com.demo.hibernate.utils.ResUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/addUser")
    public Map addUser(@Valid @RequestBody Account account, BindingResult result) throws PrivateException {
        //参数检查
        if (result.hasErrors()) {
            throw new PrivateException(result.getAllErrors().get(0).getDefaultMessage());
        }
        accountService.addUser(account);
        return ResUtils.success();
    }
}
