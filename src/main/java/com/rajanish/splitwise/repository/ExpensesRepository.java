package com.rajanish.splitwise.repository;

import com.rajanish.splitwise.models.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExpensesRepository extends JpaRepository<Expenses, Long> {
    Expenses save(Expenses expense);
}
