package pl.edu.pg.student.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class GatewayApplication {

    @Autowired
    private Environment environment;
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("producers", r -> r
                        .host(environment.getProperty("server.host").concat(":").concat(environment.getProperty("server.port")))
                        .and()
                        .path("/api/producers/{name}", "/api/producers")
                        .uri("http://".concat(environment.getProperty("gateway.producers"))))
                .route("cars", r -> r
                        .host(environment.getProperty("server.host").concat(":").concat(environment.getProperty("server.port")))
                        .and()
                        .path("/api/cars", "/api/cars/**", "/api/producers/cars", "/api/producers/{name}/cars", "/api/producers/{name}/cars/**")
                        .uri("http://".concat(environment.getProperty("gateway.cars"))))
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
