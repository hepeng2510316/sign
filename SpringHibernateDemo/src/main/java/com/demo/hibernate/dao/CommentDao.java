package com.demo.hibernate.dao;

import com.demo.hibernate.entities.Comment;
import com.demo.hibernate.entities.Like;

import java.util.List;


public interface CommentDao {

    void save(Comment comment);

    List<Comment> findList(Integer recordId);
}
