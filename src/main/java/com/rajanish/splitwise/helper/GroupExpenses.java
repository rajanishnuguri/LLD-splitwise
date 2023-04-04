package com.rajanish.splitwise.helper;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Component
public class GroupExpenses {
    private String description;
    private String baseCurrency;
    private UserPaid payingUser;
    private List<UserOwed> userOwedList;
    private Date createdDate;
}
