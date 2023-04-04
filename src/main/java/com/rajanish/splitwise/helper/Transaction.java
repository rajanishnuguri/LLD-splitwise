package com.rajanish.splitwise.helper;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private String payingUser;
    private String owingUser;
    private double amount;
    private String baseCurrency;
}
