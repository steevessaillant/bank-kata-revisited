//step # 2
package com.alithya.qa.craftsmanship.katas.london.tdd;

import java.io.PrintStream;
import java.util.Date;

//has a running balance, no need for balance keeping here
public class Account
{

    public String accountID;
    private Statement statement;
    private TransactionRepository transactionRepository;

    public Account(String accountId, Statement statement, TransactionRepository transactionRepository) {
        this.accountID = accountId;
        this.statement = statement;
        this.transactionRepository = transactionRepository;
    }

    public PrintStream printStatement(PrintStream printer){
        this.statement.printTo(printer);
        return printer;
    }

    public Transaction deposit(Amount depositAmount, Date depositDate) {
        Transaction actualTransaction =  Transaction
                .builder()
                .value(depositAmount)
                .date(depositDate)
                .build();
        statement.addLineContaining(actualTransaction, actualTransaction.value);
        transactionRepository.add(actualTransaction);
        return actualTransaction;
    }

    public Transaction withdrawal(Amount withdrawalAmount, Date withdrawalDate) {
        Transaction actualTransaction = Transaction
                .builder().
                value(withdrawalAmount)
                .date(withdrawalDate)
                .build();
        statement.addLineContaining(actualTransaction, actualTransaction.value);
        return actualTransaction;
    }

}
