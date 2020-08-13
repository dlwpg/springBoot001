package com.wpg.springboot001.moudles.test.service.impl;

import com.wpg.springboot001.moudles.common.vo.Result;
import com.wpg.springboot001.moudles.test.hibernatedao.CardHibernatedao;
import com.wpg.springboot001.moudles.test.pojo.Card;
import com.wpg.springboot001.moudles.test.pojo.City;
import com.wpg.springboot001.moudles.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardHibernatedao cardHibernatedao;
    @Override
    @Transactional
    public Result<Card> insertCard(Card card) {
        cardHibernatedao.saveAndFlush(card);
        return new Result<Card>(Result.Resultstatus.SUCCESS.status,"insert success!",card);
    }
}
