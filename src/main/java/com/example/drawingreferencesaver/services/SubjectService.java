package com.example.drawingreferencesaver.services;

import com.example.drawingreferencesaver.models.Reference;
import com.example.drawingreferencesaver.repositories.ReferenceRepository;
import com.example.drawingreferencesaver.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.drawingreferencesaver.models.Subject;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ReferenceRepository referenceRepository;

    public List<Subject> list() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(long id) {
        return this.subjectRepository.findById(id);
    }

    public List<Subject> getSubjectByTitle(String title) {
        return this.subjectRepository.findSubjectsByTitle(title);
    }

    public Subject addSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public void addReferenceToSubject(Long subjectId, Long referenceId) {
        Subject subject = subjectRepository.getReferenceById(subjectId);
        Reference reference = referenceRepository.getReferenceById(referenceId);
        subject.getReferences().add(reference);
        subjectRepository.save(subject);
    }
}
