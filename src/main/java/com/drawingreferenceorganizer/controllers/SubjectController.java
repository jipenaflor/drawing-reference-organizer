package com.drawingreferenceorganizer.controllers;

import com.drawingreferenceorganizer.models.Subject;
import com.drawingreferenceorganizer.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return subjectService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping("title/{referenceTitle}")
    public ResponseEntity<List<Subject>> findByTitle(@PathVariable String referenceTitle) {
        return subjectService.getSubjectByTitle(referenceTitle);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject, Principal principal) {
        return subjectService.addSubject(subject, principal);
    }

    @PostMapping("/{subjectId}/references/{referenceId}")
    public ResponseEntity<?> addReferenceToSubject(
            @PathVariable long subjectId,
            @PathVariable long referenceId,
            Principal principal) {
        return subjectService.addReferenceToSubject(subjectId, referenceId, principal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable long id, Principal principal) {
        return subjectService.deleteSubject(id, principal);
    }

    @DeleteMapping("/{subjectId}/references/{referenceId}")
    public ResponseEntity<?> deleteReferenceFromSubject(
            @PathVariable long subjectId,
            @PathVariable long referenceId,
            Principal principal) {
        return subjectService.deleteReferenceFromSubject(subjectId, referenceId, principal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(
            @RequestBody Subject subject,
            @PathVariable long id,
            Principal principal) {
        return subjectService.updateSubject(subject, id, principal);
    }
}
