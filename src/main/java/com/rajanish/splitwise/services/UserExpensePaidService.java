package com.rajanish.splitwise.services;

import com.rajanish.splitwise.models.Expenses;
import com.rajanish.splitwise.models.UserExpensePaid;
import com.rajanish.splitwise.repository.UserExpensePaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExpensePaidService {
    private UserExpensePaidRepository userExpensePaidRepository;
    @Autowired
    public UserExpensePaidService(UserExpensePaidRepository userExpensePaidRepository) {
        this.userExpensePaidRepository = userExpensePaidRepository;
    }

    public List<UserExpensePaid> getExpensesPaid(List<Expenses> expensesList){
        return userExpensePaidRepository.findByExpenseIn(expensesList);
    }
    public UserExpensePaid save(UserExpensePaid userExpensePaid){
        return userExpensePaidRepository.save(userExpensePaid);
    }

}
