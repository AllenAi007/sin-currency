package com.ai.sin.currency.repo.imp;

import com.ai.sin.currency.exception.NoRateFoundException;
import com.ai.sin.currency.model.CurrencyRate;
import com.ai.sin.currency.model.CurrencyRateId;
import com.ai.sin.currency.repo.CurrencyRateRepo;
import com.ai.sin.currency.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of CurrencyRate Repo
 */
@Component
public class CurrencyRateRepoImp implements CurrencyRateRepo {

    private static Logger LOG = LoggerFactory.getLogger(CurrencyRateRepoImp.class);

    /**
     * where the rates file are located
     */
    @Value("${rate.inbound}")
    private String rateFolder ;

    @Value("${rate.date.format}")
    private String dateFormat;

    @Autowired
    private ResourceLoader resourceLoader;


    /**
     * Data source, could be changed into
     */
    private Map<CurrencyRateId, CurrencyRate> currencyRates;

    public CurrencyRateRepoImp(){
        this.currencyRates = new ConcurrentHashMap<>();
    }

    /**
     * Load the currency on start up
     */
    @PostConstruct
    public void onStartUp() {
        LOG.info("Application starting, loading currency rates from {}", this.rateFolder);
        Map<CurrencyRateId, CurrencyRate> rates = this.load();
        LOG.info("Currency Rates load finished, total {} rates loaded into memory!", rates.size());
    }

    @Override
    public Map<CurrencyRateId, CurrencyRate> load() {
        try {
          Arrays.stream(ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(rateFolder))
            .forEach(r -> {
                BufferedReader bufferedReader = null;
                String date = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(r.getFile()));
                    date = Utils.getDateFromFileName(r.getFilename());
                    if(LOG.isDebugEnabled()) {
                        LOG.debug("Reading file {} for date {}", r.getFile(), date);
                    }
                    String line;
                    while((line = bufferedReader.readLine()) != null) {
                        String fields[] = line.split(" ");
                        CurrencyRateId id = new CurrencyRateId();
                        id.setDate(date);
                        id.setCurrency(fields[1]);
                        CurrencyRate currencyRate = new CurrencyRate();
                        currencyRate.setDate(date);
                        currencyRate.setCurrency(fields[1]);
                        currencyRate.setRate(Double.parseDouble(fields[4]));
                        currencyRates.put(id, currencyRate);
                    }
                    if(LOG.isDebugEnabled()) {
                        LOG.debug("Finish in reading file for date {}", date);
                    }
                }catch(Exception e) {
                    LOG.error("error caused during reading file " + date, e);
                } finally {
                    if(bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e) {
                            LOG.warn("error caused during close buffer reader", e);
                        }
                    }
                }
            });
        } catch (IOException e) {
            LOG.error("error caused during reading file", e);
        }
        return currencyRates;
    }

    @Override
    public Set<CurrencyRate> getCurrencyRatesByDate(String date) {
        Set<CurrencyRate> result = new HashSet<>();
        currencyRates.forEach((k, v) -> {
            if(k.getDate().equals(date)) {
                result.add(v);
            }
        });
        return result;
    }

    @Override
    public double getRateAgainst(String date, String currency1, String currency2) {
        CurrencyRate rate1 = this.currencyRates.get(new CurrencyRateId(date, currency1));
        if(rate1 == null) {
            throw new NoRateFoundException("No rate found for " + currency1 + " on "+ date);
        }
        CurrencyRate rate2 = this.currencyRates.get(new CurrencyRateId(date, currency2));
        if(rate2 == null) {
            throw new NoRateFoundException("No rate found for " + currency2 + " on "+ date);
        }
        BigDecimal bigDecimal = new BigDecimal(rate1.getRate()/rate2.getRate());
        bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    @Override
    public double getRateAvg(String startDate, String endDate, String currency) {
        Date start = Utils.parseDate(startDate, this.dateFormat);
        Date end = Utils.parseDate(endDate, this.dateFormat);
        double rates = 0;
        int rateCount = 0;
        for(CurrencyRateId id : currencyRates.keySet()) {
            Date date = Utils.parseDate(id.getDate(), this.dateFormat);
            if(!id.getCurrency().equals(currency)) continue;
            if(Utils.isDateBetween(date, start, end)) {
                rates += currencyRates.get(id).getRate();
                rateCount ++;
            }
        }
        if(rateCount == 0) {
            throw new NoRateFoundException("No any rate found between " + startDate + " and " + endDate);
        }
        BigDecimal bigDecimal = new BigDecimal(rates/rateCount);
        bigDecimal.setScale(2, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

}
