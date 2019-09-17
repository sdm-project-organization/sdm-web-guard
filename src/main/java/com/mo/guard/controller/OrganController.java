package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.OrganEntity;
import com.mo.guard.service.OrganServiceImpl;
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
@RequestMapping("organs")
public class OrganController {

    @Autowired
    OrganServiceImpl organService;

    // [GET] /organs
    @RequestMapping(method = RequestMethod.GET)
    /*public List<OrganResource> getOrgans() throws Exception {*/
    public List<OrganEntity> getOrgans() throws Exception {
        return organService.findAllByEnableFlag(EnableFlag.YES);
    }

    // [POST] /organs
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createOrgan(
            /*@Validated @RequestBody OrganResource organResource,*/
            @Validated @RequestBody OrganEntity organResource,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {
        /*OrganEntity organ = organService.save(organResource.toInsert().toEntity());*/
        OrganEntity organ = organService.save(organResource);

        URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(OrganController.class).getOrgan(organ.getSequence()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    // [GET] /organs/{organId}
    @RequestMapping(path = "/{organId}", method = RequestMethod.GET)
    /*public OrganResource getOrgan(@PathVariable int organId) throws Exception {*/
    public OrganEntity getOrgan(@PathVariable int organId) throws Exception {
        /*OrganEntity organ = organService.findBySequence(organId);*/
        OrganEntity organ = organService.findBySequenceAndEnableFlag(organId, EnableFlag.YES);
        return organ;
    }

    // [PUT] /organs/{organId}
    @RequestMapping(path = "/{organId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateOrgan(
            @PathVariable int organId,
            /*@Validated @RequestBody OrganResource organResource) throws Exception {*/
            @Validated @RequestBody OrganEntity organResource) throws Exception {
        /*organService.updateBySequence(organId, organResource.toUpdate().toEntity());*/
        organService.updateBySequence(organId, organResource);
        return null;
    }

    // [DELETE] /organs/{organId}
    @RequestMapping(path = "/{organId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteOrgan(@PathVariable int organId) {
        organService.unenable(organId);
        return null;
    }

}