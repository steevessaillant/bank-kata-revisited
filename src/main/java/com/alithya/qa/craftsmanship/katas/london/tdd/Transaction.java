package com.alithya.qa.craftsmanship.katas.london.tdd;

import lombok.Builder;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
public class Transaction {

    public Date date;
    public Amount value;

    private String printDebit(Amount currentBalance, DateFormat dateFormatter) {
        return  "| " + dateFormatter.format(this.date) +
                " |          | " +
                this.value.abs().moneyRepresentation() +
                " | " +
                currentBalance.thenAdd(this.value).moneyRepresentation() +
                " |";
    }


    public void printTo(PrintStream printer, Amount currentBalance) {
        DateFormat dateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");

        if(this.value.isGreaterThanOrEqual(Amount.amountOf(0)))
        {
            printer.println(printCredit(currentBalance,dateFormatter));
        }else{
            printer.println(printDebit(currentBalance,dateFormatter));
        }
    }

    private String printCredit(Amount currentBalance, DateFormat dateFormatter) {

        return  "| " + dateFormatter.format(this.date) +
                " | " +
                this.value.moneyRepresentation() +
                " |          | " +
                currentBalance.thenAdd(this.value).moneyRepresentation() +
                " |";
    }


    public Amount balanceAfterTransaction(Amount currentBalance) {
        return value.thenAdd(currentBalance);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Transaction)) {
            return false;
        }
        if (this == obj)
            return true;
        Transaction other = (Transaction) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (value != null) {
            return value.equals(other.value);
        } else {
            return other.value == null;
        }
    }

}
