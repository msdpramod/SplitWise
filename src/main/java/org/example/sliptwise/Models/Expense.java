package org.example.sliptwise.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Expense extends  BaseModel{
    private int amount;
    private String description;
    @Enumerated( EnumType.ORDINAL)
    private ExpenseType expenseType;
    @ManyToOne
    private User createdBy;
    @ManyToOne
    private Group group;

}
