package com.mo.guard.controller.relation;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.relation.RelationAuthResource;
import com.mo.guard.service.relation.RelationAuthResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("relation/auth/resource")
public class RelationAuthResourceController {

    @Autowired
    RelationAuthResourceServiceImpl relationAuthResourceService;

    // [GET] /relation/auth/resource
    @RequestMapping(method = RequestMethod.GET)
    public List<RelationAuthResource> getRelationAuthResources() throws Exception {
        return relationAuthResourceService.findAllByEnableFlag(EnableFlag.YES);
    }

    // [POST] /relation/auth/resource
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createRelationAuthResource(
            @Validated @RequestBody RelationAuthResource relationAuthResourceResource,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {
        RelationAuthResource relationAuthResource = relationAuthResourceService.save(relationAuthResourceResource);

        /*URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(RelationAuthResourceController.class).getRelationAuthResource(relationAuthResource.getKey().toString()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();*/
        return null;
    }

    // [GET] /relation/auth/resource/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public RelationAuthResource getRelationAuthResource(@PathVariable String id) throws Exception {
        return relationAuthResourceService.findByPkAndEnableFlag(id, EnableFlag.YES);
    }

    // [PUT] /relation/auth/resource/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRelationAuthResource(
            @PathVariable int id,
            @Validated @RequestBody RelationAuthResource relationAuthResource) throws Exception {
        relationAuthResourceService.updateBySequence(id, relationAuthResource);
        return null;
    }

    // [DEL] /relation/auth/resource/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRelationAuthResource(@PathVariable int id) {
        relationAuthResourceService.unenable(id);
        return null;
    }

}