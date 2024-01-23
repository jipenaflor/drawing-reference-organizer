package com.drawingreferenceorganizer.services;

import com.drawingreferenceorganizer.models.Subject;
import com.drawingreferenceorganizer.models.User;
import com.drawingreferenceorganizer.repositories.ReferenceRepository;
import com.drawingreferenceorganizer.models.Reference;
import com.drawingreferenceorganizer.repositories.SubjectRepository;
import com.drawingreferenceorganizer.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final ReferenceRepository referenceRepository;
    private final UserRepository userRepository;

    public ResponseEntity<List<Subject>> list() {
        return new ResponseEntity<>(subjectRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getSubjectById(long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            return new ResponseEntity<>(subject.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid subject id", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Subject>> getSubjectByTitle(String title) {
        return new ResponseEntity<>(subjectRepository.findSubjectsByTitle(title), HttpStatus.OK);
    }

    public ResponseEntity<Subject> addSubject(Subject subject, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        subject.setUser(user);
        return new ResponseEntity<>(subjectRepository.save(subject), HttpStatus.CREATED);
    }

    public ResponseEntity<?> addReferenceToSubject(long subjectId, long referenceId, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        Optional<Reference> reference = referenceRepository.findById(referenceId);
        if (subject.isPresent() && reference.isPresent()
                && subject.get().getUser().getId() == user.getId()
                && reference.get().getId() == user.getId()) {
            subject.get().getReferences().add(reference.get());
            return new ResponseEntity<>(
                    subjectRepository.save(subject.get()),
                    HttpStatus.ACCEPTED
            );
        }
        return new ResponseEntity<>(
                "Invalid subject or reference id",
                HttpStatus.UNAUTHORIZED
        );
    }

    public ResponseEntity<?> deleteSubject(long id, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Subject subject = subjectRepository.getReferenceById(id);
        if (subject.getUser().getId() == user.getId()) {
            subjectRepository.deleteById(id);
            return new ResponseEntity<>(
                    "Subject is successfully deleted",
                    HttpStatus.ACCEPTED
            );
        }
        return new ResponseEntity<>(
                "Invalid subject or reference id",
                HttpStatus.UNAUTHORIZED
        );
    }

    public ResponseEntity<?> deleteReferenceFromSubject(long subjectId, long referenceId, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Optional<Subject> subject = subjectRepository.findById(subjectId);
        Optional<Reference> reference = referenceRepository.findById(referenceId);
        if (subject.isPresent() && reference.isPresent()
                && subject.get().getUser().getId() == user.getId()
                && reference.get().getId() == user.getId()) {
            subject.get().getReferences().remove(reference.get());
            return new ResponseEntity<>(
                    subjectRepository.save(subject.get()),
                    HttpStatus.ACCEPTED
            );
        }
        return new ResponseEntity<>(
                "Invalid subject or reference id",
                HttpStatus.UNAUTHORIZED
        );
    }

    public ResponseEntity<?> updateSubject(Subject updatedSubject, long id, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Subject subject = subjectRepository.getReferenceById(id);
        if (subject.getUser().getId() == user.getId()) {
            subject.setTitle(updatedSubject.getTitle());
            return new ResponseEntity<>(subjectRepository.save(subject), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(
                "Invalid subject id",
                HttpStatus.UNAUTHORIZED
        );
    }
}
