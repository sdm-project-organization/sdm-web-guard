package com.mo.guard.config;

import com.mo.guard.model.Doc;
import com.mo.guard.service.DocService;
import com.mo.guard.util.FileUtil;
import com.mo.guard.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Configuration
@Profile(value = "default")
public class AuthInjectionConfig {

    @Autowired
    DocService docService;

    /*@PostConstruct
    public void injectGuardAuth() throws RuntimeException, IOException {
        File file = FileUtil.loadResource("specification/guard.json");
        String specification = FileUtil.readFile(file);
        Doc doc = JsonUtil.getJsonObject(Doc.class, specification);
        docService.uploadDoc(doc);
    }*/


}
