package com.example.drawingreferencesaver.controllers;

import com.example.drawingreferencesaver.models.Subject;
import com.example.drawingreferencesaver.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

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
}
