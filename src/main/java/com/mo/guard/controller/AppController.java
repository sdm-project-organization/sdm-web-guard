package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.AppEntity;
import com.mo.guard.service.AppServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("apps")
public class AppController {

    @Autowired
    AppServiceImpl appService;

    // [GET] /apps
    @RequestMapping(method = RequestMethod.GET)
    /*public List<AppResource> getApps() throws Exception {*/
    public List<AppEntity> getApps() throws Exception {
        return appService.findAllByEnableFlag(EnableFlag.YES);
    }

    // [POST] /apps
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createApp(
            /*@Validated @RequestBody AppResource appResource,*/
            @Validated @RequestBody AppEntity appEntity,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {
        /*AppEntity app = appService.save(appResource.toInsert().toEntity());*/
        AppEntity app = appService.save(appEntity);

        URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(AppController.class).getApp(app.getSequence()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    // [GET] /apps/{appId}
    @RequestMapping(path = "/{appId}", method = RequestMethod.GET)
    /*public AppResource getApp(@PathVariable int appId) throws Exception {*/
    public AppEntity getApp(@PathVariable int appId) throws Exception {
        /*AppEntity app = appService.findBySequence(appId);*/
        AppEntity app = appService.findBySequenceAndEnableFlag(appId, EnableFlag.YES);
        return app;
    }

    // [PUT] /apps/{appId}
    @RequestMapping(path = "/{appId}", method = RequestMethod.PUT)
    public AppEntity updateApp(
            @PathVariable int appId,
            /*@Validated @RequestBody AppResource appResource) throws Exception {*/
            @Validated @RequestBody AppEntity appEntity) throws Exception {
        /*appService.updateBySequence(appId, appResource.toUpdate().toEntity());*/
        return appService.updateBySequence(appId, appEntity);
    }

    // [DELETE] /apps/{appId}
    @RequestMapping(path = "/{appId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteApp(@PathVariable int appId) {
        appService.unenable(appId);
        return null;
    }

}