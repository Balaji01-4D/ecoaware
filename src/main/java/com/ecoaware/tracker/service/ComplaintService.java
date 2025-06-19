package com.ecoaware.tracker.service;

import com.ecoaware.tracker.DTO.ComplaintRequest;
import com.ecoaware.tracker.DTO.ComplaintResponse;
import com.ecoaware.tracker.model.Complaint;
import com.ecoaware.tracker.model.Users;
import com.ecoaware.tracker.repo.ComplaintRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

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

    public ComplaintResponse addComplaint(ComplaintRequest complaintRequest, Users user) {
        Complaint complaint = new Complaint();
        complaint.setTitle(complaintRequest.getTitle());
        complaint.setDescription(complaintRequest.getDescription());
        complaint.setCategory(categoryService.findById(complaintRequest.getCategoryId()));
        complaint.setImagePath(complaintRequest.getImagePath());
        complaint.setStatus("PENDING");
        complaint.setCreatedAt(LocalDateTime.now());
        complaint.setCreatedBy(user);
        complaintRepo.save(complaint);
        return convertToDto(complaint, user);
    }

    public ComplaintResponse getById(Long id) {
        Complaint complaint = complaintRepo.findById(id).orElse(new Complaint());
        return convertToDto(complaint, complaint.getCreatedBy());
    }

    public List<Complaint> getComplaintsById(Long id) {
        return complaintRepo.findByCreatedById(id);
    }

    public ComplaintResponse convertToDto(Complaint complaint, Users user) {
        ComplaintResponse response = new ComplaintResponse();
        response.setId(complaint.getId());
        response.setTitle(complaint.getTitle());
        response.setDescription(complaint.getDescription());
        response.setImagePath(complaint.getImagePath());
        response.setUsersResponse(userService.convertToUserResponseDto(user));
        response.setCategoryResponse(categoryService.convertToCategoryDto(complaint.getCategory()));
        return response;
    }
}
