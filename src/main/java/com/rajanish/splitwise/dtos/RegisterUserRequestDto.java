package com.rajanish.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class RegisterUserRequestDto {
    private String userName;
    private String phoneNumber;
    private String hashedPassword;
}
