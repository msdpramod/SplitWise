package org.example.sliptwise.Repositories;

import org.example.sliptwise.Models.Expense;
import org.example.sliptwise.Models.User;

import org.example.sliptwise.Models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {

     List<UserExpense> findAllByUser(User user);
     List<UserExpense> findAllByExpenseIn(List<Expense> expenses);
}
