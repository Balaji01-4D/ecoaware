package com.ecoaware.tracker.service;

import com.ecoaware.tracker.DTO.UsersRequest;
import com.ecoaware.tracker.DTO.UsersResponse;
import com.ecoaware.tracker.model.Users;
import com.ecoaware.tracker.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public UsersResponse addUser(UsersRequest usersRequest) {
        return convertToUserResponseDto(userRepo.save(convertToUser(usersRequest)));
    }

    private Users convertToUser(UsersRequest usersRequest){
        String encodedPassword = passwordEncoder(usersRequest.getPassword());
        return new Users(
                usersRequest.getName(),
                usersRequest.getEmail(),
                encodedPassword,
                usersRequest.getRole()
        );

    }

    private String passwordEncoder(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean verify(Users user) {
        return userRepo.existsById(user.getId());
    }

    public Users loadUserByUsername(String username) {
        return userRepo.getByName(username);
    }

    public Users getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UsersResponse convertToUserResponseDto(Users user) {
        UsersResponse response = new UsersResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        return response;
    }

}
