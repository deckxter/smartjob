package com.demo.smartjob.controller;

import com.demo.smartjob.dto.UserGetDto;
import com.demo.smartjob.dto.UserPostDto;
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
    private ResponseEntity<UserGetDto> createUser(@RequestBody UserPostDto userPostDto) {
        UserGetDto userCreated = this.userService.createUser(userPostDto);
        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }

    @GetMapping("/users/{id}")
    private ResponseEntity<UserGetDto> getUserById(@PathVariable("id") UUID id) {
        UserGetDto userGetDto = this.userService.getUserById(id);
        return new ResponseEntity<>(userGetDto, HttpStatus.CREATED);
    }
}
