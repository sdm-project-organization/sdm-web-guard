//package com.mo.guard.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//// @Configuration
//// @EnableWebSecurity
//// @EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /*@Autowired
//    UserServiceImpl userService;*/
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
//
//    /**
//     * 필터처리
//     *
//     * */
//    /*@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); // redirect to `/login`
//
//        *//*http.authorizeRequests()
//                // login
//                .and()
//                .formLogin()
//                .usernameParameter("userId")
//                .passwordParameter("password")
//                .loginPage("/")
//                .loginProcessingUrl("/")
//                .defaultSuccessUrl("/")
//                .failureUrl("/")
//                // logout
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//                .invalidateHttpSession(true)
//                .logoutSuccessUrl("/")
//                .permitAll();*//*
//    }*/
//
//    /**
//     * 인증처리
//     * `UserDetailsService`를 사용해 인증처리를 하려면 `AuthenticationManagerBuilder`에 적용시켜야
//     *
//     * @param auth
//     * */
//    /*@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }*/
//
//
//}
