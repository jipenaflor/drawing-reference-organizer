package com.drawingreferenceorganizer.repositories;

import com.drawingreferenceorganizer.models.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
