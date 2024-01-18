package com.drawingreferencesaver.controllers;

import com.drawingreferencesaver.models.Subject;
import com.drawingreferencesaver.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Subject addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }

    @PostMapping("/{subjectId}/references/{referenceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReferenceToSubject(@PathVariable long subjectId, @PathVariable long referenceId) {
        subjectService.addReferenceToSubject(subjectId, referenceId);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable long id) {
        subjectService.deleteSubject(id);
    }

    @DeleteMapping("/{subjectId}/references/{referenceId}")
    public void deleteReferenceFromSubject(@PathVariable long subjectId, @PathVariable long referenceId) {
        subjectService.deleteReferenceFromSubject(subjectId, referenceId);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(@RequestBody Subject subject, @PathVariable long id) {
        return subjectService.updateSubject(subject, id);
    }
}
