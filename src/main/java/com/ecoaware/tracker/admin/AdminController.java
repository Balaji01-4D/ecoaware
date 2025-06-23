package com.ecoaware.tracker.admin;

import com.ecoaware.tracker.complaint.dto.ComplaintResponse;
import com.ecoaware.tracker.complaint.dto.ComplaintStatusUpdate;
import com.ecoaware.tracker.complaint.Complaint;
import com.ecoaware.tracker.complaint.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    private final ComplaintService complaintService;

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
                .map(complaintService::convertToResponseDto)
                .toList();

        return ResponseEntity.ok(complaintResponses);
    }


    @PutMapping("/complaints/{id}/status")
    public ResponseEntity<ComplaintResponse> updateStatus(@PathVariable Long id, @RequestBody ComplaintStatusUpdate complaintStatus) {
        Complaint complaint = complaintService.getById(id);
        complaint.setStatus(complaintStatus.getStatus());
        complaintService.saveComplaint(complaint);
        return ResponseEntity.ok(complaintService.convertToResponseDto(complaint));
    }



}
