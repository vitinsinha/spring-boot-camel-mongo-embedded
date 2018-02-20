package com.vitz.springbootcameldemo.processors;

import com.vitz.springbootcameldemo.models.Person;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vsinha on 2/20/2018.
 */

@Component
public class PersonAggregationStrategy implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (oldExchange == null) {
            return newExchange;
        }
        List<Person> firstResult = oldExchange.getIn().getBody(List.class);
        List<Person> secondResult = newExchange.getIn().getBody(List.class);
        oldExchange.getIn().setBody(Stream.concat(firstResult.stream(), secondResult.stream()).distinct().collect(Collectors.toList()));
        return oldExchange;
    }
}
