package org.example.sliptwise.Service;

import org.example.sliptwise.Models.Expense;
import org.example.sliptwise.Models.Group;
import org.example.sliptwise.Models.User;
import org.example.sliptwise.Models.UserExpense;
import org.example.sliptwise.Repositories.ExpenseRepository;
import org.example.sliptwise.Repositories.GroupRepository;
import org.example.sliptwise.Repositories.UserExpenseRepository;
import org.example.sliptwise.Repositories.UserRepository;
import org.example.sliptwise.Strategy.SettleStrategy;
import org.example.sliptwise.Strategy.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {
    private UserRepository userRepository;
    private UserExpenseRepository userExpenseRepository;
    private SettleStrategy settleStrategy;
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(UserRepository userRepository, UserExpenseRepository userExpenseRepository,
                          @Qualifier("twoSetsSettleUpStrategy")   SettleStrategy settleStrategy,
                          GroupRepository groupRepository, ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.settleStrategy = settleStrategy;
        this.groupRepository = groupRepository;
        this.expenseRepository = expenseRepository;
    }




    public List<Transaction> settleUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            // throw Exception
            return null;
        }

        List<UserExpense> userExpenses = userExpenseRepository.findAllByUser(userOptional.get());

        List<Expense> expensesInvolvingUser = new ArrayList<>();
        for (UserExpense userExpense: userExpenses) {
            expensesInvolvingUser.add(userExpense.getExpense());
        }

        List<Transaction> transactions = settleStrategy.settle(expensesInvolvingUser);

        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction: transactions) {
            if (transaction.getFrom().equals(userOptional.get()) || transaction.getTo().equals(userOptional.get())) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    public List<Transaction> settleUpGroup(Long groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if (groupOptional.isEmpty()) {
            // throw nexception
            return null;
        }

        List<Expense> expenses = expenseRepository.findAllByGroup(groupOptional.get());

        List<Transaction> transactions = settleStrategy.settle(
                expenses
        );

        return transactions;
    }
}