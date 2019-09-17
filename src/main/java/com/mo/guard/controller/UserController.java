package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.entity.UserEntity;
import com.mo.guard.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // [GET] /users
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<UserEntity> getUsers() throws Exception {
        return userService.findAllByEnableFlag(EnableFlag.YES);
    }

    // [POST] /users
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> saveUser(
            /*@Validated @RequestBody UserResource userResource,*/
            @Validated @RequestBody UserEntity userResource,
            BindingResult bindingResult,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {

        /* validation with binding */
        if(bindingResult.hasErrors()) {
            /*return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST*//*400*//*)
                    .body("dict"*//*TODO*//*);*/
        }

        /*UserEntity user = userService.save(userResource.toEntity());*/
        UserEntity user = userService.save(userResource);

        URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(UserController.class).getUser(user.getSequence()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    // [GET] /users/{userSeq}
    @RequestMapping(path = "/{userSeq}", method = RequestMethod.GET)
    /*public AppResource getApp(@PathVariable int userSeq) throws Exception {*/
    public UserEntity getUser(@PathVariable int userSeq) throws Exception {
        return userService.findBySequence(userSeq);
    }

    // [GET] /users/username/{username}
    @RequestMapping(path = "/username/{username}", method = RequestMethod.GET)
    /*public AppResource getApp(@PathVariable int userSeq) throws Exception {*/
    public UserEntity getUserByUsername(@PathVariable String username) throws Exception {
        return userService.findByUsernameAndEnableFlag(username, EnableFlag.YES);
    }

    // [PUT] /users/{userSeq}
    @RequestMapping(path = "/{userSeq}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateBySequence(
            @PathVariable int userSeq,
            /*@Validated @RequestBody UserResource userResource) throws Exception {*/
            @Validated @RequestBody UserEntity userResource) throws Exception {
        /*appService.updateBySequence(appId, appResource.toUpdate().toEntity());*/
        userService.updateBySequence(userSeq, userResource);
        return null;
    }

    // [PUT] /users/{userSeq}/roles
    @RequestMapping(path = "/{userSeq}/roles", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateRolesBySequence(
            @PathVariable int userSeq,
            @Validated @RequestBody List<Integer> roles) throws Exception {
        /*appService.updateBySequence(appId, appResource.toUpdate().toEntity());*/
        userService.updateRolesBySequence(userSeq, roles);
        return null;
    }

    // [DELETE] /users/{userSeq}
    @RequestMapping(path = "/{userSeq}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable int userSeq) throws Exception {
        userService.unenable(userSeq);
        return null;
    }

}
