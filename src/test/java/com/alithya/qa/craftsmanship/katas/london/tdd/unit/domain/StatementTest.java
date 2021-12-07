package com.alithya.qa.craftsmanship.katas.london.tdd.unit.domain;

import com.alithya.qa.craftsmanship.katas.london.tdd.Statement;
import com.alithya.qa.craftsmanship.katas.london.tdd.StatementLine;
import com.alithya.qa.craftsmanship.katas.london.tdd.Transaction;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.alithya.qa.craftsmanship.katas.london.tdd.Amount.amountOf;
import static org.mockito.Mockito.verify;

public class StatementTest {

    @Mock
    PrintStream printer;
    @Mock
    StatementLine statementLine;

    private Statement statement;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        statement = new Statement();
    }

    @Test public void shouldPrintStatementHeader() {
        statement.printTo(printer);

        verify(printer).println(Statement.STATEMENT_HEADER);
    }

    @SneakyThrows
    @Test public void shouldPrintDeposit() {
        DateFormat DateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");

        Date depositDate = DateFormatter.parse("10/01/2012");

        statement.addLineContaining(Transaction
                        .builder()
                        .value(amountOf(1000))
                        .date(depositDate)
                        .build(),amountOf(1000));

        statement.printTo(printer);
        verify(printer).println(Statement.STATEMENT_HEADER);
        verify(printer).println("| 10/01/2012 | 1000.00 |          | 2000.00 |");
    }

    @SneakyThrows
    @Test public void shouldPrintWithdrawal() {
        DateFormat DateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");

        Date depositDate = DateFormatter.parse("10/01/2012");

        statement.addLineContaining(Transaction
                .builder()
                .value(amountOf(-1000))
                .date(depositDate)
                .build(), amountOf(0));

        statement.printTo(printer);
        verify(printer).println(Statement.STATEMENT_HEADER);
        verify(printer).println("| 10/01/2012 |          | 1000.00 | -1000.00 |");
    }

    @SneakyThrows
    @Test public void should_print_two_deposits_in_reverse_order() {
        DateFormat DateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");

        statement.addLineContaining(Transaction
                .builder()
                .value(amountOf(1000))
                .date(DateFormatter.parse("10/01/2012"))
                .build(), amountOf(0));
        statement.addLineContaining(Transaction
                .builder()
                        .value(amountOf(2000))
                        .date(DateFormatter.parse("13/01/2012"))
                        .build(),
                amountOf(1000));
        statement.addLineContaining(Transaction
                        .builder()
                        .value(amountOf(-500))
                        .date(DateFormatter.parse("14/01/2012"))
                        .build(),
                amountOf(3000));

        statement.printTo(printer);

        InOrder inOrder = Mockito.inOrder(printer);
        inOrder.verify(printer).println(Statement.STATEMENT_HEADER);
        //passes because there are no more interactions after last method:
        //inOrder.verifyNoMoreInteractions();
        inOrder.verify(printer).println("| 14/01/2012 |         | 500.00 | 2500.00 |");
        inOrder.verify(printer).println("| 13/01/2012 | 2000.00 |        | 3000.00 |");
        inOrder.verify(printer).println("| 10/01/2012 | 1000.00 |        | 1000.00 |");
        //inOrder.verify(printer).println("| 13/01/2012 | 2000.00 |          | 3000.00 |");

    }
}