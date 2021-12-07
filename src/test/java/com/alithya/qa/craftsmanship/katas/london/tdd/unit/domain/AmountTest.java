package com.alithya.qa.craftsmanship.katas.london.tdd.unit.domain;

import com.alithya.qa.craftsmanship.katas.london.tdd.Amount;
import org.junit.Test;

import static com.alithya.qa.craftsmanship.katas.london.tdd.Amount.amountOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class AmountTest {


    @Test public void shouldReturnMoneyRepresentation() {
        Amount oneThousand = amountOf(1000);

        assertThat("1000.00", is(equalTo(oneThousand.moneyRepresentation())));
    }

    @Test
    public void shouldReturnTheNegativeValue() {
        Amount five = amountOf(5);
        assertEquals(-5,five.negative().value,0);
    }

    @Test
    public void shouldReturnTheAbsoluteValue() {
        Amount five = amountOf(-5);
        assertEquals(5,five.abs().value,0);
    }

    @Test public void
    shouldIndicateWhenItIsNotGreaterThanOtherVmount() {
        Amount ten = amountOf(10);
        Amount five = amountOf(5);

        assertThat(five.isGreaterThanOrEqual(ten), is(false));
    }

    @Test public void
    shouldIndicateWhenItIsGreaterThanOtherAmount() {
        Amount ten = amountOf(10);
        Amount five = amountOf(5);

        assertThat(ten.isGreaterThanOrEqual(five), is(true));
    }

    @Test public void
    shouldSumAmounts() {
        Amount ten = amountOf(10);
        Amount five = amountOf(5);
        Amount fifteen = amountOf(15);

        assertEquals(fifteen.value, ten.thenAdd(five).value,0);
    }
}