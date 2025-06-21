package com.ecoaware.tracker.controller;

import com.ecoaware.tracker.DTO.ComplaintRequest;
import com.ecoaware.tracker.DTO.ComplaintResponse;
import com.ecoaware.tracker.model.Complaint;
import com.ecoaware.tracker.model.Users;
import com.ecoaware.tracker.service.ComplaintService;
import com.ecoaware.tracker.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class ComplaintController {

    private final UserService userService;
    private final ComplaintService complaintService;

    public ComplaintController(UserService userService, ComplaintService complaintService) {
        this.userService = userService;
        this.complaintService = complaintService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("this is home page");
    }

    // for learning
    @GetMapping("/me")
    public ResponseEntity<String> getMyData(@AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(userDetails.getUsername());
    }

    @GetMapping("/complaints")
    public ResponseEntity<List<ComplaintResponse>> getMyComplaints(@AuthenticationPrincipal UserDetails userDetails) {
        Users user = userService.findUserByEmail(userDetails.getUsername());
        List<Complaint> complaints = complaintService.getComplaintsById(user.getId());
        List<ComplaintResponse> responses = complaints.stream()
                .map(complaintService::convertToResponseDto)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/complaints")
    public ResponseEntity<ComplaintResponse> addComplaint(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ComplaintRequest complaintRequest) {
        Users user = userService.findUserByEmail(userDetails.getUsername());
        Complaint complaint = complaintService.fromRequestDto(complaintRequest, user);
        complaintService.saveComplaint(complaint);
        return ResponseEntity.ok(complaintService.convertToResponseDto(complaint));
    }


    @GetMapping("/complaints/{id}")
    public ResponseEntity<ComplaintResponse> getComplaintById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id){
        Complaint complaint = complaintService.getById(id);
        Users user = userService.findUserByEmail(userDetails.getUsername());
        if (!user.getRole().equals("ADMIN") && !complaint.getCreatedBy().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "complaint not found");
        }
        ComplaintResponse response = complaintService.convertToResponseDto(complaint);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/complaints/{id}")
    public ResponseEntity<ComplaintResponse> updateComplaintById(@AuthenticationPrincipal UserDetails userDetails,
                                                                 @PathVariable Long id,
                                                                 @RequestBody ComplaintRequest complaintRequest) {
        Complaint complaint = complaintService.getById(id);
        Users user = userService.findUserByEmail(userDetails.getUsername());
        if (!user.getRole().equals("ADMIN") && !complaint.getCreatedBy().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "complaint not found");
        }
        Complaint updateComplaint = complaintService.updateFromRequestDto(complaint, complaintRequest);
        complaintService.saveComplaint(updateComplaint);
        return ResponseEntity.ok(complaintService.convertToResponseDto(updateComplaint));
    }

    @DeleteMapping("/complaints/{id}")
    public ResponseEntity<String> deleteComplaintById(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id){
        Complaint complaint = complaintService.getById(id);
        Users user = userService.findUserByEmail(userDetails.getUsername());
        if (!user.getRole().equals("ADMIN") && !complaint.getCreatedBy().getId().equals(user.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "complaint not found");
        }
        complaintService.deleteComplaintById(id);
        return ResponseEntity.ok("complaint deleted");
    }

}
