package com.programing.tech.rotes;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import java.net.URI;
import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;


@Configuration
public class Routes {
//    @Bean
//    public RouterFunction<ServerResponse> productServiceRouter() {
//        return route("product_service")
//                .route(RequestPredicates.path("/product"), HandlerFunctions.http("http://localhost:8084"))
//                .filter(circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
//        return route("product_service_swagger")
//                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8084"))
//                .filter(setPath("/api-docs"))
//                .filter(circuitBreaker("productServiceCircuitBreakerSwagger", URI.create("forward:/fallbackRoute")))
//                .build();
//    }


        // Create a bean for restTemplate to call services
        @Bean
        @LoadBalanced        // Load balance between service instances running at different ports.
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }





//
//
//
//
//    @Bean
//    public RouterFunction<ServerResponse> OrderServiceRouterSwagger() {
//        return route("order_service")
//                .route(RequestPredicates.path("/api/v1/order"), HandlerFunctions.http("http://localhost:8082"))
//                .filter(circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute"))).build();
//    }
//
//
//    @Bean
//    public RouterFunction<ServerResponse> InventoryServiceRouter() {
//        return route("inventory_service")
//                .route(RequestPredicates.path("/api/v1/inventory"), HandlerFunctions.http("http://localhost:8083"))
//                .filter(circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
//                .build();
//    }
//
//
//
//
//
//    @Bean
//    public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
//        return route("order_service_swagger")
//                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
//                .filter(setPath("/api-docs"))
//                .filter(circuitBreaker("orderServiceCircuitBreakerSwagger", URI.create("forward:/fallbackRoute")))
//                .build();
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
//        return route("inventory_service_swagger")
//                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8083"))
//                .filter(setPath("/api-docs"))
//                .filter(circuitBreaker("inventoryServiceCircuitBreakerSwagger", URI.create("forward:/fallbackRoute")))
//                .build();
//    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute() {
        return route("fallbackRoute")
                .GET("/fallbackRoute", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable, please try again later"))
                .build();
    }
}
