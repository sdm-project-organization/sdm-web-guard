package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.Role;
import com.mo.guard.service.RoleServiceImpl;
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
@RequestMapping("roles")
public class RoleController {

    @Autowired
    RoleServiceImpl roleService;

    // [GET] /roles
    @RequestMapping(method = RequestMethod.GET)
    /*public List<RoleResource> getRoles() throws Exception {*/
    public List<Role> getRoles() throws Exception {
        return roleService.findAllByEnableFlag(EnableFlag.Y.getValue());
    }

    // [POST] /roles
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createRole(
            /*@Validated @RequestBody RoleResource roleResource,*/
            @Validated @RequestBody Role roleResource,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {
        /*Role role = roleService.save(roleResource.toInsert().toEntity());*/
        Role role = roleService.save(roleResource);

        URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(RoleController.class).getRole(role.getSequence()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    // [GET] /roles/{roleId}
    @RequestMapping(path = "/{roleId}", method = RequestMethod.GET)
    /*public RoleResource getRole(@PathVariable int roleId) throws Exception {*/
    public Role getRole(@PathVariable int roleId) throws Exception {
        /*return roleService.findBySequence(roleId);*/
        return roleService.findBySequenceAndEnableFlag(roleId, EnableFlag.Y.getValue());
    }

    // [PUT] /roles/{roleId}
    @RequestMapping(path = "/{roleId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRole(
            @PathVariable int roleId,
            /*@Validated @RequestBody RoleResource roleResource) throws Exception {*/
            @Validated @RequestBody Role roleResource) throws Exception {
        /*roleService.updateBySequence(roleId, roleResource.toUpdate().toEntity());*/
        roleService.updateBySequence(roleId, roleResource);
        return null;
    }

    // [DELETE] /roles/{roleId}
    @RequestMapping(path = "/{roleId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRole(@PathVariable int roleId) {
        roleService.unenable(roleId);
        return null;
    }

}