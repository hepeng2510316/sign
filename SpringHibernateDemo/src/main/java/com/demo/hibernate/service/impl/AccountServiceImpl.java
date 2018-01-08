package com.demo.hibernate.service.impl;

import com.demo.hibernate.dao.AccountDao;
import com.demo.hibernate.entities.Account;
import com.demo.hibernate.exceptions.AccountException;
import com.demo.hibernate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    @Autowired
    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
    public void addUser(Account account) {
        //判断用户是否存在
        long count = accountDao.countOne(account.getOpenId());
        if (count > 0) {
            throw  new AccountException("用户已存在!");
        }
        accountDao.saveAccount(account);
    }

}
