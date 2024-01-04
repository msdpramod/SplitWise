package org.example.sliptwise.Strategy;

import ch.qos.logback.core.joran.sanity.Pair;
import org.example.sliptwise.Models.Expense;
import org.example.sliptwise.Models.User;
import org.example.sliptwise.Models.UserExpense;
import org.example.sliptwise.Models.UserExpenseType;
import org.example.sliptwise.Repositories.UserExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

@Component("twoSetsSettleUpStrategy")
public class TwoSetsSettleUpStrategy implements SettleStrategy {
    private UserExpenseRepository userExpenseRepository;

    @Autowired
    public TwoSetsSettleUpStrategy(UserExpenseRepository userExpenseRepository) {
        this.userExpenseRepository = userExpenseRepository;
    }

    @Override
    public List<Transaction> settle(
            List<Expense> expenses) {
        // for all expenses thqat I have to settle, who paid how much and who had to pay how much
        List<UserExpense> allUserExpenseForTheseExpenses = userExpenseRepository.findAllByExpenseIn(expenses);

        HashMap<User, Integer> moneyPaidExtra = new HashMap<>();

        // I went through all of the expenses and found out who has paid how much extra or less
        for (UserExpense userExpense: allUserExpenseForTheseExpenses) {
            User user = userExpense.getUser();
            int currentExtraPaid = 0;

            if (moneyPaidExtra.containsKey(user)) {
                currentExtraPaid = moneyPaidExtra.get(user);
            }

            if (userExpense.getUserExpenseType().equals(UserExpenseType.PAID)) {
                moneyPaidExtra.put(user, currentExtraPaid + userExpense.getAmount());
            } else {
                moneyPaidExtra.put(user, currentExtraPaid - userExpense.getAmount());
            }
        }

        TreeSet<Pair<User, Integer>> extraPaid = new TreeSet<>();
        TreeSet<Pair<User, Integer>> lessPaid = new TreeSet<>();


        // {Key, Value}
        for (Map.Entry<User, Integer> userAmount: moneyPaidExtra.entrySet()) {
            if (userAmount.getValue() < 0) {
                lessPaid.add(new Pair<>(userAmount.getKey(), userAmount.getValue()));
            } else {
                extraPaid.add(new Pair<>(userAmount.getKey(), userAmount.getValue()));
            }
        }

        List<Transaction> transactions = new ArrayList<>();

        while (!lessPaid.isEmpty()) {
            Pair<User, Integer> lessPaidPair = lessPaid.pollFirst(); // get and remove the first value
            Pair<User, Integer> extraPaidPair = extraPaid.pollFirst();

            Transaction t = new Transaction();
            t.setFrom(lessPaidPair.a);
            t.setTo(extraPaidPair.a);

            if (Math.abs(lessPaidPair.b) < extraPaidPair.b) { // Vikram : -100 // Deepika: +200
                t.setAmount(Math.abs(lessPaidPair.b));

                if (!(extraPaidPair.b - Math.abs(lessPaidPair.b) == 0)) {
                    extraPaid.add(new Pair<>(extraPaidPair.a, extraPaidPair.b - Math.abs(lessPaidPair.b)));
                }
            } else { // Vikram : -200 // Deepika: 100
                t.setAmount(extraPaidPair.b);

                if (!(lessPaidPair.b + extraPaidPair.b == 0)) {
                    lessPaid.add(new Pair<>(lessPaidPair.a, lessPaidPair.b + extraPaidPair.b));
                }
            }

            transactions.add(t);
        }

        return transactions;
    }
    public class Pair<A, B> {
        private A a;
        private B b;

        public Pair(A a, B b) {
            this.a = a;
            this.b = b;
        }

        public A getA() {
            return a;
        }

        public B getB() {
            return b;
        }
    }
}
