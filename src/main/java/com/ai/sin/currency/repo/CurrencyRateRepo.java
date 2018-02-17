package com.ai.sin.currency.repo;

import com.ai.sin.currency.model.CurrencyRate;
import com.ai.sin.currency.model.CurrencyRateId;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Repository for currency rate
 *
 * Repository layer
 */
public interface CurrencyRateRepo {

    /**
     * Load all the data into cache
     * @return a map with key, currency
     */
    Map<CurrencyRateId, CurrencyRate> load();

    /**
     * 1) Give a date, get all the exchange rate of all the currencies(wrt USD)
     * @param date - expected date format should be yyyy-MM-dd
     * @return all the currency of that date
     */
    Set<CurrencyRate> getCurrencyRatesByDate(String date);

    /**
     * 2) Give a date and 2 currencies, find the exchange rate between them
     * @param date a given date
     * @param currency1 currency one
     * @param currency2 currency two
     * @return double - rate between the two currency
     */
    double getRateAgainst(String date, String currency1, String currency2) ;

    /**
     * 3) Give a date rang and give a currency, find the avg rates
     * @param startDate start date
     * @param endDate end date
     * @param currency a given currency
     * @return double - rate
     */
    double getRateAvg(String startDate, String endDate, String currency);


}
