package com.example.producingwebservice.client;

import io.spring.spring_webservices.Country;
import io.spring.spring_webservices.GetCountryResponse;
import io.spring.spring_webservices.ObjectFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

@Component
public class WebServiceClient {

    private final ObjectFactory factory;
    private final WebServiceTemplate webServiceTemplate;

    public WebServiceClient(final WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
        this.factory = new ObjectFactory();
    }

    @SuppressWarnings("unchecked")
    public Country getCountry(final String name) {
        final var request = factory.createGetCountryRequest();
        request.setName(name);

        final var response = (GetCountryResponse) webServiceTemplate.marshalSendAndReceive(request);
        return response.getCountry();
    }
}