package com.ai.sin.currency.controller;

import com.ai.sin.currency.model.CurrencyRate;
import com.ai.sin.currency.service.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Currency Rate Controller
 *
 * Controller layer
 */
@RestController
public class CurrencyRateController {

    private CurrencyRateService currencyRateService;

    @Autowired
    public CurrencyRateController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    /**
     * 1) Give a date, get all the exchange rate of all the currencies(wrt USD)
     * @param date - expected date format should be yyyy-MM-dd
     * @return all the currency of that date
     */
    @GetMapping("/rates/{date}")
    Set<CurrencyRate> getCurrencyRateByDate(@PathVariable String date) {
        return this.currencyRateService.getCurrencyRateByDate(date);
    }

    /**
     * 2) Give a date and 2 currencies, find the exchange rate between them
     * @param date a given date
     * @param currency1 currency one
     * @param currency2 currency two
     * @return double - rate between the two currency
     */
    @GetMapping("/rates/{date}/against/{currency1}/{currency2}")
    double getRateAgainst(@PathVariable String date, @PathVariable String currency1, @PathVariable String currency2) {
       return this.currencyRateService.getRateAgainst(date, currency1, currency2);
    }

    /**
     * 3) Give a date rang and give a currency, find the exchange between
     * @param startDate start date
     * @param endDate end date
     * @param currency a given currency
     * @return double - rate
     */
    @GetMapping("/rates/{currency}/avg/{startDate}/{endDate}")
    double getRateAvg(@PathVariable String currency, @PathVariable String startDate, @PathVariable String endDate) {
        return this.currencyRateService.getRateAvg(startDate, endDate, currency);
    }

}
