package com.demo.hibernate.service.impl;

import com.demo.hibernate.entities.Tips;
import com.demo.hibernate.repository.TipsRepository;
import com.demo.hibernate.service.TipsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TipServiceImpl implements TipsService {

    @Resource
    private TipsRepository tipsRepository;

    @Override
    public Tips getRandomTips() {
        List<Tips> list = tipsRepository.findAll();
        if (list.isEmpty()) {
            return null;
        }
        int i = (int) (Math.random() * list.size());
        return list.get(i);
    }
}
