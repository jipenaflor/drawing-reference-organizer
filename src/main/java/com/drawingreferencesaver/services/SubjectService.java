package com.drawingreferencesaver.services;

import com.drawingreferencesaver.models.Subject;
import com.drawingreferencesaver.repositories.ReferenceRepository;
import com.drawingreferencesaver.models.Reference;
import com.drawingreferencesaver.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return this.subjectRepository.save(subject);
    }

    public void addReferenceToSubject(long subjectId, long referenceId) {
        Subject subject = subjectRepository.getReferenceById(subjectId);
        Reference reference = referenceRepository.getReferenceById(referenceId);
        subject.getReferences().add(reference);
        subjectRepository.save(subject);
    }

    public void deleteSubject(long id) {
        this.subjectRepository.deleteById(id);
    }

    public void deleteReferenceFromSubject(long subjectId, long referenceId) {
        Subject subject = subjectRepository.getReferenceById(subjectId);
        Reference reference = referenceRepository.getReferenceById(referenceId);
        subject.getReferences().remove(reference);
        subjectRepository.save(subject);
    }

    public Subject updateSubject(Subject updatedSubject, long id) {
        Subject subject = subjectRepository.getReferenceById(id);
        subject.setTitle(updatedSubject.getTitle());
        return subjectRepository.save(subject);
    }
}
