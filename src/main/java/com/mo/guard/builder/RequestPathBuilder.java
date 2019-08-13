package com.mo.guard.builder;

import com.mo.guard.util.RequestUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class RequestPathBuilder {

    /**
     * build
     *
     * @param parentMap
     * @param httpMethod
     * @param httpPath
     * @param role
     */
    public void build(Map<String, Object> parentMap,
                      String httpMethod,
                      String httpPath,
                      String role) {
        if (httpPath.charAt(0) == '/')
            httpPath = httpPath.substring(1);
        String[] partOfPath = httpPath.split("/");

        Map<String, Object> resultMap = parentMap;
        for (String part : partOfPath) {
            if(RequestUtil.validPathVariable(part))
                part = RequestUtil.PATH_VARIABLE;
            resultMap = findNextMap(resultMap, part);
        }
        addRole(resultMap, httpMethod, role);
    }

    /**
     * findNextMap
     *
     * @param parentMap
     * @param partOfPath
     */
    public Map<String, Object> findNextMap(Map<String, Object> parentMap,
                                           String partOfPath) {
        Map<String, Object> currentMap = (Map) parentMap.get(partOfPath);
        if (currentMap == null) {
            currentMap = new HashMap<>();
            parentMap.put(partOfPath, currentMap);
        }
        return currentMap;
    }

    /**
     * addRole
     *
     * @param resultMap
     * @param httpMethod
     * @param role
     */
    public void addRole(Map<String, Object> resultMap,
                        String httpMethod,
                        String role) {
        List<String> roleList = (List<String>) resultMap.get(httpMethod);
        if (roleList == null) {
            roleList = new LinkedList<>();
            resultMap.put(httpMethod, roleList);
        }
        roleList.add(role);
    }

}
