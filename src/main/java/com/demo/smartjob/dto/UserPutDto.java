package com.demo.smartjob.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class UserPutDto {
    @NotNull
    private UUID id;

    private String name;
    private String email;
    private List<PhoneDto> phones;
}
