package com.rtcab.spock.introduction.example4

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.http.ResponseEntity
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

/**
 * Example 4:
 *
 * Integration Test for CustomerController to test API with complete Spring context
 *
 * - dependencies & mocking
 * - spring context
 * - JSON interaction
 *
 */

@SpringBootTest(webEnvironment = RANDOM_PORT)
class CustomerControllerIntegrationSpec extends Specification {


    @TestConfiguration
    static class MockConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        CustomerService customerService() {
            detachedMockFactory.Mock(CustomerService)
        }
    }

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


}
