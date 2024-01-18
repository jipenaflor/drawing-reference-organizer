package com.drawingreferencesaver.controllers;

import com.drawingreferencesaver.models.User;
import com.drawingreferencesaver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable long id) {
        return userService.getUserById(id);
    }

}
