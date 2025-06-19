package com.ecoaware.tracker.service;

import com.ecoaware.tracker.model.Users;
import com.ecoaware.tracker.repo.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepo userRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Users addUser(Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public boolean verify(Users user) {
        return userRepo.existsById(user.getId());
    }

    public Users loadUserByUsername(String username) {
        return userRepo.getByName(username);
    }
}
