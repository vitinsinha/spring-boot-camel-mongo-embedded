package com.vitz.springbootcameldemo.routes;

import com.vitz.springbootcameldemo.processors.ExceptionProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

/**
 * Created by vsinha on 2/19/2018.
 */

@Component
public class ExceptionRouteBuilder extends RouteBuilder {

    @Autowired
    private ExceptionProcessor exceptionProcessor;

    @Override
    public void configure() throws Exception {

        onException(Exception.class)
                .handled(true)
                .id("exception-handler")
                .log("Exception: '${exception.stacktrace}'")
                .process(exceptionProcessor)
                .marshal()
                .json(JsonLibrary.Jackson);
    }
}