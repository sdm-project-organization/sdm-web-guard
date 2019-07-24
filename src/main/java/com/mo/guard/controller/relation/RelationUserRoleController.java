package com.mo.guard.controller.relation;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.relation.RelationUserRole;
import com.mo.guard.service.relation.RelationUserRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("relation/user/role")
public class RelationUserRoleController {

    @Autowired
    RelationUserRoleServiceImpl relationUserRoleService;

    // [GET] /relation/user/role
    @RequestMapping(method = RequestMethod.GET)
    public List<RelationUserRole> getRelationUserRoles() throws Exception {
        return relationUserRoleService.findAllByEnableFlag(EnableFlag.Y.getValue());
    }

    // [POST] /relation/user/role
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createRelationUserRole(
            @Validated @RequestBody RelationUserRole relationUserRoleResource,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {
        RelationUserRole relationUserRole = relationUserRoleService.save(relationUserRoleResource);

        /*URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(RelationUserRoleController.class).getRelationUserRole(relationUserRole.getKey().toString()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();*/
        return null;
    }

    // [GET] /relation/user/role/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public RelationUserRole getRelationUserRole(@PathVariable String id) throws Exception {
        return relationUserRoleService.findByPkAndEnableFlag(id, EnableFlag.Y.getValue());
    }

    // [PUT] /relation/user/role/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRelationUserRole(
            @PathVariable int id,
            @Validated @RequestBody RelationUserRole relationUserRole) throws Exception {
        relationUserRoleService.updateBySequence(id, relationUserRole);
        return null;
    }

    // [DEL] /relation/user/role/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRelationUserRole(@PathVariable int id) {
        relationUserRoleService.unenable(id);
        return null;
    }

}