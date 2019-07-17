package com.mo.guard.securitys;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 메서드로 전달된 HttpSecurity 객체로 모든 접근 규칙이 구성
        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        // 특정역할을 이용한 서비스 보호
        /*http.authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/v1/organizations/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated();*/
    }
}