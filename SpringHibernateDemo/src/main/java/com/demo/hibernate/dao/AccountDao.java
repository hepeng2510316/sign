package com.demo.hibernate.dao;


import com.demo.hibernate.entities.Account;

public interface AccountDao {

   Account findAccount(String openId);

   long countOne(String openId);

   void saveAccount(Account account);

   void updateAccount(Account account);
}
