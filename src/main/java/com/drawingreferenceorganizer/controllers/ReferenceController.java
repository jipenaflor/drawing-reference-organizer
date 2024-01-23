package com.drawingreferenceorganizer.controllers;

import com.drawingreferenceorganizer.services.ReferenceService;
import com.drawingreferenceorganizer.models.Reference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/references")
@RequiredArgsConstructor
public class ReferenceController {

    private final ReferenceService referenceService;

    @GetMapping
    public ResponseEntity<List<Reference>> getReferences() {
        return referenceService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        return referenceService.getReferenceById(id);
    }

    @PostMapping
    public ResponseEntity<Reference> addReference(
            @RequestBody Reference reference,
            Principal principal
    ) {
        return referenceService.addReference(reference, principal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReference(@PathVariable long id, Principal principal) {
        return referenceService.deleteReference(id, principal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReference(
            @RequestBody Reference reference,
            @PathVariable long id,
            Principal principal) {
        return referenceService.updateReference(reference, id, principal);
    }
}
