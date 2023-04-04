package com.rajanish.splitwise.repository;

import com.rajanish.splitwise.models.Expenses;
import com.rajanish.splitwise.models.UserExpenseHaveToPay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseHaveToPayRepository extends JpaRepository<UserExpenseHaveToPay,Long> {
    UserExpenseHaveToPay save(UserExpenseHaveToPay userExpenseHaveToPay);
    List<UserExpenseHaveToPay> findByExpenseEquals(Expenses expense);
}
