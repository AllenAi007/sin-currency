package com.ai.sin.currency.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ITCurrencyRateController {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testGetCurrencyRateByDate() throws Exception {
        String date = "2017-01-01";
        String expectedJson = "{\"header\":{\"success\":true,\"authToken\":null},\"payload\":[{\"date\":\"2017-01-01\",\"currency\":\"GBP\",\"rate\":1.3},{\"date\":\"2017-01-01\",\"currency\":\"OMR\",\"rate\":2.6},{\"date\":\"2017-01-01\",\"currency\":\"KWD\",\"rate\":3.32},{\"date\":\"2017-01-01\",\"currency\":\"BHD\",\"rate\":2.65},{\"date\":\"2017-01-01\",\"currency\":\"EUR\",\"rate\":1.19},{\"date\":\"2017-01-01\",\"currency\":\"CHF\",\"rate\":1.04},{\"date\":\"2017-01-01\",\"currency\":\"SGD\",\"rate\":0.74}],\"error\":null}";
        String body = this.testRestTemplate.getForEntity("/rates/" + date, String.class).getBody();
        assertThat(body).isEqualTo(expectedJson);
    }

    @Test
    public void testGetRateAgainst() throws Exception {
        String expectedJson = "{\"header\":{\"success\":true,\"authToken\":null},\"payload\":4.486486486486486,\"error\":null}";
        //http://localhost:8080/rates/2017-01-01/against/KWD/SGD
        String body = this.testRestTemplate.getForEntity("/rates/2017-01-01/against/KWD/SGD", String.class).getBody();
        assertThat(body).isEqualTo(expectedJson);
    }

    @Test
    public void testGetRateAvg() throws Exception {
        String expectedJson = "{\"header\":{\"success\":true,\"authToken\":null},\"payload\":0.72,\"error\":null}";
        // http://localhost:8080/rates/SGD/avg/2017-01-01/2017-01-02
        String body = this.testRestTemplate.getForEntity("/rates/SGD/avg/2017-01-01/2017-01-02", String.class).getBody();
        assertThat(body).isEqualTo(expectedJson);
    }

    @Test
    public void testGetRateByDateWithDateFormatError() throws Exception {
        String expectedJson = "{\"header\":{\"success\":false,\"authToken\":\"\"},\"payload\":null,\"error\":{\"errorCode\":\"INTERNAL_SERVER_ERROR\",\"errorType\":\"com.ai.sin.currency.exception.InvalidDateFormatException\",\"errorMessage\":\"The date 2017-Dec-01 is not expected date format as yyyy-MM-dd\"}}";
        // http://localhost:8080/rates/2017-Dec-01
        String body = this.testRestTemplate.getForEntity("/rates/2017-Dec-01", String.class).getBody();
        assertThat(body).isEqualTo(expectedJson);
    }

    @Test
    public void testGetRateByDateWithNoRateFoundError() throws Exception {
        String expectedJson = "{\"header\":{\"success\":false,\"authToken\":\"\"},\"payload\":null,\"error\":{\"errorCode\":\"INTERNAL_SERVER_ERROR\",\"errorType\":\"com.ai.sin.currency.exception.NoRateFoundException\",\"errorMessage\":\"No rate found for date 2019-01-01\"}}";
        // http://localhost:8080/rates/2019-01-01
        String body = this.testRestTemplate.getForEntity("/rates/2019-01-01", String.class).getBody();
        assertThat(body).isEqualTo(expectedJson);
    }

}
