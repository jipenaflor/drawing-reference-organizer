package com.example.drawingreferencesaver.controllers;

import com.example.drawingreferencesaver.models.Reference;
import com.example.drawingreferencesaver.services.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/references")
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService;

    @GetMapping
    public List<Reference> getReferences() {
        return referenceService.list();
    }

    @PostMapping("/references")
    @ResponseStatus(HttpStatus.CREATED)
    public Reference addReference(@RequestBody Reference reference) {
        return referenceService.addReference(reference);
    }
}
