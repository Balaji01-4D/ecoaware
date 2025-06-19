package com.ecoaware.tracker.controller;

import com.ecoaware.tracker.model.Users;
import com.ecoaware.tracker.service.UserService;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody Users user) {
        return ResponseEntity.ok(userService.addUser(user));
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Users user) {
        if (userService.verify(user))
            return ResponseEntity.ok("success");
        return ResponseEntity.ok("failure");
    }
}
