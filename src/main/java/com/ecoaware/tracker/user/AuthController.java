package com.ecoaware.tracker.user;

import com.ecoaware.tracker.security.JwtService;
import com.ecoaware.tracker.user.dao.AuthenticationResponse;
import com.ecoaware.tracker.user.dao.LoginRequest;
import com.ecoaware.tracker.user.dao.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        Users user = userService.fromRegisterRequestDto(registerRequest);
        var registeredUser = userService.register(user);
        String token = jwtService.generateToken(new HashMap<>(), new UserPrincipal(registeredUser));
        return ResponseEntity.ok(userService.toAuthenticationResponseDto(token));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody LoginRequest loginRequest) {
        Users user = new Users();
        user.setEmail(loginRequest.getEmail());
        user.setPassword(loginRequest.getPassword());

        if (userService.authenticate(user)){
            String token = jwtService.generateToken(new HashMap<>(), new UserPrincipal(user));
            return ResponseEntity.ok(userService.toAuthenticationResponseDto(token));
        }
        else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
