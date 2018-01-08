package com.demo.hibernate.dao.impl;

import com.demo.hibernate.dao.CommentDao;
import com.demo.hibernate.dao.LikeDao;
import com.demo.hibernate.entities.Comment;
import com.demo.hibernate.entities.Like;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public CommentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void save(Comment comment) {
        getSession().save(comment);
    }

    @Override
    public List<Comment> findList(Integer recordId) {
        String hql = "from Comment c WHERE c.recordId =?";
        Query query = getSession().createQuery(hql);
        return query.setInteger(0, recordId).list();
    }


}
