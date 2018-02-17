package com.ai.sin.currency.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Currency Rate Identifier
 */
public class CurrencyRateId implements Serializable {

    private String date;

    private String currency;

    public CurrencyRateId(){}

    public CurrencyRateId(String date, String currency) {
        this.date = date;
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyRateId that = (CurrencyRateId) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date, currency);
    }
}
