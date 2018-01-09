package com.demo.hibernate.repository;

import com.demo.hibernate.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LikeRepository extends JpaRepository<Like,Integer>{

    Like findByOpenIdAndRecordId(String openId, Integer recordId);

    List<Like> findListByRecordId(Integer recordId);
}

