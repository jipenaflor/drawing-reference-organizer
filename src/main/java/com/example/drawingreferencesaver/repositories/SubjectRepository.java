package com.example.drawingreferencesaver.repositories;

import com.example.drawingreferencesaver.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findSubjectsByTitle(String title);
}
