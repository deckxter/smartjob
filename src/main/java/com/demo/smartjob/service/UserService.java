package com.demo.smartjob.service;

import com.demo.smartjob.dto.UserGetDto;
import com.demo.smartjob.dto.UserPostDto;
import com.demo.smartjob.dto.UserPutDto;
import com.demo.smartjob.entity.User;
import com.demo.smartjob.exceptions.BadEmailException;
import com.demo.smartjob.exceptions.BadPasswordException;
import com.demo.smartjob.exceptions.EmailAlreadyExistsException;
import com.demo.smartjob.exceptions.ResourceNotFoundException;
import com.demo.smartjob.mapper.MapStructMapper;
import com.demo.smartjob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class UserService {
    @Value("${app.password.regexp}")
    private String passwordRegex;

    @Value("${app.email.regexp}")
    private String emailRegex;

    private final UserRepository userRepository;
    private final MapStructMapper mapStructMapper;

    private final String EMAIL_ALREADY_EXIST = "Email already exists";
    private final String EMAIL_NOT_FOUND = "User with email not found";
    private final String BAD_EMAIL_REGEXP = "The email doesn't meet the required pattern";
    private final String USER_ID_NOT_FOUND = "User id not found";
    private final String BAD_PASSWORD_REGEXP = "Password doesn't meet the minimum requirements";

    public UserService(UserRepository userRepository, MapStructMapper mapStructMapper) {
        this.userRepository = userRepository;
        this.mapStructMapper = mapStructMapper;
    }

    public UserGetDto createUser(UserPostDto userPostDto) {
        if(emailAlreadyExists(userPostDto.getEmail())) throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXIST);

        if(!emailMeetsRegexp(userPostDto.getEmail())) throw new BadEmailException(BAD_EMAIL_REGEXP);

        if(!passwordMeetsRegexp(userPostDto.getPassword())) throw new BadPasswordException(BAD_PASSWORD_REGEXP);

        User userToBePersisted = mapStructMapper.userPostDtoToUser(userPostDto);
        User userPersisted = this.userRepository.save(userToBePersisted);
        return mapStructMapper.userToUserGetDto(userPersisted);
    }

    public UserGetDto getUserById(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(USER_ID_NOT_FOUND));

        return  mapStructMapper.userToUserGetDto(user);
    }

    public UserGetDto getUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email);
        if(user == null)
            throw new ResourceNotFoundException(EMAIL_NOT_FOUND);

        return mapStructMapper.userToUserGetDto(user);
    }

    public void deleteUserById(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(USER_ID_NOT_FOUND));

        userRepository.delete(user);
    }

    public UserGetDto updateUser(UserPutDto userPutDto) {
        //First: check user exists
        User user = this.userRepository.findById(userPutDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(USER_ID_NOT_FOUND));


        if(userPutDto.getEmail() != null) {
            //Second: Check email regexp
            if(!emailMeetsRegexp(userPutDto.getEmail())) throw new BadEmailException(BAD_EMAIL_REGEXP);

            //Third: Check email from dto, already exists in a different user
            User userEmail = this.userRepository.findByEmail(userPutDto.getEmail());
            if(user.getId() != userEmail.getId()) throw new EmailAlreadyExistsException(EMAIL_ALREADY_EXIST);
        }

        if(userPutDto.getPassword() != null) {
            //Fourth: Password matches regexp
            if(!passwordMeetsRegexp(userPutDto.getPassword())) throw new BadPasswordException(BAD_PASSWORD_REGEXP);
        }


        User userToBePersisted = mapStructMapper.userPutDtoToUser(userPutDto);
        User userPersisted = this.userRepository.save(userToBePersisted);
        return mapStructMapper.userToUserGetDto(userPersisted);
    }

    public boolean emailAlreadyExists(String email) {
        return (userRepository.findByEmail(email) != null);
    }

    public boolean passwordMeetsRegexp(String password) {
        return Pattern.compile(passwordRegex).matcher(password).matches();
    }

    public boolean emailMeetsRegexp(String email) {
        return Pattern.compile(emailRegex).matcher(email).matches();
    }
}
