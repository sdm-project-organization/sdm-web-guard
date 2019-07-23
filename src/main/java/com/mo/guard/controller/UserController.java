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

    // [GET] /users/{userId}
    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    /*public AppResource getApp(@PathVariable int userId) throws Exception {*/
    public User getUser(@PathVariable int userId) throws Exception {
        return userService.findBySequence(userId);
    }

    // [PUT] /users/{userId}
    @RequestMapping(path = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateApp(
            @PathVariable int userId,
            /*@Validated @RequestBody UserResource userResource) throws Exception {*/
            @Validated @RequestBody User userResource) throws Exception {
        /*appService.updateBySequence(appId, appResource.toUpdate().toEntity());*/
        userService.updateBySequence(userId, userResource);
        return null;
    }

    // [DELETE] /users/{userId}
    @RequestMapping(path = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) {
        userService.unenable(userId);
        return null;
    }

}
