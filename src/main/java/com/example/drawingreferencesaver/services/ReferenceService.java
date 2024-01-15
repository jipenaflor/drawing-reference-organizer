package com.example.drawingreferencesaver.services;

import com.example.drawingreferencesaver.models.Reference;
import com.example.drawingreferencesaver.repositories.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;

    public List<Reference> list() {
        return referenceRepository.findAll();
    }

    public Optional<Reference> getReferenceById(long id) {
        return this.referenceRepository.findById(id);
    }

    public Reference addReference(Reference reference) {
        return this.referenceRepository.save(reference);
    }

    public void deleteReference(long id) {
        this.referenceRepository.deleteById(id);
    }

    public Reference updateReference(Reference updatedReference, long id) {
        Reference reference = referenceRepository.getReferenceById(id);
        reference.setDescription(updatedReference.getDescription());
        reference.setUrl(updatedReference.getUrl());
        return referenceRepository.save(reference);
    }

}
