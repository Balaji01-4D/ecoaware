package com.ecoaware.tracker.user.dao;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private String role;
}
