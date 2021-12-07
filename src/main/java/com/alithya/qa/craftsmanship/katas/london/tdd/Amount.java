package com.alithya.qa.craftsmanship.katas.london.tdd;

import java.text.DecimalFormat;

public class Amount {
    public final float value;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public Amount(float value) {
        this.value = value;
    }

    public static Amount amountOf(float value) {
        return new Amount(value);
    }

    public String moneyRepresentation() {
        return decimalFormat.format(value);
    }

    public Amount negative() {
        return amountOf(-value);
    }

    public Amount abs() {
        return amountOf(Math.abs(value));
    }

    public boolean isGreaterThanOrEqual(Amount amount) {
        return this.value >= amount.value;
    }

    public Amount thenAdd(Amount amount) {
        return amountOf(this.value + amount.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Amount)) {
            return false;
        }
        Amount other = (Amount) obj;
        return value == other.value;
    }
}
