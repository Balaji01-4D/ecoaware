package com.ecoaware.tracker.DTO;

import lombok.Data;

@Data
public class ComplaintResponse {

    private Long id;
    private String title;
    private String description;
    private String imagePath;
    private UsersResponse usersResponse;
    private CategoryResponse categoryResponse;
}
