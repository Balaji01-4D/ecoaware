package com.ecoaware.tracker.user;

import com.ecoaware.tracker.user.dao.AuthenticationResponse;
import com.ecoaware.tracker.user.dao.RegisterRequest;
import com.ecoaware.tracker.user.dao.UsersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;


    public Users register(Users user) {
        return userRepository.save(user);
    }

    public AuthenticationResponse toAuthenticationResponseDto(String token) {
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

    public boolean authenticate(Users user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        return authentication.isAuthenticated();
    }

    public Users fromRegisterRequestDto(RegisterRequest registerRequest) {
        return new Users(registerRequest.getName(),
                registerRequest.getEmail(),
                registerRequest.getPassword(),
                registerRequest.getRole()
        );
    }
}
