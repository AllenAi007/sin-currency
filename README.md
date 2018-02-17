# sin-currency currency rate service
## 1. how To run
`./mvnw clean spring-boot:run`
## 2 To test, after start application, use curl or copy the url to into browser
1. `curl http://localhost:8080/rates/2017-01-01 `
{"header":{"success":true,"authToken":null},"payload":[{"date":"2017-01-01","currency":"GBP","rate":1.3},{"date":"2017-01-01","currency":"OMR","rate":2.6},{"date":"2017-01-01","currency":"KWD","rate":3.32},{"date":"2017-01-01","currency":"BHD","rate":2.65},{"date":"2017-01-01","currency":"EUR","rate":1.19},{"date":"2017-01-01","currency":"CHF","rate":1.04},{"date":"2017-01-01","currency":"SGD","rate":0.74}],"error":null}
2. `curl http://localhost:8080/rates/2017-01-01/against/KWD/SGD` 
{"header":{"success":true,"authToken":null},"payload":4.486486486486486,"error":null}
3. `curl http://localhost:8080/rates/SGD/avg/2017-01-01/2017-01-02` 
{"header":{"success":true,"authToken":null},"payload":0.72,"error":null}
4. `curl http://localhost:8080/rates/2017-Dec-01` 
{"header":{"success":false,"authToken":""},"payload":null,"error":{"errorCode":"INTERNAL_SERVER_ERROR","errorType":"com.ai.sin.currency.exception.InvalidDateFormatException","errorMessage":"The date 2017-Dec-01 is not expected date format as yyyy-MM-dd"}}


## Unit Test - UT

`**/UT*.java` for unit test cases 
run by: `./mvnw clean test`

## Integration Test - IT
`**/IT*.java` for integration test cases
1. run by: `./mvnw clean integration test` 
2. run with IT only `./mvnw integration-test -DskipUTs=true` 

## JaCOCO - Test Coverage 
1. After run unit test or integration test. Current coverage is **`80%`**
2. IT report - target/jacoco-it/index.html
3. UT report - target/jacoco-ut/index.html

## Performance Testing - PT
1. Pre Set UP, Java 8 or later.
2. Download and install Apache JMeter 4.0
3. Run Performance testing:
    1. `cd sin-currency`
    2. `rm -rf src/test/pt/output/*`
    3. `$JMETER_HOME/bin/jmeter.sh -n -t src/test/pt/jemeter/sin-currency-pt.jmx -l src/test/pt/sin-currency-pt.log -e -o src/test/pt/output/` 
4. Check the output report `src/test/pt/output/index.html`
