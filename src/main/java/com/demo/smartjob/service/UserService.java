package com.demo.smartjob.service;

import com.demo.smartjob.dto.UserGetDto;
import com.demo.smartjob.dto.UserPostDto;
import com.demo.smartjob.entity.Phone;
import com.demo.smartjob.entity.User;
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
        Optional<User> user = this.userRepository.findById(id);
        return  mapStructMapper.userToUserGetDto(user.get());
    }

    public UserGetDto getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        return mapStructMapper.userToUserGetDto(user);
    }
}
