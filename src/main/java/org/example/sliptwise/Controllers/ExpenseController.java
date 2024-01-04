package org.example.sliptwise.Controllers;

import org.example.sliptwise.DTOs.RequestSettelUserDTO;
import org.example.sliptwise.DTOs.RequestSettleGroupDTO;
import org.example.sliptwise.DTOs.ResponseSettelUserDTO;
import org.example.sliptwise.DTOs.ResponseSettleGroupDTO;
import org.example.sliptwise.Service.ExpenseService;
import org.example.sliptwise.Strategy.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {
    private ExpenseService expenseService;


    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public ResponseSettelUserDTO settleUpUser(RequestSettelUserDTO request) {
        List<Transaction> transactions = expenseService
                .settleUser(request.getUserId());


        ResponseSettelUserDTO response = new ResponseSettelUserDTO();
        response.setStatus("SUCCESS");
        response.setTransactions(transactions);

        return response;
    }

    public ResponseSettleGroupDTO settleUpGroup(RequestSettleGroupDTO request) {
        return null;
    }
}