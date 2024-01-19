package com.drawingreferenceorganizer.repositories;

import com.drawingreferenceorganizer.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersByEmail(String email);
}
