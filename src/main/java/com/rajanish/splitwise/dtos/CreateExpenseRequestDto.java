package com.rajanish.splitwise.dtos;

import com.rajanish.splitwise.helper.UserOwed;
import com.rajanish.splitwise.helper.UserPaid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class CreateExpenseRequestDto {

    private Long groupId;
    private String description;
    private String currency;
    private String createdBy;
    private List<String> participants;
    private UserPaid payingUser;
    private List<UserOwed> owingUsers;
}
