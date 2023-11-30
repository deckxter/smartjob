package com.demo.smartjob.controller;

import com.demo.smartjob.entity.User;
import com.demo.smartjob.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/smartjob")
public class UserController {
    private final UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    private ResponseEntity<User> createUser(@RequestBody User user) {
        User userCreated = this.userService.createUser(user);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    private ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        User userFromDb = this.userService.getUserById(id);
        return new ResponseEntity<>(userFromDb, HttpStatus.CREATED);
    }
}
