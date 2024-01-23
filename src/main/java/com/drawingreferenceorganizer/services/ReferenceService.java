package com.drawingreferenceorganizer.services;

import com.drawingreferenceorganizer.models.User;
import com.drawingreferenceorganizer.repositories.ReferenceRepository;
import com.drawingreferenceorganizer.models.Reference;
import com.drawingreferenceorganizer.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReferenceService {

    private final ReferenceRepository referenceRepository;
    private final UserRepository userRepository;

    public ResponseEntity<List<Reference>> list() {
        return new ResponseEntity<>(referenceRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getReferenceById(long id) {
        Optional<Reference> reference = referenceRepository.findById(id);
        if (reference.isPresent()) {
            return new ResponseEntity<>(reference.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid reference id", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Reference> addReference(Reference reference, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        reference.setUser(user);
        return new ResponseEntity<>(
                referenceRepository.save(reference),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<?> deleteReference(long id, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Reference reference = referenceRepository.getReferenceById(id);
        if (reference.getUser().getId() == user.getId()) {
            referenceRepository.deleteById(id);
            return new ResponseEntity<>(
                    "Reference is successfully deleted.",
                    HttpStatus.ACCEPTED
            );
        }
        return new ResponseEntity<>(
                "Invalid reference id",
                HttpStatus.UNAUTHORIZED
        );
    }

    public ResponseEntity<?> updateReference(Reference updatedReference, long id, Principal principal) {
        User user = userRepository.findUsersByEmail(principal.getName());
        Reference reference = referenceRepository.getReferenceById(id);
        if (reference.getUser().getId() == user.getId()) {
            reference.setDescription(updatedReference.getDescription());
            reference.setUrl(updatedReference.getUrl());
            return new ResponseEntity<>(
                    referenceRepository.save(reference),
                    HttpStatus.ACCEPTED
            );
        }
        return new ResponseEntity<>(
                "Invalid reference id",
                HttpStatus.UNAUTHORIZED
        );
    }
}
