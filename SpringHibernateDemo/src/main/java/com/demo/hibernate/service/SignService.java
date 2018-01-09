package com.demo.hibernate.service;

import com.demo.hibernate.beans.SignRecordResult;
import com.demo.hibernate.entities.Comment;
import com.demo.hibernate.entities.Like;
import com.demo.hibernate.entities.SignRecord;

import java.util.List;

public interface SignService {

    void sign(SignRecord sign, String openId);

    List<SignRecordResult> getSignRecordList(String openId);

    void addLike(Like like);

    void addComment(Comment comment);

}
