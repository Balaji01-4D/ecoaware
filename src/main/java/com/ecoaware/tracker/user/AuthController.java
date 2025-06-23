package com.ecoaware.tracker.user;

import com.ecoaware.tracker.user.dao.AuthenticationResponse;
import com.ecoaware.tracker.user.dao.LoginRequest;
import com.ecoaware.tracker.user.dao.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.register(registerRequest));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.authenticate(loginRequest));
    }
}
