package com.ecoaware.tracker.DTO;


import lombok.Data;

@Data
public class ComplaintRequest {
    private String title;
    private String description;
    private String imagePath;
    private Long categoryId;
}
