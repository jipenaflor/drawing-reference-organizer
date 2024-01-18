package com.drawingreferencesaver.controllers;

import com.drawingreferencesaver.services.ReferenceService;
import com.drawingreferencesaver.models.Reference;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public List<Reference> getReferences() {
        return referenceService.list();
    }

    @GetMapping("/{id}")
    public Optional<Reference> findById(@PathVariable long id) {
        return referenceService.getReferenceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reference addReference(@RequestBody Reference reference, Principal principal) {
        return referenceService.addReference(reference, principal);
    }

    @DeleteMapping("/{id}")
    public void deleteReference(@PathVariable long id, Principal principal) {
        referenceService.deleteReference(id, principal);
    }

    @PutMapping("/{id}")
    public void updateReference(
            @RequestBody Reference reference,
            @PathVariable long id,
            Principal principal) {
        referenceService.updateReference(reference, id, principal);
    }
}
