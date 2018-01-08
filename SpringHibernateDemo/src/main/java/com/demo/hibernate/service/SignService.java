package com.demo.hibernate.service;

import com.demo.hibernate.beans.SignRecordResult;
import com.demo.hibernate.entities.SignRecord;

import java.util.List;

public interface SignService {

    void sign(SignRecord sign, String openId);

    List<SignRecordResult> getSignRecordList(String openId);

    void addLike(String openId, Integer recordId);

    void addComment(String openId, String info, Integer recordId);

}
