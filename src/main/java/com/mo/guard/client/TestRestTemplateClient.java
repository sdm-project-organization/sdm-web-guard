package com.mo.guard.client;

import com.mo.guard.controller.TestController;
import com.mo.guard.util.UserContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TestRestTemplateClient {
    private static final Logger logger = LoggerFactory.getLogger(TestRestTemplateClient.class);

    // OAuth2RestTemplate - 표준 `RestTemplate` 에 대한 드롭인 대체품으로 OAuth2 엑세스 토큰의 전파를 처리
    /*@Autowired
    OAuth2RestTemplate restTemplate;*/

    /*public TestController.Item getItem(String itemId) {
        // logger.info("In Guard Service.getItem : {}", UserContext.getCorrelationId());

        // 서비스호출은 표준 RestTemplate과 똑같은 방식으로 수행
        ResponseEntity<TestController.Item> restExchange = restTemplate.exchange(
                "http://zuulserver:5555/api/organization/v1/organizations/{organizationId}",
                HttpMethod.GET,
                null,
                TestController.Item.class,
                itemId);

        // 캐시의 데이터 저장
        return restExchange.getBody();
    }*/


}
