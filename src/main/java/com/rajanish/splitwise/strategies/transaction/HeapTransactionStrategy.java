package com.rajanish.splitwise.strategies.transaction;

import com.rajanish.splitwise.helper.Transaction;
import com.rajanish.splitwise.helper.UserTransaction;

import java.util.*;

public class HeapTransactionStrategy implements TransactionStrategy{

    @Override
    public List<Transaction> getTransactions(Map<String, Double> transactions,String baseCurrency) {
        Queue<UserTransaction> negativeHeap=new PriorityQueue<>(10 ,(a,b)-> (int)(b.getAmount()-a.getAmount()));
        Queue<UserTransaction> positiveHeap=new PriorityQueue<>(10 ,(a,b)-> (int)(a.getAmount()-b.getAmount()));
        for(Map.Entry<String,Double> transaction:transactions.entrySet()){
            if(transaction.getValue()>=0)
                positiveHeap.add(new UserTransaction(transaction.getKey(),transaction.getValue()));
            else
                negativeHeap.add(new UserTransaction(transaction.getKey(),transaction.getValue()));
        }
        List<Transaction> transactionList= new ArrayList<>();
        UserTransaction positiveUser;
        UserTransaction negativeUser;
        Transaction tempTransaction;
        while(!negativeHeap.isEmpty() ){
            positiveUser =positiveHeap.poll();
            negativeUser =negativeHeap.poll();
            double negativeAmount = negativeUser.getAmount();
            double positiveAmount = positiveUser.getAmount();
            if(positiveAmount > Math.abs(negativeAmount)){
                positiveUser.setAmount(positiveAmount+negativeAmount);
                positiveHeap.add(positiveUser);
                tempTransaction= new Transaction();
                tempTransaction.setPayingUser(negativeUser.getUserName());
                tempTransaction.setOwingUser(positiveUser.getUserName());
                tempTransaction.setAmount(Math.abs(negativeAmount));
                tempTransaction.setBaseCurrency(baseCurrency);
                transactionList.add(tempTransaction);
            } else if(positiveUser.getAmount() < Math.abs(negativeAmount)){
                negativeUser.setAmount(positiveAmount+negativeAmount);
                negativeHeap.add(negativeUser);
                tempTransaction= new Transaction();
                tempTransaction.setPayingUser(negativeUser.getUserName());
                tempTransaction.setOwingUser(positiveUser.getUserName());
                tempTransaction.setAmount(Math.abs(positiveAmount));
                tempTransaction.setBaseCurrency(baseCurrency);
                transactionList.add(tempTransaction);
            }
        }

        return transactionList;
    }

}
