package com.mo.guard.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Doc {

    public String application;
    public Map<String, DocResource> resources;
    public Map<String, List<String>> auths;
    public Map<String, List<String>> roles;

    @Data
    public static class DocResource {
        public String method;
        public String path;
    }

}