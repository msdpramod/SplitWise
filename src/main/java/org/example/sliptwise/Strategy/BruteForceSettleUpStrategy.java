package org.example.sliptwise.Strategy;

import org.example.sliptwise.Models.Expense;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("bruteForceSettleUpStrategy")
public class BruteForceSettleUpStrategy implements SettleStrategy {

    @Override
    public List<Transaction> settle(List<Expense> expenses) {
        return null;
    }
}