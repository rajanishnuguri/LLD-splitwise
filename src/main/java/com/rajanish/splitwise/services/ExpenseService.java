package com.rajanish.splitwise.services;

import com.rajanish.splitwise.dtos.GetExpensesResponseDto;
import com.rajanish.splitwise.enums.ResponseStatus;
import com.rajanish.splitwise.helper.GroupExpenses;
import com.rajanish.splitwise.helper.Transaction;
import com.rajanish.splitwise.helper.UserOwed;
import com.rajanish.splitwise.helper.UserPaid;
import com.rajanish.splitwise.models.*;
import com.rajanish.splitwise.repository.*;
import com.rajanish.splitwise.strategies.transaction.HeapTransactionStrategy;
import com.rajanish.splitwise.strategies.transaction.TransactionStrategy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class ExpenseService {
    private UserExpensePaidService userExpensePaidService;
    private UserExpenseHaveToPayservice userExpenseHaveToPayservice;
    private ExpensesRepository expensesRepository;
    private UserService userService;
    private CurrrencyService currrencyService;
    private GroupService groupService;
    @Autowired
    public ExpenseService(UserExpensePaidService userExpensePaidService, UserExpenseHaveToPayservice userExpenseHaveToPayservice, ExpensesRepository expensesRepository
    ,UserService userService,CurrrencyService currrencyService,GroupService groupService) {
        this.userExpensePaidService=userExpensePaidService;
        this.userExpenseHaveToPayservice = userExpenseHaveToPayservice;
        this.expensesRepository = expensesRepository;
        this.userService = userService;
        this.currrencyService=currrencyService;
        this.groupService= groupService;
    }
    public String generateExpense(Long groupId, String description, String currency, String createdBy, List<String> participants, UserPaid userPaid, List<UserOwed> userOwed){
        Expenses expense=new Expenses();
        List<Users> participant=new ArrayList<>();
        expense.setParticipants(participant);
        for(String userName:participants){
            expense.getParticipants().add(userService.getUser(userName));
        }

        Currrency baseCurrency=currrencyService.findCurrency(currency);
        Optional<Currrency> opt = Optional.ofNullable(baseCurrency);
        if(opt.isEmpty()) {
            baseCurrency = new Currrency();
            baseCurrency.setBaseCurrency(currency);
            baseCurrency = currrencyService.save(baseCurrency);
        }
        expense.setBaseCurrency(baseCurrency);
        expense.setDescription(description);
        expense.setUserCreatedBy(userService.getUser(createdBy));
        expensesRepository.save(expense);

        Groups group=groupService.findGroup(groupId);
        UserExpensePaid userExpensePaid= new UserExpensePaid();
        userExpensePaid.setExpense(expense);
        userExpensePaid.setUser(userService.getUser(userPaid.getPayingUser()));
        userExpensePaid.setAmount(userPaid.getAmount());
        userExpensePaidService.save(userExpensePaid);

        for(UserOwed userOwes:userOwed){
            UserExpenseHaveToPay userExpenseHaveToPay= new UserExpenseHaveToPay();
            userExpenseHaveToPay.setExpense(expense);
            userExpenseHaveToPay.setUser(userService.getUser(userOwes.getOwingUser()));
            userExpenseHaveToPay.setAmount(userOwes.getAmount());
            userExpenseHaveToPayservice.save(userExpenseHaveToPay);
        }
        List<Expenses> expensesList=group.getExpenses();
        if(expensesList.isEmpty())
            expensesList=new ArrayList<>();
        expensesList.add(expense);
        group.setExpenses(expensesList);
        groupService.save(group);
        return ResponseStatus.SUCCESS;
    }
public GetExpensesResponseDto findExpensesOfGroup(Long groupId){
        List<Expenses> expensesList=groupService.getExpensesForGroup(groupId);
        List<UserExpensePaid> userExpensePaidList=userExpensePaidService.getExpensesPaid(expensesList);
        List<GroupExpenses> groupExpensesList = new ArrayList<>();
        Map<String,Double> userAmounts=new HashMap<>();
        for(UserExpensePaid userPaid: userExpensePaidList){
            GroupExpenses groupExpense=new GroupExpenses();
            Expenses expense=userPaid.getExpense();
            groupExpense.setDescription(userPaid.getExpense().getDescription());
            groupExpense.setBaseCurrency(userPaid.getExpense().getBaseCurrency().getBaseCurrency());
            groupExpense.setCreatedDate(userPaid.getExpense().getCreatedDate());

            UserPaid payingUser=new UserPaid();
            payingUser.setPayingUser(userPaid.getUser().getUserName());
            payingUser.setAmount(userPaid.getAmount());
            List<UserExpenseHaveToPay> userExpenseHaveToPayList=userExpenseHaveToPayservice.getExpenseOwed(userPaid.getExpense());
            groupExpense.setPayingUser(payingUser);
            List<UserOwed> userOwedList=new ArrayList<>();
            double amount =0,tempAmount=0;
            if(userAmounts.containsKey(userPaid.getUser().getUserName())) {
                tempAmount = userAmounts.get(userPaid.getUser().getUserName());
                tempAmount+=userPaid.getAmount();
            }else{
                tempAmount=userPaid.getAmount();
            }
            userAmounts.put(userPaid.getUser().getUserName(), tempAmount);
            for(UserExpenseHaveToPay userOwed: userExpenseHaveToPayList){
                UserOwed userOwes=new UserOwed();
                userOwes.setOwingUser(userOwed.getUser().getUserName());
                userOwes.setAmount(userOwed.getAmount());
                userOwedList.add(userOwes);
                amount+= userOwed.getAmount();
                if(userAmounts.containsKey(userOwed.getUser().getUserName())) {
                    tempAmount = userAmounts.get(userOwed.getUser().getUserName());
                    tempAmount+=userOwed.getAmount();
                }else{
                    tempAmount=userOwed.getAmount();
                }
                userAmounts.put(userOwed.getUser().getUserName(), -tempAmount);
            }
            UserOwed userOwes=new UserOwed();
            userOwes.setOwingUser(userPaid.getUser().getUserName());
            userOwes.setAmount(userPaid.getAmount()-amount);
            userOwedList.add(userOwes);
            groupExpense.setUserOwedList(userOwedList);
            groupExpensesList.add(groupExpense);
        }
        GetExpensesResponseDto getExpensesResponseDto=new GetExpensesResponseDto();
        getExpensesResponseDto.setExpenses(groupExpensesList);
        TransactionStrategy transactionStrategy= new HeapTransactionStrategy();
        List<Transaction> transactionList=new ArrayList<>();

        getExpensesResponseDto.setTransactions(transactionStrategy.getTransactions(userAmounts,"INR"));
        return getExpensesResponseDto;
}
}