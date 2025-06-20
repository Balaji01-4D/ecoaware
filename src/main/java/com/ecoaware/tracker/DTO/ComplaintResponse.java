package com.ecoaware.tracker.DTO;

import com.ecoaware.tracker.enums.Status;
import lombok.Data;

@Data
public class ComplaintResponse {

    private Long id;
    private String title;
    private String description;
    private String imagePath;
    private Status status;
    private UsersResponse usersResponse;
    private CategoryResponse categoryResponse;
}
