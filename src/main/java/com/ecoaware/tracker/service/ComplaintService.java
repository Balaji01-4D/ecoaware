package com.ecoaware.tracker.service;

import com.ecoaware.tracker.DTO.ComplaintRequest;
import com.ecoaware.tracker.DTO.ComplaintResponse;
import com.ecoaware.tracker.model.Complaint;
import com.ecoaware.tracker.model.Users;
import com.ecoaware.tracker.repo.ComplaintRepo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ComplaintService {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ComplaintRepo complaintRepo;

    public ComplaintService(UserService userService, CategoryService categoryService, ComplaintRepo complaintRepo) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.complaintRepo = complaintRepo;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public Complaint fromRequestDto(ComplaintRequest complaintRequest, Users user){
        Complaint complaint = new Complaint();
        complaint.setTitle(complaintRequest.getTitle());
        complaint.setDescription(complaintRequest.getDescription());
        complaint.setCategory(categoryService.findById(complaintRequest.getCategoryId()));
        complaint.setImagePath(complaintRequest.getImagePath());
        complaint.setStatus("PENDING");
        complaint.setCreatedAt(LocalDateTime.now());
        complaint.setCreatedBy(user);
        return complaint;
    }

    public Complaint getById(Long id) {
        return complaintRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "complaint not found"));
    }

    public List<Complaint> getComplaintsById(Long id) {
        return complaintRepo.findByCreatedById(id);
    }

    public ComplaintResponse convertToResponseDto(Complaint complaint) {
        Users user = complaint.getCreatedBy();
        ComplaintResponse response = new ComplaintResponse();
        response.setId(complaint.getId());
        response.setTitle(complaint.getTitle());
        response.setDescription(complaint.getDescription());
        response.setImagePath(complaint.getImagePath());
        response.setUsersResponse(userService.convertToUserResponseDto(user));
        response.setCategoryResponse(categoryService.convertToCategoryDto(complaint.getCategory()));
        return response;
    }

    public void deleteComplaintById(Long id) {
        complaintRepo.deleteById(id);
    }


    public Complaint updateFromRequestDto(Complaint complaint, ComplaintRequest complaintRequest) {
        String title = complaintRequest.getTitle();
        String description = complaintRequest.getDescription();
        String imagePath = complaintRequest.getImagePath();
        Long categoryId = complaintRequest.getCategoryId();
        if (title != null) complaint.setTitle(title);
        if (description != null) complaint.setDescription(description);
        if (imagePath != null) complaint.setImagePath(imagePath);
        if (categoryId != null) complaint.setCategory(categoryService.findById(categoryId));

        return complaint;
    }

    public void saveComplaint(Complaint complaint) {
        complaintRepo.save(complaint);
    }


}


