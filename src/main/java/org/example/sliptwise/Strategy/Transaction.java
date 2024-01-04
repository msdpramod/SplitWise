package org.example.sliptwise.Strategy;


import lombok.Getter;
import lombok.Setter;
import org.example.sliptwise.Models.User;

@Getter
@Setter
public class Transaction {
    private User from;
    private User to;
    private Integer amount;
}