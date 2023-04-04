package com.rajanish.splitwise.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UserLoginRequestDto {
    private String userName;
    private String hashedPassword;
}
