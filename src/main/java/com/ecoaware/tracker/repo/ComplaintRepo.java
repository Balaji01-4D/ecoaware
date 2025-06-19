package com.ecoaware.tracker.repo;

import com.ecoaware.tracker.model.Complaint;
import com.ecoaware.tracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, Long> {



    List<Complaint> findByCreatedById(Long id);

}
