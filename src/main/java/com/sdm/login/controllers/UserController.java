package com.sdm.login.controllers;

import com.sdm.login.models.resources.UserResource;
import com.sdm.login.models.tables.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    /*@Autowired
    private UserServiceImpl userService;*/

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "hello?";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity login(UserResource user) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveUser(
            HttpServletRequest req,
            @Valid UserResource userResource,
            BindingResult bindingResult,
            UriComponentsBuilder uriBuilder) throws Exception {
        /* validation with binding */
        if(bindingResult.hasErrors()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST/*400*/)
                    .body("dict"/*TODO*/);
        }

        /*User user = userService.save(userResource.toEntity());*/

        /*URI resourceUri = MvcUriComponentsBuilder
                .relativeTo(uriBuilder)
                .withMethodCall(on(UserController.class).getNodeBySequence(node.getSequence()))
                .build().encode().toUri();*/

        return null;
    }


}
