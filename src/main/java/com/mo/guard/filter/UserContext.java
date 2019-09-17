package com.mo.guard.filter;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserContext {

    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN     = "tmx-auth-token";
    public static final String USER_ID        = "tmx-user-id";
    public static final String ORG_ID         = "tmx-org-id";

    public String correlationId= new String();
    public String authToken= new String();
    public String username = new String();
    public String orgId = new String();

}