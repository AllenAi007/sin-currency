package com.ai.sin.currency.service;

import com.ai.sin.currency.model.CurrencyRate;
import com.ai.sin.currency.repo.CurrencyRateRepo;
import com.ai.sin.currency.repo.RepoConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

import static org.junit.Assert.*;

/**
 * Unit test for currency rate repository
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfiguration.class, RepoConfiguration.class})
@TestPropertySource("classpath:test_application.properties")
@ActiveProfiles("dev")
public class UTCurrencyRateService {

    @Autowired
    private CurrencyRateService currencyRateService;

    @Autowired
    private CurrencyRateRepo currencyRateRepo;

    @Before
    public void before(){
        currencyRateRepo.load();
    }

    @Test
    public void testGetCurrencyRateByDate() {
        String date = "2017-01-02";
        Set<CurrencyRate> currencyRateSet = currencyRateService.getCurrencyRateByDate(date);
        assertNotNull(currencyRateSet);
        assertFalse(currencyRateSet.isEmpty());
        assertEquals(currencyRateSet.size(), 7);
    }

    @Test
    public void testGetRateAgainst() {
        String currency1 = "KWD";
        String currency2 = "SGD";
        String date = "2017-01-01";
        double expected = 4.49d;  // 3.32 / 0.74
        assertEquals(currencyRateService.getRateAgainst(date, currency1, currency2), expected, 0.01d);
    }

    @Test
    public void testGetRateAvg() {
        String startDate = "2017-01-01";
        String endDate = "2017-01-02";
        String currency = "SGD";
        double expectedValue = 0.72d; // 0.74 + 0.70 / 2
        assertEquals(currencyRateService.getRateAvg(startDate, endDate, currency), expectedValue, 0.01d);
    }

}
