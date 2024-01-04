package org.example.sliptwise.DTOs;

import lombok.Getter;
import lombok.Setter;
import org.example.sliptwise.Strategy.Transaction;

import java.util.List;

@Getter@Setter
public class ResponseSettleGroupDTO {
    String message;
    String status;
    List<Transaction> transactions;
}
