package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.table.Auth;
import com.mo.guard.service.AuthServiceImpl;
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
@RequestMapping("auths")
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    // [GET] /auths
    @RequestMapping(method = RequestMethod.GET)
    /*public List<AuthResource> getAuths() throws Exception {*/
    public List<Auth> getAuths() throws Exception {
        return authService.findAllByEnableFlag(EnableFlag.Y.getValue());
    }

    // [POST] /auths
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createAuth(
            /*@Validated @RequestBody AuthResource authResource,*/
            @Validated @RequestBody Auth authResource,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {
        /*Auth auth = authService.save(authResource.toInsert().toEntity());*/
        Auth auth = authService.save(authResource);

        URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(AuthController.class).getAuth(auth.getSequence()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    // [GET] /auths/{authId}
    @RequestMapping(path = "/{authId}", method = RequestMethod.GET)
    /*public AuthResource getAuth(@PathVariable int authId) throws Exception {*/
    public Auth getAuth(@PathVariable int authId) throws Exception {
        /*return authService.findBySequence(authId);*/
        return authService.findBySequenceAndEnableFlag(authId, EnableFlag.Y.getValue());
    }

    // [PUT] /auths/{authId}
    @RequestMapping(path = "/{authId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateAuth(
            @PathVariable int authId,
            /*@Validated @RequestBody AuthResource authResource) throws Exception {*/
            @Validated @RequestBody Auth authResource) throws Exception {
        /*authService.updateBySequence(authId, authResource.toUpdate().toEntity());*/
        authService.updateBySequence(authId, authResource);
        return null;
    }

    // [PUT] /auths/{authId}/resources
    @RequestMapping(path = "/{authId}/resources", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateResource(
            @PathVariable int authId,
            /*@Validated @RequestBody AuthResource authResource) throws Exception {*/
            @Validated @RequestBody List<Integer> listOfResourceId) throws Exception {
        /*authService.updateBySequence(authId, authResource.toUpdate().toEntity());*/
        authService.updateResourcesBySequence(authId, listOfResourceId);
        return null;
    }

    // [DELETE] /auths/{authId}
    @RequestMapping(path = "/{authId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteAuth(@PathVariable int authId) {
        authService.unenable(authId);
        return null;
    }

}