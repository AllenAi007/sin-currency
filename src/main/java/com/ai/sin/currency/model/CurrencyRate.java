package com.ai.sin.currency.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;

/**
 * Currency Rate POJO
 * Default against USD
 */
public class CurrencyRate extends CurrencyRateId{

    private double rate;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

}
