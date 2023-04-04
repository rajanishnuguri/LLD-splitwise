package com.rajanish.splitwise.strategies.transaction;

import com.rajanish.splitwise.helper.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionStrategy {
    public List<Transaction> getTransactions(Map<String,Double> transactions,String baseCurrency);
}
