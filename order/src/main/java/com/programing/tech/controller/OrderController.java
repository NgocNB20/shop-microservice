package com.programing.tech.controller;

import com.programing.tech.client.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final ProductClient productClient;
    @GetMapping
    public Mono<String> getOrder() {
     return productClient.getProduct();
    }
}
