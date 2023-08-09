package com.bergeskar.currencyexchange.controller;

import com.bergeskar.currencyexchange.VO.ExchangeVO;
import com.bergeskar.currencyexchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/{totalPrice}")
    public ExchangeVO getTotalPriceInEUR(@PathVariable("totalPrice") double totalPrice){
        return exchangeService.callExchangeAPI(totalPrice);
    }
}