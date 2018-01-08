package com.demo.hibernate.service.impl;

import com.demo.hibernate.beans.SignRecordResult;
import com.demo.hibernate.dao.AccountDao;
import com.demo.hibernate.dao.CommentDao;
import com.demo.hibernate.dao.LikeDao;
import com.demo.hibernate.dao.SignRecordDao;
import com.demo.hibernate.entities.Account;
import com.demo.hibernate.entities.Comment;
import com.demo.hibernate.entities.Like;
import com.demo.hibernate.entities.SignRecord;
import com.demo.hibernate.exceptions.LikeException;
import com.demo.hibernate.exceptions.SignException;
import com.demo.hibernate.service.SignService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SignServiceImpl implements SignService {

    @Resource
    private SignRecordDao signRecordDao;

    @Resource
    private AccountDao accountDao;

    @Resource
    private LikeDao likeDao;

    @Resource
    private CommentDao commentDao;

    public SignServiceImpl() {

    }


    @Override
    public void sign(SignRecord signRecord, String openId) {
        //1.是否签到
        long count = signRecordDao.findTodaySignRecord(openId);
        if (count > 0) {
            throw new SignException("用户今日已签到!");
        }
        //2.可以签到
        signRecordDao.save(signRecord);
        //3.更新用户的连续签到次数和累计签到次数
        Account account = accountDao.findAccount(openId);
        account.setTotalSignCount(account.getTotalSignCount() + 1);
        //4.该用户昨日是否签到
        long yesterdayCount = signRecordDao.findYesterdaySignRecord(openId);
        if (yesterdayCount > 0) {
            account.setContinueSignCount(account.getContinueSignCount() + 1);
        } else {
            account.setContinueSignCount(1);
        }
        accountDao.updateAccount(account);
    }

    @Override
    public List<SignRecordResult> getSignRecordList(String openId) {

        List<SignRecordResult> list = new ArrayList<>();

        //1.查所有签到记录
        List<SignRecord> all = signRecordDao.findAll();

        for (SignRecord signRecord : all) {
            SignRecordResult signRecordResult = new SignRecordResult();

            //2.查找用户属性
            Account account = accountDao.findAccount(signRecord.getOpenId());
            //3.查找签到记录点赞数，及该记录当前用户是否点赞
            List<Like> likes = likeDao.findList(signRecord.getId());
            int likeCount = likes.size();
            boolean isMineLike = false;
            for (Like l : likes) {
                if (l.getOpenId().equals(openId)) {
                    //当前用户是点赞
                    isMineLike = true;
                    break;
                }
            }
            //查找签到记录评论列表
            List<Comment> comments = commentDao.findList(signRecord.getId());

            List<SignRecordResult.Comment> commentList = new ArrayList<>();

            //评论添加用户昵称
            for (Comment comment : comments) {
                Account commentAccount = accountDao.findAccount(signRecord.getOpenId());
                SignRecordResult.Comment comment1 = new SignRecordResult.Comment();
                comment1.setCommentInfo(comment.getCommentInfo());
                comment1.setId(comment.getId());
                comment1.setNickName(commentAccount.getNickName());
                comment1.setRecordId(comment.getRecordId());
                comment1.setOpenId(comment.getOpenId());
                commentList.add(comment1);
            }

            signRecordResult.setId(signRecord.getId());
            signRecordResult.setAvatarUrl(account.getAvatarUrl());
            signRecordResult.setNickName(account.getNickName());
            signRecordResult.setOpenId(account.getOpenId());
            signRecordResult.setSignTime(signRecord.getSignTime());
            signRecordResult.setSingInfo(signRecord.getSingInfo());
            signRecordResult.setTotalSignCount(account.getTotalSignCount());
            signRecordResult.setLikeCount(likeCount);
            signRecordResult.setMineLike(isMineLike);
            signRecordResult.setList(commentList);
            list.add(signRecordResult);
        }


        return list;
    }


    @Override
    public void addLike(String openId, Integer recordId) {
        //1.是否点赞
        Like like = likeDao.find(openId, recordId);
        if (like != null) {
            throw new LikeException("您已经点过赞");
        }
        likeDao.save(new Like(openId, recordId));
    }

    @Override
    public void addComment(String openId, String info, Integer recordId) {

        commentDao.save(new Comment(recordId, openId, info));
    }
}
