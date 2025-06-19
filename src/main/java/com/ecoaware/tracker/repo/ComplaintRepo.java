package com.ecoaware.tracker.repo;

import com.ecoaware.tracker.model.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, Long> {

}
