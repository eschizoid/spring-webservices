package com.example.producingwebservice.client;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;
import io.spring.guides.gs_producing_web_service.ObjectFactory;
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