package com.ecoaware.tracker.controller;

import com.ecoaware.tracker.DTO.ComplaintRequest;
import com.ecoaware.tracker.DTO.ComplaintResponse;
import com.ecoaware.tracker.model.Complaint;
import com.ecoaware.tracker.model.Users;
import com.ecoaware.tracker.service.ComplaintService;
import com.ecoaware.tracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
        Users user = userService.getUserByEmail(userDetails.getUsername());
        List<Complaint> complaints = complaintService.getComplaintsById(user.getId());
        List<ComplaintResponse> responses = complaints.stream()
                .map(Complaint -> complaintService.convertToDto(Complaint, user))
                .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/complaints")
    public ResponseEntity<ComplaintResponse> addComplaint(@AuthenticationPrincipal UserDetails userDetails, @RequestBody ComplaintRequest complaintRequest) {
        Users user = userService.getUserByEmail(userDetails.getUsername());
        return ResponseEntity.ok(complaintService.addComplaint(complaintRequest, user));
    }


    @GetMapping("/complaints/{id}")
    public ResponseEntity<ComplaintResponse> getComplaintById(@PathVariable Long id){
        return ResponseEntity.ok(complaintService.getById(id));
    }
}
