package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.Resource;
import com.mo.guard.service.ResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("docs")
public class DocController {

    @Autowired
    ResourceServiceImpl resourceService;

    // [GET] /docs/download/resource
    @RequestMapping(path = "/download/resource", method = RequestMethod.GET)
    public List<Resource> downloadResource() throws Exception {
        return resourceService.findAllByEnableFlag(EnableFlag.Y.getValue());
    }

    // [GET] /docs/upload/resource
    @RequestMapping(path = "/upload/resource", method = RequestMethod.GET)
    public ResponseEntity<Void> uploadResource(
            List<Resource> resources
    ) throws Exception {
        return null;
    }

}
