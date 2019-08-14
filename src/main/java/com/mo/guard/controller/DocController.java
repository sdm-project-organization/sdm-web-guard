package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.Doc;
import com.mo.guard.model.entity.ResourceEntity;
import com.mo.guard.service.DocService;
import com.mo.guard.service.ResourceServiceImpl;
import com.mo.guard.util.FileUtil;
import com.mo.guard.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("docs")
public class DocController {

    @Autowired
    ResourceServiceImpl resourceService;

    @Autowired
    DocService docService;

    // [GET] /docs/download/resource
    @RequestMapping(path = "/download", method = RequestMethod.GET)
    public List<ResourceEntity> downloadResource()
            throws Exception {
        return resourceService.findAllByEnableFlag(EnableFlag.Y.getValue());
    }

    // [GET] /docs/upload
    @RequestMapping(path = "/upload", method = RequestMethod.GET)
    public ResponseEntity<Void> uploadResource(Map<String, Object> resources)
            throws Exception {
        File file = FileUtil.loadResource("static/specification/guard.json");
        String specification = FileUtil.readFile(file);
        Doc doc = JsonUtil.getJsonObject(Doc.class, specification);
        docService.uploadDoc(doc);
        return null;
    }

}