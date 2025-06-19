package com.ecoaware.tracker.controller;


import com.ecoaware.tracker.model.Complaint;
import com.ecoaware.tracker.service.ComplaintService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok("this is home page");
    }

    @GetMapping("/complaints")
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        return ResponseEntity.ok(complaintService.getAllComplaints());
    }

    @PostMapping("/complaints")
    public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
        return ResponseEntity.ok(complaintService.addComplaint(complaint));
    }

    @GetMapping("/complaints/{id}")
    public ResponseEntity<Complaint> getComplaintById(@PathVariable Long id){
        return ResponseEntity.ok(complaintService.getById(id));
    }
}
