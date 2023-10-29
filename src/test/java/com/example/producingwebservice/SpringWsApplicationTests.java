package com.example.producingwebservice;

import com.example.producingwebservice.client.WebServiceClient;
import io.spring.spring_webservices.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class SpringWsApplicationTests {

    @Autowired
    private WebServiceClient webServiceClient;

    @Test
    public void testListFlights() {
        final var country = webServiceClient.getCountry("Spain");
        assertThat(country.getCapital()).isEqualTo("Madrid");
        assertThat(country.getCurrency()).isEqualTo(Currency.PESETAS);
        assertThat(country.getPopulation()).isEqualTo(46704314);
    }
}