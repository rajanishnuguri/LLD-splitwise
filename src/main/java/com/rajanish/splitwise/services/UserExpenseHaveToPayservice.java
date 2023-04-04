package com.rajanish.splitwise.services;

import com.rajanish.splitwise.models.Expenses;
import com.rajanish.splitwise.models.UserExpenseHaveToPay;
import com.rajanish.splitwise.repository.UserExpenseHaveToPayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExpenseHaveToPayservice {
    private UserExpenseHaveToPayRepository userExpenseHaveToPayRepository;
    @Autowired
    public UserExpenseHaveToPayservice(UserExpenseHaveToPayRepository userExpenseHaveToPayRepository) {
        this.userExpenseHaveToPayRepository = userExpenseHaveToPayRepository;
    }
    public List<UserExpenseHaveToPay> getExpenseOwed(Expenses expense){
        return userExpenseHaveToPayRepository.findByExpenseEquals(expense);
    }
    public UserExpenseHaveToPay save(UserExpenseHaveToPay userExpenseHaveToPay){
        return userExpenseHaveToPayRepository.save(userExpenseHaveToPay);
    }

}
