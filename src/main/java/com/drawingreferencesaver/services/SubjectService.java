package com.drawingreferencesaver.services;

import com.drawingreferencesaver.models.Subject;
import com.drawingreferencesaver.models.User;
import com.drawingreferencesaver.repositories.ReferenceRepository;
import com.drawingreferencesaver.models.Reference;
import com.drawingreferencesaver.repositories.SubjectRepository;
import com.drawingreferencesaver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ReferenceRepository referenceRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Subject> list() {
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(long id) {
        return this.subjectRepository.findById(id);
    }

    public List<Subject> getSubjectByTitle(String title) {
        return this.subjectRepository.findSubjectsByTitle(title);
    }

    public Subject addSubject(Subject subject, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        subject.setUser(user);
        return this.subjectRepository.save(subject);
    }

    public void addReferenceToSubject(long subjectId, long referenceId, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Subject subject = subjectRepository.getReferenceById(subjectId);
        Reference reference = referenceRepository.getReferenceById(referenceId);
        if (subject.getUser().getId() == user.getId() &&
                reference.getUser().getId() == user.getId()) {
            subject.getReferences().add(reference);
            subjectRepository.save(subject);
        }
    }

    public void deleteSubject(long id, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Subject subject = subjectRepository.getReferenceById(id);
        if (subject.getUser().getId() == user.getId()) {
            this.subjectRepository.deleteById(id);
        }
    }

    public void deleteReferenceFromSubject(long subjectId, long referenceId, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Subject subject = subjectRepository.getReferenceById(subjectId);
        Reference reference = referenceRepository.getReferenceById(referenceId);
        if (subject.getUser().getId() == user.getId() &&
                reference.getUser().getId() == user.getId()) {
            subject.getReferences().remove(reference);
            subjectRepository.save(subject);
        }
    }

    public void updateSubject(Subject updatedSubject, long id, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Subject subject = subjectRepository.getReferenceById(id);
        if (subject.getUser().getId() == user.getId()) {
            subject.setTitle(updatedSubject.getTitle());
            subjectRepository.save(subject);
        }
    }
}
