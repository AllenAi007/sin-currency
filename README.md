# sin-currency currency rate service
## 1. To run
./mvnw clean spring-boot:run
## 2 To test, after start application, use curl or copy below url to browser to test
1. curl http://localhost:8080/rates/2017-01-01 <br/>
ouput: <br/>
{"header":{"success":true,"authToken":null},"payload":[{"date":"2017-01-01","currency":"GBP","rate":1.3},{"date":"2017-01-01","currency":"OMR","rate":2.6},{"date":"2017-01-01","currency":"KWD","rate":3.32},{"date":"2017-01-01","currency":"BHD","rate":2.65},{"date":"2017-01-01","currency":"EUR","rate":1.19},{"date":"2017-01-01","currency":"CHF","rate":1.04},{"date":"2017-01-01","currency":"SGD","rate":0.74}],"error":null}
2. curl http://localhost:8080/rates/2017-01-01/against/KWD/SGD <br/>
{"header":{"success":true,"authToken":null},"payload":4.486486486486486,"error":null}
3. curl http://localhost:8080/rates/SGD/avg/2017-01-01/2017-01-02 <br/>
{"header":{"success":true,"authToken":null},"payload":0.72,"error":null}
4. curl http://localhost:8080/rates/2017-Dec-01 <br/>
{"header":{"success":false,"authToken":""},"payload":null,"error":{"errorCode":"INTERNAL_SERVER_ERROR","errorType":"com.ai.sin.currency.exception.InvalidDateFormatException","errorMessage":"The date 2017-Dec-01 is not expected date format as yyyy-MM-dd"}}


# Unit Test

**/UT*.java for unit test cases <br/>
run by: `./mvnw clean test`

# Integration test
**/IT*.java for integration test cases
1. run by: `./mvnw clean integration test` 
2. run with IT only `./mvnw integration-test -DskipUTs=true` 

# JaCOCO for test coverage
1. After run unit test or integration test. Current coverage is **`80%`**
1. IT report - target/jacoco-it/index.html
2. UT report - target/jacoco-ut/index.html