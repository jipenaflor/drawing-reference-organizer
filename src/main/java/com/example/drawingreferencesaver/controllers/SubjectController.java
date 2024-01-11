package com.example.drawingreferencesaver.controllers;

import com.example.drawingreferencesaver.models.Subject;
import com.example.drawingreferencesaver.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping("/subjects")
    public List<Subject> getAllSubjects() {
        return subjectService.list();
    }

    @GetMapping("/subjects/{id}")
    public Subject getOneSubject(@PathVariable Long id) {
        return subjectService.getSubject(id);
    }

    @PostMapping("/subject")
    public void addSubject(@RequestBody Subject subject) {
        subjectService.addSubject(subject);
    }

    @PostMapping("/subject/{subjectId}/reference/{referenceId}")
    public void addReferenceToSubject(@PathVariable Long subjectId, @PathVariable Long referenceId) {
        subjectService.addReferenceToSubject(subjectId, referenceId);
    }
}
