package com.programing.tech.config;


import com.programing.tech.client.ProductClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;



@Configuration
public class AppConfig {

    @Value("${product.url}")
    private String productsUrl;

    @Bean
    WebClient webClient(){
        return WebClient.builder()
                .baseUrl(productsUrl)
                .build();
    }

    @Bean
    ProductClient productClient(WebClient webClient){
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient)).build();

        return httpServiceProxyFactory.createClient(ProductClient.class);
    }



//    @Bean
//    ProductClient productClient() {
//        RestClient restClient = RestClient.builder().baseUrl(productsUrl).build();
//        var restClientAdapter = RestClientAdapter.create(restClient);
//        var httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(restClientAdapter).build();
//        return  httpServiceProxyFactory.createClient(ProductClient.class);
//    }

}
