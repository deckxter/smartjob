package com.demo.smartjob.service;

import com.demo.smartjob.dto.UserGetDto;
import com.demo.smartjob.dto.UserPostDto;
import com.demo.smartjob.dto.UserPutDto;
import com.demo.smartjob.entity.Phone;
import com.demo.smartjob.entity.User;
import com.demo.smartjob.exceptions.ResourceNotFoundException;
import com.demo.smartjob.mapper.MapStructMapper;
import com.demo.smartjob.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final MapStructMapper mapStructMapper;

    public UserService(UserRepository userRepository, MapStructMapper mapStructMapper) {
        this.userRepository = userRepository;
        this.mapStructMapper = mapStructMapper;
    }

    public UserGetDto createUser(UserPostDto userPostDto) {
        User userToBePersisted = mapStructMapper.userPostDtoToUser(userPostDto);
        User userPersisted = this.userRepository.save(userToBePersisted);
        return mapStructMapper.userToUserGetDto(userPersisted);
    }

    public UserGetDto getUserById(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id: " +id+  " not found"));

        return  mapStructMapper.userToUserGetDto(user);
    }

    public UserGetDto getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        if(user == null)
            throw new ResourceNotFoundException("User with email: " +email+  " not found");

        return mapStructMapper.userToUserGetDto(user);
    }

    public void deleteUserById(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User with id: " +id+  " not found"));

        userRepository.delete(user);
    }

    public UserGetDto updateUser(UserPutDto userPutDto) {
        User user = this.userRepository.findById(userPutDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User with id: " +userPutDto.getId()+  " not found"));

        User userToBePersisted = mapStructMapper.userPutDtoToUser(userPutDto);
        User userPersisted = this.userRepository.save(userToBePersisted);
        return mapStructMapper.userToUserGetDto(userPersisted);
    }
}
