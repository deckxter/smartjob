package com.demo.smartjob.controller;

import com.demo.smartjob.dto.UserGetDto;
import com.demo.smartjob.dto.UserPostDto;
import com.demo.smartjob.dto.UserPutDto;
import com.demo.smartjob.service.UserService;
import jakarta.validation.Valid;
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
    public UserGetDto createUser(@Valid @RequestBody UserPostDto userPostDto) {
        return this.userService.createUser(userPostDto);
    }

    @GetMapping("/users/{id}")
    public UserGetDto getUserById(@PathVariable("id") UUID id) {
        return this.userService.getUserById(id);
    }

    @GetMapping("/users/email/{email}")
    public UserGetDto getUserByEmail(@PathVariable("email") String email) {
        return this.userService.getUserByEmail(email);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable("id") UUID id) {
        this.userService.deleteUserById(id);
    }

    @PutMapping("/users")
    public UserGetDto updateUser(@Valid @RequestBody UserPutDto userPutDto) {
        return this.userService.updateUser(userPutDto);
    }
}
