package com.alithya.qa.craftsmanship.katas.london.tdd;

import javax.annotation.Nullable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.alithya.qa.craftsmanship.katas.london.tdd.Amount.amountOf;

public class StatementLine {


    private Transaction transaction;
    private Amount currentBalance;

    public StatementLine(Date transactionDate, Amount amount, Amount balance) {
        this.transaction = Transaction
                .builder()
                .date(transactionDate)
                .value(amount)
                .build();
        this.currentBalance = balance;
    }


    public StatementLine(Transaction actualTransaction, Amount balance) {
        this.transaction = actualTransaction;
        this.currentBalance = balance;
    }

    @Nullable
    public String printTo() {

        DateFormat dateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");
        String creditOperation = "| " + dateFormatter.format(transaction.date) +
                " | " +
                transaction.value.moneyRepresentation() +
                " |        | " +
                transaction.balanceAfterTransaction(currentBalance).moneyRepresentation() +
                " |";

        String debitOperation = "| " + dateFormatter.format(transaction.date) +
                " |         | " +
                transaction.value.negative().moneyRepresentation() +
                " | " +
                amountOf(currentBalance.value  - (transaction.value.abs().value)).moneyRepresentation() +
                " |";

        if(transaction.value.isGreaterThanOrEqual(amountOf(0))){
            return creditOperation;
        }else {
            return debitOperation;
        }
    }
}
