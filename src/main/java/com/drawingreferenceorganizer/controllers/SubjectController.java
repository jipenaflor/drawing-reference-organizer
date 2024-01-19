package com.drawingreferenceorganizer.controllers;

import com.drawingreferenceorganizer.models.Subject;
import com.drawingreferenceorganizer.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<Subject> getAllSubjects() {
        return subjectService.list();
    }

    @GetMapping("/{id}")
    public Optional<Subject> findById(@PathVariable long id) {
        return subjectService.getSubjectById(id);
    }

    @GetMapping("title/{referenceTitle}")
    public List<Subject> findByTitle(@PathVariable String referenceTitle) {
        return subjectService.getSubjectByTitle(referenceTitle);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subject addSubject(@RequestBody Subject subject, Principal principal) {
        return subjectService.addSubject(subject, principal);
    }

    @PostMapping("/{subjectId}/references/{referenceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReferenceToSubject(
            @PathVariable long subjectId,
            @PathVariable long referenceId,
            Principal principal) {
        subjectService.addReferenceToSubject(subjectId, referenceId, principal);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable long id, Principal principal) {
        subjectService.deleteSubject(id, principal);
    }

    @DeleteMapping("/{subjectId}/references/{referenceId}")
    public void deleteReferenceFromSubject(
            @PathVariable long subjectId,
            @PathVariable long referenceId,
            Principal principal) {
        subjectService.deleteReferenceFromSubject(subjectId, referenceId, principal);
    }

    @PutMapping("/{id}")
    public void updateSubject(
            @RequestBody Subject subject,
            @PathVariable long id,
            Principal principal) {
        subjectService.updateSubject(subject, id, principal);
    }
}
