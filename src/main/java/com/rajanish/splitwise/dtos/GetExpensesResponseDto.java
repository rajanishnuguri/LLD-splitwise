package com.rajanish.splitwise.dtos;

import com.rajanish.splitwise.helper.GroupExpenses;
import com.rajanish.splitwise.helper.Transaction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class GetExpensesResponseDto {
    private List<Transaction> transactions;
    private List<GroupExpenses> expenses;

}
