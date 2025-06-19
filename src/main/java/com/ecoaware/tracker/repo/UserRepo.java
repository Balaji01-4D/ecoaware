package com.ecoaware.tracker.repo;

import com.ecoaware.tracker.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users getByName(String username);
    Users findByEmail(String username);

}
