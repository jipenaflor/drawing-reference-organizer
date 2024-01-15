package com.example.drawingreferencesaver.controllers;

import com.example.drawingreferencesaver.models.Reference;
import com.example.drawingreferencesaver.services.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/references")
public class ReferenceController {

    @Autowired
    private ReferenceService referenceService;

    @GetMapping
    public List<Reference> getReferences() {
        return referenceService.list();
    }

    @GetMapping("/{id}")
    public Optional<Reference> findById(@PathVariable long id) {
        return referenceService.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reference addReference(@RequestBody Reference reference) {
        return referenceService.addReference(reference);
    }

    @DeleteMapping("/{id}")
    public void deleteReference(@PathVariable long id) {
        referenceService.deleteReference(id);
    }

    @PutMapping("/{id}")
    public Reference updateReference(@RequestBody Reference reference, @PathVariable long id) {
        return referenceService.updateReference(reference, id);
    }
}
