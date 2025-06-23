package com.ecoaware.tracker.complaint;

import com.ecoaware.tracker.category.Category;
import com.ecoaware.tracker.enums.Status;
import com.ecoaware.tracker.user.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imagePath;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    @ManyToOne
    private Users createdBy;

    @ManyToOne
    private Category category;
}
