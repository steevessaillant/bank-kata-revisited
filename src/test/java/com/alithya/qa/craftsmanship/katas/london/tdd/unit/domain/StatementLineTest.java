package com.alithya.qa.craftsmanship.katas.london.tdd.unit.domain;

import com.alithya.qa.craftsmanship.katas.london.tdd.Amount;
import com.alithya.qa.craftsmanship.katas.london.tdd.StatementLine;
import com.alithya.qa.craftsmanship.katas.london.tdd.Transaction;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.alithya.qa.craftsmanship.katas.london.tdd.Amount.amountOf;
import static org.junit.Assert.assertEquals;

public class StatementLineTest {

    @Mock
    private PrintStream printer;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @SneakyThrows
    public void shouldPrintItself() {
        DateFormat DateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");

        Date depositDate = DateFormatter.parse("10/01/2012");
        Amount depositAmount = amountOf(1000);

        Transaction actualTransaction = Transaction.builder()
                .value(depositAmount)
                .date(depositDate)
                .build();

        StatementLine statementLine = new StatementLine(actualTransaction,amountOf(0));

        String expected = "| 10/01/2012 | 1000.00 |          | 1000.00 |";
         assertEquals(expected,statementLine.printTo());

    }

    @SneakyThrows
    @Test public void shouldPrintWithdrawal() {
        DateFormat DateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");

        Date withdrawalDate = DateFormatter.parse("10/01/2012");
        Amount withdrawalAmount = amountOf(-1000);

        Transaction actualTransaction = Transaction.builder()
                .value(withdrawalAmount)
                .date(withdrawalDate)
                .build();

        StatementLine statementLine = new StatementLine(actualTransaction,amountOf(0));

        String expected = "| 10/01/2012 |          | 1000.00 | -1000.00 |";
        assertEquals(expected,statementLine.printTo());

    }
}