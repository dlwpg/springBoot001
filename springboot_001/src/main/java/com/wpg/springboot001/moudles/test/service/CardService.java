package com.wpg.springboot001.moudles.test.service;

import com.wpg.springboot001.moudles.common.vo.Result;
import com.wpg.springboot001.moudles.test.pojo.Card;

public interface CardService {
    //插入一条数据
    Result<Card>  insertCard(Card card);
}
