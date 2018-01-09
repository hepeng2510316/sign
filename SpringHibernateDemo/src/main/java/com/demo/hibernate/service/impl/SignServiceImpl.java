package com.demo.hibernate.service.impl;

import com.demo.hibernate.beans.SignRecordResult;
import com.demo.hibernate.entities.Account;
import com.demo.hibernate.entities.Comment;
import com.demo.hibernate.entities.Like;
import com.demo.hibernate.entities.SignRecord;
import com.demo.hibernate.exceptions.LikeException;
import com.demo.hibernate.exceptions.SignException;
import com.demo.hibernate.repository.AccountRepository;
import com.demo.hibernate.repository.CommentRepository;
import com.demo.hibernate.repository.LikeRepository;
import com.demo.hibernate.repository.SignRecordRepository;
import com.demo.hibernate.service.SignService;
import com.demo.hibernate.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SignServiceImpl implements SignService {

    @Resource
    private SignRecordRepository signRecordRepository;

    @Resource
    private AccountRepository accountRepository;

    @Resource
    private LikeRepository likeRepository;

    @Resource
    private CommentRepository commentRepository;

    public SignServiceImpl() {

    }


    @Override
    public void sign(SignRecord signRecord, String openId) {
        //1.是否签到
        SignRecord sToday = signRecordRepository.findTodaySignRecord(openId, DateUtils.getToday());
        if (sToday != null) {
            throw new SignException("用户今日已签到!");
        }
        //2.可以签到
        signRecord.setSignTime(new Date());
        signRecordRepository.save(signRecord);
        //3.更新用户的连续签到次数和累计签到次数
        Account account = accountRepository.findByOpenId(openId);
        account.setTotalSignCount(account.getTotalSignCount() + 1);
        //4.该用户昨日是否签到
        SignRecord sYesterday = signRecordRepository.findYesterdaySignRecord(openId, DateUtils.getYesterday(), DateUtils.getToday());
        if (sYesterday != null) {
            account.setContinueSignCount(account.getContinueSignCount() + 1);
        } else {
            account.setContinueSignCount(1);
        }
        accountRepository.save(account);
    }

    @Override
    public List<SignRecordResult> getSignRecordList(String openId) {

        List<SignRecordResult> list = new ArrayList<>();

        //1.查所有签到记录(最大500条)
        Pageable pageable  = new PageRequest(0, 500);
        Page<SignRecord> all = signRecordRepository.findAll(pageable);

        for (SignRecord signRecord : all.getContent()) {
            SignRecordResult signRecordResult = new SignRecordResult();
            //2.查找用户属性
            Account account = accountRepository.findByOpenId(signRecord.getOpenId());
            //3.查找签到记录点赞数，及该记录当前用户是否点赞
            List<Like> likes = likeRepository.findListByRecordId(signRecord.getId());
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
            List<Comment> comments = commentRepository.findListByRecordId(signRecord.getId());

            List<SignRecordResult.Comment> commentList = new ArrayList<>();

            //评论添加用户昵称
            for (Comment comment : comments) {
                Account commentAccount = accountRepository.findByOpenId(signRecord.getOpenId());
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
    public void addLike(Like like) {
        //1.是否点赞
        Like like1 = likeRepository.findByOpenIdAndRecordId(like.getOpenId(), like.getRecordId());
        if (like1 != null) {
            throw new LikeException("您已经点过赞");
        }
        likeRepository.save(like);
    }

    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }
}
