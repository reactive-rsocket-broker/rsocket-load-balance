package com.alibaba.calculator.impl;

import com.alibaba.calculator.MathCalculatorService;
import com.alibaba.calculator.annotations.RSocketHandler;
import com.alibaba.calculator.annotations.SpringRSocketService;
import reactor.core.publisher.Mono;

@SpringRSocketService(MathCalculatorService.RSOCKET_SERVICE_NAME)
public class MathCalculatorImpl implements MathCalculatorService {

    @RSocketHandler("square")
    public Mono<Integer> square(Integer input) {
        System.out.println("received: " + input);
        return Mono.just(input * input);
    }
}
