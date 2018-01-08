package com.demo.hibernate.service.impl;

import com.demo.hibernate.dao.TipsDao;
import com.demo.hibernate.entities.Tips;
import com.demo.hibernate.service.TipsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipServiceImpl implements TipsService {

    private final TipsDao tipsDao;

    @Autowired
    public TipServiceImpl(TipsDao tipsDao) {
        this.tipsDao = tipsDao;
    }

    @Override
    public Tips getRandomTips() {
        List list = tipsDao.findAll();
        int i = (int) (Math.random() * list.size());
        return (Tips) list.get(i);
    }
}
