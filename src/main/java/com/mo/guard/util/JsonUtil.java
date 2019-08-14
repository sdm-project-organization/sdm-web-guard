package com.mo.guard.util;


import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * getConfig
     *
     * @param strJson
     * */
    public static <T> T getJsonObject(Class<T> clazz, String strJson) throws IOException {
        JavaType type = objectMapper.getTypeFactory().constructType(clazz);
        T listOfResult = objectMapper.readValue(strJson, type);
        return listOfResult;
    }
}