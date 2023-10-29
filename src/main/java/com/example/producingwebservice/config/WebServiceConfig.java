package com.example.producingwebservice.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
//import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(final ApplicationContext applicationContext) {
        final var servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    //@Bean(name = "countries")
    //public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema countriesSchema) {
    //	DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    //	wsdl11Definition.setPortTypeName("CountriesPort");
    //	wsdl11Definition.setLocationUri("/ws");
    //	wsdl11Definition.setTargetNamespace("http://spring.io/spring-webservices");
    //	wsdl11Definition.setSchema(countriesSchema);
    //	return wsdl11Definition;
    //}

    @Bean(name = "countries")
    public SimpleWsdl11Definition defaultWsdl11Definition() {
        final var wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("countries.wsdl"));
        return wsdl11Definition;
    }


    @Bean
    public XsdSchema countriesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("countries.xsd"));
    }
}
