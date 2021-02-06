package com.alibaba.calculator;

import reactor.core.publisher.Mono;

public interface MathCalculatorService {
    String RSOCKET_SERVICE_NAME = "com.alibaba.calculator.MathCalculatorService";

    Mono<Integer> square(Integer input);
}
