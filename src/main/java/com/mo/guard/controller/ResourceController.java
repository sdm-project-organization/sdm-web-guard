package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.Resource;
import com.mo.guard.service.ResourceServiceImpl;
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
@RequestMapping("resources")
public class ResourceController {

    @Autowired
    ResourceServiceImpl resourceService;

    // [GET] /resources
    @RequestMapping(method = RequestMethod.GET)
    /*public List<ResourceResource> getResources() throws Exception {*/
    public List<Resource> getResources() throws Exception {
        return resourceService.findAllByEnableFlag(EnableFlag.Y.getValue());
    }

    // [POST] /resources
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createResource(
            /*@Validated @RequestBody ResourceResource resourceResource,*/
            @Validated @RequestBody Resource resourceResource,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {
        /*Resource resource = resourceService.save(resourceResource.toInsert().toEntity());*/
        Resource resource = resourceService.save(resourceResource);

        URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(ResourceController.class).getResource(resource.getSequence()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    // [GET] /resources/{resourceId}
    @RequestMapping(path = "/{resourceId}", method = RequestMethod.GET)
    /*public ResourceResource getResource(@PathVariable int resourceId) throws Exception {*/
    public Resource getResource(@PathVariable int resourceId) throws Exception {
        return resourceService.findBySequence(resourceId);
    }

    // [PUT] /resources/{resourceId}
    @RequestMapping(path = "/{resourceId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateResource(
            @PathVariable int resourceId,
            /*@Validated @RequestBody ResourceResource resourceResource) throws Exception {*/
            @Validated @RequestBody Resource resourceResource) throws Exception {
        /*resourceService.updateBySequence(resourceId, resourceResource.toUpdate().toEntity());*/
        resourceService.updateBySequence(resourceId, resourceResource);
        return null;
    }

    // [DELETE] /resources/{resourceId}
    @RequestMapping(path = "/{resourceId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteResource(@PathVariable int resourceId) {
        resourceService.unenable(resourceId);
        return null;
    }

}