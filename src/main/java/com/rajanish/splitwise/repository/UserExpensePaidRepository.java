package com.rajanish.splitwise.repository;

import com.rajanish.splitwise.models.Expenses;
import com.rajanish.splitwise.models.UserExpensePaid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpensePaidRepository extends JpaRepository<UserExpensePaid,Long> {
    UserExpensePaid save(UserExpensePaid userExpensePaid);
    List<UserExpensePaid> findByExpenseIn(List<Expenses> expenses);
}
