package com.ecoaware.tracker.controller;

import com.ecoaware.tracker.DTO.UsersRequest;
import com.ecoaware.tracker.DTO.UsersResponse;
import com.ecoaware.tracker.model.Users;
import com.ecoaware.tracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsersResponse> register(@RequestBody UsersRequest usersRequest) {
        return ResponseEntity.ok(userService.addUser(usersRequest));
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsersRequest usersRequest) {
        String email = usersRequest.getEmail();
        Users user;
        if (email == null) {
            throw new ErrorResponseException(HttpStatus.NOT_FOUND);
        }
        user = userService.getUserByEmail(email);
        if (user.getPassword().equals(usersRequest.getPassword())){
            return ResponseEntity.ok("success");
        }
        return ResponseEntity.ok("wrong password");
    }
}
