package com.demo.hibernate.dao;

import com.demo.hibernate.entities.SignRecord;

import java.util.List;

public interface SignRecordDao {

    long findTodaySignRecord(String openId);

    long findYesterdaySignRecord(String openId);

    void save(SignRecord signRecord);

    List<SignRecord> findAll();
}
