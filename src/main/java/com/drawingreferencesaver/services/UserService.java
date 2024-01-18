package com.drawingreferencesaver.services;

import com.drawingreferencesaver.models.User;
import com.drawingreferencesaver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(long id) {
        return this.userRepository.findById(id);
    }
}
