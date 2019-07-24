package com.mo.guard.controller.relation;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.relation.RelationRoleAuth;
import com.mo.guard.service.relation.RelationRoleAuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("relation/role/auth")
public class RelationRoleAuthController {

    @Autowired
    RelationRoleAuthServiceImpl relationRoleAuthService;

    // [GET] /relation/role/auth
    @RequestMapping(method = RequestMethod.GET)
    public List<RelationRoleAuth> getRelationRoleAuths() throws Exception {
        return relationRoleAuthService.findAllByEnableFlag(EnableFlag.Y.getValue());
    }

    // [POST] /relation/role/auth
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createRelationRoleAuth(
            @Validated @RequestBody RelationRoleAuth relationRoleAuthResource,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {
        RelationRoleAuth relationRoleAuth = relationRoleAuthService.save(relationRoleAuthResource);

        /*URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(RelationRoleAuthController.class).getRelationRoleAuth(relationRoleAuth.getKey().toString()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();*/
        return null;
    }

    // [GET] /relation/role/auth/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public RelationRoleAuth getRelationRoleAuth(@PathVariable String id) throws Exception {
        return relationRoleAuthService.findByPkAndEnableFlag(id, EnableFlag.Y.getValue());
    }

    // [PUT] /relation/role/auth/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRelationRoleAuth(
            @PathVariable int id,
            @Validated @RequestBody RelationRoleAuth relationRoleAuth) throws Exception {
        relationRoleAuthService.updateBySequence(id, relationRoleAuth);
        return null;
    }

    // [DEL] /relation/role/auth/{id}
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteRelationRoleAuth(@PathVariable int id) {
        relationRoleAuthService.unenable(id);
        return null;
    }

}