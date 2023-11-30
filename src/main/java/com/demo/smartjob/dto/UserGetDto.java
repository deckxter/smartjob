package com.demo.smartjob.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Data
public class UserGetDto {
    private UUID id;
    private String name;
    private String email;
    private List<PhoneDto> phones;
}
