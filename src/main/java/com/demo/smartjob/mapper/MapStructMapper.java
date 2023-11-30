package com.demo.smartjob.mapper;

import com.demo.smartjob.dto.PhoneDto;
import com.demo.smartjob.dto.UserGetDto;
import com.demo.smartjob.dto.UserPostDto;
import com.demo.smartjob.entity.Phone;
import com.demo.smartjob.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    PhoneDto phoneToPhoneDto(Phone phone);

    Phone phoneDtoToPhone(PhoneDto phoneDto);
    UserPostDto userToUserPostDto(User user);
    UserGetDto userToUserGetDto(User user);
    User userPostDtoToUser(UserPostDto userPostDto);
}
