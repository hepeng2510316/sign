package com.demo.hibernate.dao.impl;

import com.demo.hibernate.dao.TipsDao;
import com.demo.hibernate.entities.Tips;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipsDaoImpl implements TipsDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public TipsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Tips> findAll() {
        Query query = getSession().createQuery("from Tips");
        return (List<Tips>)query.list();
    }
}
