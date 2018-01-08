package com.demo.hibernate.dao.impl;

import com.demo.hibernate.dao.LikeDao;
import com.demo.hibernate.entities.Like;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeDaoImpl implements LikeDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public LikeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public Like find(String openId, Integer recordId) {
        String hql = "from Like l WHERE l.openId =? and l.recordId =?";
        Query query = getSession().createQuery(hql);
        return (Like) query.setString(0, openId).setInteger(1, recordId).uniqueResult();
    }

    @Override
    public void save(Like like) {
        getSession().save(like);
    }

    @Override
    public List<Like> findList(Integer recordId) {
        String hql = "from Like l WHERE l.recordId =?";
        Query query = getSession().createQuery(hql);
        return query.setInteger(0, recordId).list();
    }
}
