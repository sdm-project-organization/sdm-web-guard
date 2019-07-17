package com.mo.guard;

import com.mo.guard.utils.UserContext;
import com.mo.guard.utils.UserContextFilter;
import com.mo.guard.utils.UserContextInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import java.util.Collections;
import java.util.List;

/**
 * 스프링클라우드와 스프링시큐리티에 해당서비스가 보호자원이라 알림
 * 서비스로 유입되는 모든 호출을 가로채 HTTP 해더에 OAuth2 Access Token이 있는지 확인한 후 토큰 유효성을 확인위하여
 * `security.oauth2.resource.userInfoUri`에 정의된 콜백 URL로 다시 호출
 * */
@SpringBootApplication
//@EnableEurekaClient
//@EnableCircuitBreaker
@EnableResourceServer
public class GuardApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GuardApplication.class, args);
    }

    @Bean
    public Filter userContextFilter() {
        UserContextFilter userContextFilter = new UserContextFilter();
        return userContextFilter;
    }

    /*@Bean
    @Primary
    public RestTemplate getCustomRestTemplate() {
        // new UserContextInterceptor() = Authorization 헤더를 모든 REST 호출에 삽입
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }
        return template;
    }*/

}