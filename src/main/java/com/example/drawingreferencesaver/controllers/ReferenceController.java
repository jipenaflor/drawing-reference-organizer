package com.example.drawingreferencesaver.controllers;

import com.example.drawingreferencesaver.models.Reference;
import com.example.drawingreferencesaver.services.ReferenceService;
import com.example.drawingreferencesaver.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService;

    @GetMapping("/reference")
    public List<Reference> getReferences() {
        return referenceService.list();
    }

    @PostMapping("/reference")
    public void addReference(@RequestBody Reference reference) {
        referenceService.addReference(reference);
    }
}
