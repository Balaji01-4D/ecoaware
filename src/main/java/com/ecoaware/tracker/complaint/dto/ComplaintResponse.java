package com.ecoaware.tracker.complaint.dto;

import com.ecoaware.tracker.category.CategoryResponse;
import com.ecoaware.tracker.enums.Status;
import com.ecoaware.tracker.user.dao.UsersResponse;
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
