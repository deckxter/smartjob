package com.demo.smartjob.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class PhoneDto {
    @NotNull
    private String number;

    @NotNull
    private String cityCode;

    @NotNull
    private String countryCode;
}
