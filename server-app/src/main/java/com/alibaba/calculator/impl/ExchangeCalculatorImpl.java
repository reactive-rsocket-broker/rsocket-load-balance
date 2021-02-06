package com.alibaba.calculator.impl;

import com.alibaba.calculator.ExchangeCalculatorService;
import com.alibaba.calculator.ExchangeRequest;
import com.alibaba.calculator.annotations.RSocketHandler;
import com.alibaba.calculator.annotations.SpringRSocketService;
import reactor.core.publisher.Mono;

@SpringRSocketService(ExchangeCalculatorService.RSOCKET_SERVICE_NAME)
public class ExchangeCalculatorImpl implements ExchangeCalculatorService {
    @Override
    @RSocketHandler("exchange")
    public Mono<Double> exchange(ExchangeRequest request) {
        if (request.getSource().equals("USD") && request.getTarget().equals("CNY")) {
            return Mono.just(request.getAmount() * 6.4);
        }
        return Mono.just(0.0);
    }
}
