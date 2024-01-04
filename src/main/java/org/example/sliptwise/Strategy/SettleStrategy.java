package org.example.sliptwise.Strategy;

import org.example.sliptwise.Models.Expense;

import java.util.List;

public interface SettleStrategy {
    List<Transaction> settle(List<Expense> expenses);
}
