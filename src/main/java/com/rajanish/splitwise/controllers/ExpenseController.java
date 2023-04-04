package com.rajanish.splitwise.controllers;

import com.rajanish.splitwise.dtos.CreateExpenseRequestDto;
import com.rajanish.splitwise.dtos.GetExpensesResponseDto;
import com.rajanish.splitwise.enums.ResponseStatus;
import com.rajanish.splitwise.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {
    private ExpenseService expenseService;
    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping("/group/expense/create")
    public String createExpense(@RequestBody CreateExpenseRequestDto request){
        String status=expenseService.generateExpense(request.getGroupId(),
                request.getDescription(),
                request.getCurrency(),
                request.getCreatedBy(),
                request.getParticipants(),
                request.getPayingUser(),
                request.getOwingUsers());
        if(status==ResponseStatus.SUCCESS)
            return status;
        return ResponseStatus.FAILURE;
    }
    @GetMapping("/group/expenses/{groupId}")
        public GetExpensesResponseDto getExpenses(@PathVariable("groupId")Long groupId){
        GetExpensesResponseDto responseDto=expenseService.findExpensesOfGroup(groupId);
        return responseDto;
    }
}
