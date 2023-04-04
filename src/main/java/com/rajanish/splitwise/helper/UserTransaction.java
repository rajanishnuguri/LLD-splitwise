package com.rajanish.splitwise.helper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTransaction{
    public UserTransaction(String userName,double amount){
        this.amount=amount;
        this.userName=userName;
    }
    private String userName;
    private double amount;
}
