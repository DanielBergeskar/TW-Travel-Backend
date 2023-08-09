package com.bergeskar.currencyexchange.service;

import com.bergeskar.currencyexchange.VO.ExchangeVO;
import com.bergeskar.currencyexchange.model.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeVO exchangeVO;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public ExchangeVO callExchangeAPI(double totalPrice) {
        Exchange response = webClientBuilder.build()
                .get()
                .uri("https://api.apilayer.com/exchangerates_data/convert?to=EUR&from=SEK&amount=" + totalPrice)
                .header("apikey", "cML24MbiUgQjgeZ7aXHHFaw2Rg6D8Qa3")
                .retrieve()
                .bodyToMono(Exchange.class)
                .block();

        exchangeVO.setFrom(response.getQuery().getFrom());
        exchangeVO.setTo(response.getQuery().getTo());
        exchangeVO.setAmount(response.getQuery().getAmount());
        exchangeVO.setRate(response.getInfo().getRate());
        exchangeVO.setResult(Math.round(response.getResult() * 100.0) / 100.0);

        return exchangeVO;
    }
}