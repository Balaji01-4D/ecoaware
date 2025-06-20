package com.ecoaware.tracker.controller;

import com.ecoaware.tracker.DTO.ComplaintResponse;
import com.ecoaware.tracker.DTO.ComplaintStatusUpdate;
import com.ecoaware.tracker.model.Complaint;
import com.ecoaware.tracker.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private ComplaintService complaintService;

    public AdminController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }


    @GetMapping
    public String home() {
        return "hello world, admin";
    }

    @GetMapping("/complaints")
    public ResponseEntity<List<ComplaintResponse>> getAllComplaint() {
        List<ComplaintResponse> complaintResponses = complaintService.getAllComplaints().stream()
                .map(complaint -> complaintService.convertToResponseDto(complaint))
                .toList();

        return ResponseEntity.ok(complaintResponses);
    }


    @PutMapping("/admin/complaints/{id}/status")
    public ResponseEntity<ComplaintResponse> updateStatus(@PathVariable Long id, @RequestBody ComplaintStatusUpdate complaintStatus) {
        Complaint complaint = complaintService.getById(id);
        complaint.setStatus(complaintStatus.getStatus());
        return ResponseEntity.ok(complaintService.convertToResponseDto(complaint));
    }



}
