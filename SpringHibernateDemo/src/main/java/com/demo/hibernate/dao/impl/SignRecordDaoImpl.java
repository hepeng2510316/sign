package com.demo.hibernate.dao.impl;

import com.demo.hibernate.dao.SignRecordDao;
import com.demo.hibernate.dao.TipsDao;
import com.demo.hibernate.entities.SignRecord;
import com.demo.hibernate.entities.Tips;
import com.demo.hibernate.utils.DateUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SignRecordDaoImpl implements SignRecordDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public SignRecordDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public long findTodaySignRecord(String openId) {
        String hql = "select count(*)  from SignRecord s WHERE s.openId =? and s.signTime>?";
        Query query = getSession().createQuery(hql);
        query.setString(0, openId);
        query.setDate(1, DateUtils.getToday());
        return (long) query.uniqueResult();
    }

    @Override
    public long findYesterdaySignRecord(String openId) {
        String hql = "select count(*)  from SignRecord s WHERE s.openId =? and s.signTime>=? and s.signTime < ?";
        Query query = getSession().createQuery(hql);
        query.setString(0, openId);
        query.setDate(1, DateUtils.getYesterday());
        query.setDate(2, DateUtils.getToday());
        return (long) query.uniqueResult();
    }

    @Override
    public void save(SignRecord signRecord) {
        getSession().save(signRecord);
    }

    @Override
    public List<SignRecord> findAll() {
        String hql = "from SignRecord";
        Query query = getSession().createQuery(hql);
        query.setMaxResults(500);
        return query.list();
    }
}
