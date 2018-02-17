# sin-currency
currency exchange service

1. curl http://localhost:8080/rates/2017-01-01
2. curl http://localhost:8080/rates/2017-01-01/against/KWD/SGD
3. curl http://localhost:8080/rates/SGD/avg/2017-01-01/2017-01-02

# Unit Test

**/UT*.java for unit test cases <br/>
run by: `./mvnw clean test`

# Integration test
**/IT*.java for integration test cases
1. run by: `./mvnw clean integration test` 
2. run with IT only `./mvnw integration-test -DskipUTs=true` 

# JaCOCO for test coverage
after run unit test or integration test.
1. IT report - target/jacoco-it/index.html
2. UT report - target/jacoco-ut/index.html