package pl.edu.pg.student.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayApplication {

    private final DiscoveryClient discoveryClient;

    @Autowired
    public GatewayApplication(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        ServiceInstance car = discoveryClient.getInstances("car").stream()
                .findFirst()
                .orElseThrow();

        ServiceInstance producer = discoveryClient.getInstances("producer").stream()
                .findFirst()
                .orElseThrow();

        return builder
                .routes()
                .route("producers", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/producers/{name}", "/api/producers")
                        .uri(producer.getUri()))
                .route("cars", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/cars", "/api/cars/**", "/api/producers/cars", "/api/producers/{name}/cars", "/api/producers/{name}/cars/**")
                        .uri(car.getUri()))
                .build();
    }

    @Bean
    public CorsWebFilter corsWebFilter() {

        final CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Collections.singletonList("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
        corsConfig.addAllowedHeader("*");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
