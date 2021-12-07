package com.alithya.qa.craftsmanship.katas.london.tdd.unit.domain;

import com.alithya.qa.craftsmanship.katas.london.tdd.Amount;
import com.alithya.qa.craftsmanship.katas.london.tdd.Transaction;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.alithya.qa.craftsmanship.katas.london.tdd.Amount.amountOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

public class TransactionTest {

    @Mock
    PrintStream printer;

    private Date getDate(String dateString) throws ParseException {
        DateFormat dateFormatter
                = new SimpleDateFormat("dd/MM/yyyy");

        return dateFormatter.parse(dateString);
    }


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void builderShouldBuildWholeTransaction() {
        ZoneId zonedId = ZoneId.of( "America/Montreal" );
        LocalDate today = LocalDate.now( zonedId );
        Transaction trx = Transaction
                .builder()
                .date(Date.from(today.atStartOfDay(zonedId).toInstant()))
                .value(amountOf(10))
                .build();

        assertEquals(trx.value.value ,amountOf(10).value,0);
        assertEquals(trx.date ,Date.from(today.atStartOfDay(zonedId).toInstant()));
    }

    @SneakyThrows
    @Test
    public void shouldPrintCreditTransaction(){

        Date depositDate = getDate("10/01/2012");
        Amount depositAmount = amountOf(1000);


        Transaction transaction = Transaction
                .builder()
                .date(depositDate)
                .value(depositAmount)
                .build();

        transaction.printTo(printer, amountOf(0));

        verify(printer).println("| 10/01/2012 | 1000.00 |          | 1000.00 |");
    }

    @SneakyThrows
    @Test
    public void shouldPrintDebitTransaction(){

        Date withdrawalDate = getDate("10/01/2012");
        Amount withdrawalAmount = amountOf(-500);


        Transaction transaction = Transaction
                .builder()
                .date(withdrawalDate)
                .value(withdrawalAmount)
                .build();

        transaction.printTo(printer, amountOf(600));

        verify(printer).println("| 10/01/2012 |          | 500.00 | 100.00 |");
    }

    @SneakyThrows
    @Test public void shouldCalculateCurrentBalanceAfterDeposit() {

        Transaction transaction = Transaction
                .builder()
                .date(getDate("10/01/2012"))
                .value(amountOf(1000))
                .build();

        Amount currentValue = transaction.balanceAfterTransaction(amountOf(100));
        assertThat(currentValue, is(amountOf(1100)));
    }

    @SneakyThrows
    @Test public void shouldCalculateCurrentBalanceAfterWithdrawal() {
        Transaction transaction = Transaction
                .builder()
                .date(getDate("10/01/2012"))
                .value(amountOf(-1000))
                .build();

        Amount currentValue = transaction.balanceAfterTransaction(amountOf(100));

        assertThat(currentValue, is(amountOf(-900)));
    }

    @SneakyThrows
    @Test public void shouldBeEqualToOtherTransactionWithSameValueAndDate() {
        Date depositDate = getDate("10/01/2012");
        Transaction depositOfOneHundred = Transaction
                .builder()
                .date(getDate("10/01/2012"))
                .value(amountOf(1000))
                .build();

        Transaction anotherDepositOfOneHundred = Transaction
                .builder()
                .date(getDate("10/01/2012"))
                .value(amountOf(1000))
                .build();


        assertThat(depositOfOneHundred, is(equalTo(anotherDepositOfOneHundred)));
    }
}