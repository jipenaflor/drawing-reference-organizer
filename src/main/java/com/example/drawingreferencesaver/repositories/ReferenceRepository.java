package com.example.drawingreferencesaver.repositories;

import com.example.drawingreferencesaver.models.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long> {
}
