package com.ecoaware.tracker.complaint.dto;


import lombok.Data;

@Data
public class ComplaintRequest {
    private String title;
    private String description;
    private String imagePath;
    private Long categoryId;
}
