package com.ai.sin.currency.repo;

import com.ai.sin.currency.model.CurrencyRate;
import com.ai.sin.currency.model.CurrencyRateId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;
/**
 * Unit test for currency rate repository
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RepoConfiguration.class)
@TestPropertySource("classpath:test_application.properties")
@ActiveProfiles("dev")
public class UTCurrencyRateRepo {

    @Autowired
    private CurrencyRateRepo currencyRateRepo;

    @Before
    public void before(){
        currencyRateRepo.load();
    }

    /**
     * Two test file from classpath with 14 records
     */
    @Test
    public void testLoad() {
        Map<CurrencyRateId, CurrencyRate> all = currencyRateRepo.load();
        assertNotNull(all);
        assertFalse(all.isEmpty());
        assertEquals(all.size(), 14);
    }


    @Test
    public void testGetCurrencyRateByDate() {
        String date = "2017-01-01";
        Set<CurrencyRate> currencyRateSet = currencyRateRepo.getCurrencyRatesByDate(date);
        assertNotNull(currencyRateSet);
        assertFalse(currencyRateSet.isEmpty());
        assertEquals(currencyRateSet.size(), 7);
    }

    @Test
    public void testGetRateBetween() {
        String currency1 = "KWD";
        String currency2 = "SGD";
        String date = "2017-01-02";
        double expected = 4.77d;  // 3.34 / 0.70
        assertEquals(currencyRateRepo.getRateAgainst(date, currency1, currency2), expected, 0.01d);
    }

    @Test
    public void testGetRate() {
        String startDate = "2017-01-01";
        String endDate = "2017-01-02";
        String currency = "GBP";
        double expectedValue = 1.31d; // 1.32 + 1.30 / 2
        assertEquals(currencyRateRepo.getRateAvg(startDate, endDate, currency), expectedValue, 0.01d);
    }

}
