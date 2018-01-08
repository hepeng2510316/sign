package com.demo.hibernate.dao.impl;

import com.demo.hibernate.dao.AccountDao;
import com.demo.hibernate.dao.TipsDao;
import com.demo.hibernate.entities.Account;
import com.demo.hibernate.entities.Tips;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public AccountDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public Account findAccount(String openId) {
        String hql = "from Account a WHERE a.openId =?";
        Query query = getSession().createQuery(hql);
        return (Account) query.setString(0, openId).uniqueResult();
    }

    @Override
    public long countOne(String openId) {
        String hql = "select count(*)  from Account a WHERE a.openId =?";
        Query query = getSession().createQuery(hql);
        return (long) query.setString(0, openId).uniqueResult();
    }

    @Override
    public void saveAccount(Account account) {
        getSession().save(account);
    }

    @Override
    public void updateAccount(Account account) {
        getSession().update(account);
    }
}
