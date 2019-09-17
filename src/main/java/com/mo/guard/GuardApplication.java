package com.mo.guard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableEurekaClient
//@EnableCircuitBreaker
//@EnableBinding(Source.class)
public class GuardApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GuardApplication.class, args);
    }

    // RestTemplate 객체가 리본을 사용할 것을 나타냄
    /*@LoadBalanced
    @Bean
    public RestTemplate getRestTemplate(){
        RestTemplate template = new RestTemplate();
        List interceptors = template.getInterceptors();
        // UserContextInterceptor를 생성된 RestTemplate 인스턴스에 추가
        if (interceptors == null) {
            template.setInterceptors(Collections.singletonList(new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }
        return template;
    }*/

    /*@Bean
    public Filter userContextFilter() {
        UserContextFilter userContextFilter = new UserContextFilter();
        return userContextFilter;
    }*/


    /*@Bean
    public OAuth2RestTemplate restTemplate() {
        return null;
    }*/

}