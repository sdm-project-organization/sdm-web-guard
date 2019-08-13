package com.mo.guard.util;

public class RequestUtil {

    public static final String PATH_VARIABLE = "{}";

    public static boolean validPathVariable(String partOfPath) {
        return partOfPath.charAt(0) == '{' && partOfPath.charAt(partOfPath.length() - 1) == '}';
    }
}
