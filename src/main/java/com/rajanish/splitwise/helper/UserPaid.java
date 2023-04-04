package com.rajanish.splitwise.helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
public class UserPaid{
    private String payingUser;
    private Double amount;
}
