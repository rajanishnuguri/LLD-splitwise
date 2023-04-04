package com.rajanish.splitwise.helper;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Setter
@Getter
@Component
public class UserOwed{
    private String owingUser;
    private Double amount;
}