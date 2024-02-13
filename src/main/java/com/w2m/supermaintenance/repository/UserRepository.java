package com.w2m.supermaintenance.repository;

import com.w2m.supermaintenance.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsername(String username);
    Boolean existsByUsername(String username);
}
