package com.ecoaware.tracker.service;

import com.ecoaware.tracker.model.Complaint;
import com.ecoaware.tracker.repo.ComplaintRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplaintService {


    private final ComplaintRepo complaintRepo;

    public ComplaintService(ComplaintRepo complaintRepo) {
        this.complaintRepo = complaintRepo;
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepo.findAll();
    }

    public Complaint addComplaint(Complaint complaint) {
        return complaintRepo.save(complaint);
    }

    public Complaint getById(Long id) {
        return complaintRepo.findById(id).orElse(new Complaint());
    }
}
