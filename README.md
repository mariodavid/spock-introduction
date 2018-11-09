[![license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Build Status](https://travis-ci.org/mariodavid/spock-introduction.svg?branch=master)](https://travis-ci.org/mariodavid/spock-introduction)

# Spock introduction

This is a example project which introduces you into the cool work of spock based testing.

The associated slides to this topic can be found here: https://speakerdeck.com/mariodavid/spock-introduction

### Example 1 - No dependencies, side effect free

[TotalAmountCalculatorSpec](https://github.com/mariodavid/spock-introduction/blob/master/src/test/groovy/com/rtcab/spock/introduction/example1/TotalAmountCalculatorSpec.groovy)

```
    def 'TotalAmountCalculator sums up the amount of orders amounts'() {
        given:
        def sut = new TotalAmountCalculator()

        and:
        def orders = [
                new Order(amount: 50),
                new Order(amount: 25),
                new Order(amount: 25),
        ]

        when:
        int totalAmount = sut.calculateAmount(orders)

        then:
        totalAmount == 100
    }
```

### Example 2 - data driven test, No dependencies, side effect free, exception handling in tests

[TopThreeCustomerFinderSpec](https://github.com/mariodavid/spock-introduction/blob/master/src/test/groovy/com/rtcab/spock/introduction/example2/TopThreeCustomerFinderSpec.groovy)

```
    @Unroll
    def "TopThreeCustomerFinder takes #expectedResult from #allCustomers"() {
        given:
        def sut = new TopThreeCustomerFinder()

        when:
        def topThreeCustomers = sut.findTopCustomers(allCustomers)

        then:
        topThreeCustomers == expectedResult

        where:
        allCustomers                || expectedResult
        [moe, edna, apu, poorMario] || [apu, edna, moe]
        [moe, edna, apu, milhouse]  || [milhouse, apu, edna]
        [moe, edna, apu]            || [apu, edna, moe]
        [moe, edna]                 || [edna, moe]

    }
```


### Example 3 - dependencies & mocking, side effect free

[CustomerSpec](https://github.com/mariodavid/spock-introduction/blob/master/src/test/groovy/com/rtcab/spock/introduction/example3/CustomerSpec.groovy)

```
    def "placeOrder calls the customerService for doing the heavy lifting"() {

        given:
        def orderPlacementService = Mock(OrderPlacementService)

        and:
        Customer sut = new Customer(customerService: orderPlacementService)
        Order order = new Order()

        when:
        sut.placeOrder(order)

        then:
        1 * orderPlacementService.placeOrder(sut, order)
    }
```


### Example 4 - Integration test, Spring context, JSON API interaction

[CustomerControllerIntegrationSpec](https://github.com/mariodavid/spock-introduction/blob/master/src/test/groovy/com/rtcab/spock/introduction/example4/CustomerControllerIntegrationSpec.groovy)

```
    @Autowired
    TestRestTemplate restTemplate

    @Autowired
    CustomerService customerService


    def "GET customers returns mockedMoe customer"() {

        given:
        customerService.getCustomer("123") >> new Customer(name: "Elizabeth Hoover")

        when:
        ResponseEntity<Customer> actualCustomer = restTemplate.getForEntity('/customers/123', Customer)

        then:
        actualCustomer.body.name == "Elizabeth Hoover"
    }
```




## execute the tests

This project is a regular Spring boot application, which uses Gradle as a build system.

To run the tests via Gradle, you can run the following command:

`./gradlew check`




## Spocks failing test output

One of the things that are pretty neat when working with Spock is the fact, that when an assertion does not match, 
a fairly easy to use output will be created. Here is an example of a failing test (I changed the expected name of example 4 to "Hans Maulwurf").


```
Condition not satisfied:

actualCustomer.body.name == "Hans Maulwurf"
|              |    |    |
|              |    |    false
|              |    |    15 differences (6% similarity)
|              |    |    (Eliz)a(beth) (Hoove-)r(-)
|              |    |    (H---)a(ns--) (Maulwu)r(f)
|              |    Elizabeth Hoover
|              Elizabeth Hoover (null)
<200 OK,Elizabeth Hoover (null),{Date=[Wed, 21 Mar 2018 07:32:59 GMT], Content-Type=[application/json;charset=utf-8], Transfer-Encoding=[chunked]}>

Expected :Hans Maulwurf

Actual   :Elizabeth Hoover

 <Click to see difference>


	at com.rtcab.spock.introduction.example4.CustomerControllerIntegrationSpec.GET customers returns Elizabeth Hoover as a JSON based customer(CustomerControllerIntegrationSpec.groovy:56)
```


## Given-When-Then phases in spock
The general overview of the phases in spock look like this:

![Given-When-Then explained](http://spockframework.org/spock/docs/1.1/images/Blocks2Phases.png)



## Spock vs. JUnit

With the release of JUnit 5, JUnit implemented a lot of the topics Spock covers. There is a detailed
description on this topic here: http://bmuschko.com/blog/junit5-vs-spock-showdown/

The bottom line is that feature-wise the frameworks become pretty close. The remaining differences are:

* integrated Mocking facilities
* Groovy (and the associated "ease of readability")
* Given-When-Then syntax



## Spock documentation

The official spock documentation is pretty comprehensive: http://spockframework.org/spock/docs


## Credits
The original copy of the Spock + Gradle + Spring Boot was used from [int128/spock-spring-boot-example](https://github.com/int128/spock-spring-boot-example)

<img src="https://raw.githubusercontent.com/cuba-platform/cuba-petclinic/master/img/cuba-petclinic-overview.gif" alt="alt text" width="552" height="351">
