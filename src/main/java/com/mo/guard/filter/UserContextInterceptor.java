package com.mo.guard.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * RestTemplate 인스턴스에서 실행되는 모든 HTTP 기반 서비스 발신 요청에 상관관계 ID를 삽입
 * 서비스 호출 간 연결을 형성하는데 수행
 *
 * */
public class UserContextInterceptor implements ClientHttpRequestInterceptor {

    /**
     * intercept - HTTP 헤더에 인증토큰을 추가 ( 실제 HTTP 호출을 진행하기 전에 다음 메서드 실행 )
     *
     * */
    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        HttpHeaders headers = request.getHeaders();

        // 서비스 호출을 위해 준비할 HTTP 요청 헤더를 가져와 `UserContext` 에 저장된 상관관계 ID를 추가
        headers.add(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        headers.add(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
        return execution.execute(request, body);
    }
}