package com.mo.guard.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;

//@Configuration
//public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//
//    /**
//     * configure - 모든 접근 규칙을 재정의한 메서드 안에 정의
//     *
//     * */
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                // 특정역할을 이용한 서비스 보호
//                .antMatchers(HttpMethod.DELETE, "/v1/organizations/**")
//                .hasRole("ADMIN")
//                // HttpSecurity 객체로 모든 접근 규칙이 구성
//                .anyRequest()
//                .authenticated();
//    }
//}