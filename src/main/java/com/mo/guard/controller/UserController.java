package com.mo.guard.controller;

import com.mo.guard.constant.EnableFlag;
import com.mo.guard.model.resource.UserResource;
import com.mo.guard.model.table.App;
import com.mo.guard.model.table.User;
import com.mo.guard.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /*@RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity login(UserResource user) {
        return null;
    }*/

    // [GET] /users
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<User> getUsers() throws Exception {
        return userService.findAllByEnableFlag(EnableFlag.Y.getValue());
    }

    // [POST] /users
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> saveUser(
            /*@Validated @RequestBody UserResource userResource,*/
            @Validated @RequestBody User userResource,
            BindingResult bindingResult,
            UriComponentsBuilder uriComponentsBuilder) throws Exception {

        /* validation with binding */
        if(bindingResult.hasErrors()) {
            /*return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST*//*400*//*)
                    .body("dict"*//*TODO*//*);*/
        }

        /*User user = userService.save(userResource.toEntity());*/
        User user = userService.save(userResource);

        URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriComponentsBuilder)
                .withMethodCall(on(UserController.class).getUser(user.getSequence()))
                .build().encode().toUri();
        return ResponseEntity.created(resourceUri).build();
    }

    // [GET] /users/{userSeq}
    @RequestMapping(path = "/{userSeq}", method = RequestMethod.GET)
    /*public AppResource getApp(@PathVariable int userSeq) throws Exception {*/
    public User getUser(@PathVariable int userSeq) throws Exception {
        return userService.findBySequence(userSeq);
    }

    // [GET] /users/username/{username}
    @RequestMapping(path = "/username/{username}", method = RequestMethod.GET)
    /*public AppResource getApp(@PathVariable int userSeq) throws Exception {*/
    public User getUserByUsername(@PathVariable String username) throws Exception {
        return userService.findByUsernameAndEnableFlag(username, EnableFlag.Y.getValue());
    }

    // [PUT] /users/{userSeq}
    @RequestMapping(path = "/{userSeq}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateApp(
            @PathVariable int userSeq,
            /*@Validated @RequestBody UserResource userResource) throws Exception {*/
            @Validated @RequestBody User userResource) throws Exception {
        /*appService.updateBySequence(appId, appResource.toUpdate().toEntity());*/
        userService.updateBySequence(userSeq, userResource);
        return null;
    }

    // [DELETE] /users/{userSeq}
    @RequestMapping(path = "/{userSeq}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable int userSeq) {
        userService.unenable(userSeq);
        return null;
    }

}
