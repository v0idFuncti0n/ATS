package com.fst.atsmasterdataservice.controller;

import com.fst.atsmasterdataservice.dto.UserDTO;
import com.fst.atsmasterdataservice.entity.candidate.UserEntity;
import com.fst.atsmasterdataservice.request.AuthenticationRequest;
import com.fst.atsmasterdataservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> authenticateUser(@RequestBody AuthenticationRequest request) {
        UserDTO user = userService.authenticate(request);
        if(user != null) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }
}

