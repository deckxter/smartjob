package com.demo.smartjob.service;

import com.demo.smartjob.entity.User;
import com.demo.smartjob.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public User getUserById(UUID id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.get();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
