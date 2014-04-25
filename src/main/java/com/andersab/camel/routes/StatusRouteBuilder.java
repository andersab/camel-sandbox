package com.andersab.camel.routes;

import com.andersab.camel.processor.StatusProcessor;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class StatusRouteBuilder extends RouteBuilder {
    private Processor statusProcessor;

    @Override
    public void configure() throws Exception {
        statusProcessor = new StatusProcessor();
        from("jetty:http://localhost:8080").process(statusProcessor);
    }
}
