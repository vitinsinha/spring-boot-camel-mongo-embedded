package com.vitz.springbootcameldemo.processors;

import com.vitz.springbootcameldemo.models.Person;
import com.vitz.springbootcameldemo.models.Response;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.vitz.springbootcameldemo.utils.Utility.createResponse;

/**
 * Created by vsinha on 2/19/2018.
 */

@Component
public class ExceptionProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        Exception exception = (Exception) exchange.getProperty(Exchange.EXCEPTION_CAUGHT);
        String code = null;
        if(exception instanceof DuplicateKeyException) {
            code = "409";
        } else {
            code = "500";
        }
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, code);
        exchange.getOut().setBody(createResponse(exception.getMessage(), "Exception Occurred", code));
    }
}