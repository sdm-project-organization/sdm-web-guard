package com.mo.guard.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

//public class UserContextInterceptor implements ClientHttpRequestInterceptor {
//
//    /**
//     * intercept - HTTP 헤더에 인증토큰을 추가
//     *
//     * */
//    @Override
//    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
//        HttpHeaders headers = request.getHeaders();
//        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
//        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
//        return execution.execute(request, body);
//    }
//}