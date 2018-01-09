package com.demo.hibernate.service.impl;

import com.demo.hibernate.entities.Account;
import com.demo.hibernate.exceptions.AccountException;
import com.demo.hibernate.repository.AccountRepository;
import com.demo.hibernate.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountRepository accountRepository;

    @Override
    public void addUser(Account account) {
        //判断用户是否存在
        Account user = accountRepository.findByOpenId(account.getOpenId());
        if (user!=null) {
            throw  new AccountException("用户已存在!");
        }
        accountRepository.save(account);
    }

}
