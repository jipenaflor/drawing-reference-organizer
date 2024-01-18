package com.drawingreferencesaver.repositories;

import com.drawingreferencesaver.models.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
