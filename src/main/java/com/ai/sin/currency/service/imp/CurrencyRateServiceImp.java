package com.ai.sin.currency.service.imp;

import com.ai.sin.currency.model.CurrencyRate;
import com.ai.sin.currency.repo.CurrencyRateRepo;
import com.ai.sin.currency.service.CurrencyRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * CurrencyRateService implementation
 */
@Service
public class CurrencyRateServiceImp implements CurrencyRateService {

    private CurrencyRateRepo currencyRateRepo;

    @Autowired
    public CurrencyRateServiceImp(CurrencyRateRepo currencyRateRepo) {
        this.currencyRateRepo = currencyRateRepo;
    }

    @Override
    public Set<CurrencyRate> getCurrencyRateByDate(String date) {
        return this.currencyRateRepo.getCurrencyRatesByDate(date);
    }

    @Override
    public double getRateAvg(String date, String currency1, String currency2) {
        return this.currencyRateRepo.getRateAvg(date, currency1, currency2);
    }

    @Override
    public double getRateAgainst(String startDate, String endDate, String currency) {
        return this.currencyRateRepo.getRateAgainst(startDate, endDate, currency);
    }

}
