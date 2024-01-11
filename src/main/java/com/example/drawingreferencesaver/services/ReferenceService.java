package com.example.drawingreferencesaver.services;

import com.example.drawingreferencesaver.models.Reference;
import com.example.drawingreferencesaver.repositories.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;

    public List<Reference> list() {
        return referenceRepository.findAll();
    }

    public Reference getReference(Long id) {
        return referenceRepository.getReferenceById(id);
    }

    public void addReference(Reference reference) {
        referenceRepository.save(reference);
    }
}
