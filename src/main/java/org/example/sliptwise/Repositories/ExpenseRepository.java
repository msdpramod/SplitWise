package org.example.sliptwise.Repositories;

import org.example.sliptwise.Models.Expense;
import org.example.sliptwise.Models.Group;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long>{
  List<Expense> findAllByGroup(Group group);
}
