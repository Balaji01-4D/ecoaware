package com.ecoaware.tracker.user;

import com.ecoaware.tracker.security.JwtService;
import com.ecoaware.tracker.user.dao.AuthenticationResponse;
import com.ecoaware.tracker.user.dao.LoginRequest;
import com.ecoaware.tracker.user.dao.RegisterRequest;
import com.ecoaware.tracker.user.dao.UsersResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        Users user = userRepository.save(convertToUser(registerRequest));
        String token = jwtService.generateToken(new HashMap<>(), new UserPrincipal(user));
        return toAuthenticationResponseDto(token);
    }

    private AuthenticationResponse toAuthenticationResponseDto(String token) {
        return new AuthenticationResponse(token);
    }

    private Users convertToUser(RegisterRequest registerRequest){
        return new Users(
                registerRequest.getName(),
                registerRequest.getEmail(),
                passwordEncoder(registerRequest.getPassword()),
                registerRequest.getRole()
        );
    }

    private String passwordEncoder(String password) {
        return passwordEncoder.encode(password);
    }

    public Users findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UsersResponse toUserResponseDto(Users user) {
        UsersResponse response = new UsersResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

    public AuthenticationResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            Users user = findUserByEmail(loginRequest.getEmail());
            String token = jwtService.generateToken(new HashMap<>(), new UserPrincipal(user));
            return toAuthenticationResponseDto(token);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
