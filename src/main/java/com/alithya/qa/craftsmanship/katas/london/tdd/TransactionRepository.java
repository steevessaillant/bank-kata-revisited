package com.alithya.qa.craftsmanship.katas.london.tdd;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class TransactionRepository {
    private List<Transaction> transactions = new ArrayList<>();

    public void add(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
