package com.programing.tech.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;


public interface ProductClient {
    Logger log = LoggerFactory.getLogger(ProductClient.class);

    @GetExchange (value = "product")
    Mono<String> getProduct();

}
