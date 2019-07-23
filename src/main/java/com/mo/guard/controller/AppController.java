package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.App;
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
    public List<App> getApps() throws Exception {
        return appService.findAllByEnableFlag(EnableFlag.Y.getValue());
    }

    // [POST] /apps
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createApp(
            /*@Validated @RequestBody AppResource appResource,*/
            @Validated @RequestBody App appResource,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {
        /*App app = appService.save(appResource.toInsert().toEntity());*/
        App app = appService.save(appResource);

        URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(AppController.class).getApp(app.getSequence()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    // [GET] /apps/{appId}
    @RequestMapping(path = "/{appId}", method = RequestMethod.GET)
    /*public AppResource getApp(@PathVariable int appId) throws Exception {*/
    public App getApp(@PathVariable int appId) throws Exception {
        return appService.findBySequence(appId);
    }

    // [PUT] /apps/{appId}
    @RequestMapping(path = "/{appId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateApp(
            @PathVariable int appId,
            /*@Validated @RequestBody AppResource appResource) throws Exception {*/
            @Validated @RequestBody App appResource) throws Exception {
        /*appService.updateBySequence(appId, appResource.toUpdate().toEntity());*/
        appService.updateBySequence(appId, appResource);
        return null;
    }

    // [DELETE] /apps/{appId}
    @RequestMapping(path = "/{appId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteApp(@PathVariable int appId) {
        appService.unenable(appId);
        return null;
    }

}