package com.wpg.springboot001.moudles.test.controller;

import com.wpg.springboot001.vo.Result;
import com.wpg.springboot001.moudles.test.pojo.Card;
import com.wpg.springboot001.moudles.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CardController {
    @Autowired
    private CardService cardService;

    /**
     * 192.168.18.232/api/card
     * {"cardNo":"rtyuiopfghjfgh"}
     */
    @PostMapping(value = "/card",consumes = "application/json")
    public Result<Card> insertCard(@RequestBody Card card){
        return cardService.insertCard(card);
    }
}
