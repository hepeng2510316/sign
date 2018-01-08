package com.demo.hibernate.dao;

import com.demo.hibernate.entities.Like;

import java.util.List;


public interface LikeDao {

    Like find(String openId, Integer recordId);
    void save(Like like);

    List<Like> findList(Integer recordId);
}

