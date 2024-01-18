package com.drawingreferencesaver.services;

import com.drawingreferencesaver.models.User;
import com.drawingreferencesaver.repositories.ReferenceRepository;
import com.drawingreferencesaver.models.Reference;
import com.drawingreferencesaver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ReferenceService {
    @Autowired
    private ReferenceRepository referenceRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Reference> list() {
        return referenceRepository.findAll();
    }

    public Optional<Reference> getReferenceById(long id) {
        return this.referenceRepository.findById(id);
    }

    public Reference addReference(Reference reference, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        reference.setUser(user);
        return this.referenceRepository.save(reference);
    }

    public void deleteReference(long id, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Reference reference = referenceRepository.getReferenceById(id);
        if (reference.getUser().getId() == user.getId()) {
            this.referenceRepository.deleteById(id);
        }
    }

    public void updateReference(Reference updatedReference, long id, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Reference reference = referenceRepository.getReferenceById(id);
        if (reference.getUser().getId() == user.getId()) {
            reference.setDescription(updatedReference.getDescription());
            reference.setUrl(updatedReference.getUrl());
            referenceRepository.save(reference);
        }
    }

}
