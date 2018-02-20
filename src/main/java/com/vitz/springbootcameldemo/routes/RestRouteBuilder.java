package com.vitz.springbootcameldemo.routes;

import com.vitz.springbootcameldemo.models.Person;
import com.vitz.springbootcameldemo.processors.PersonAggregationStrategy;
import com.vitz.springbootcameldemo.processors.PersonProcessor;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by vsinha on 2/19/2018.
 */

@Component
public class RestRouteBuilder extends ExceptionRouteBuilder {

    @Value("${server.port}")
    String serverPort;

    @Value("${api.path}")
    String contextPath;

    @Autowired
    private PersonProcessor personProcessor;

    @Autowired
    private PersonAggregationStrategy personAggregationStrategy;

    @Override
    public void configure() throws Exception {
        super.configure();

        // http://localhost:8080/camel/api-doc
        restConfiguration().contextPath(contextPath)
                .port(serverPort)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "REST API")
                .apiProperty("api.version", "v1")
                .apiProperty("cors", "true") // cross-site
                .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");

        rest("/person").id("rest-person")
                // "/person" or "/person?firstName=First" or "/person?lastName=Last"
                .get("").id("rest-person-get").consumes("application/json").produces("application/json").to("direct:getAllPerson")
                // "/person/id"
                .get("/{id}").id("rest-person-get-id").consumes("application/json").produces("application/json").to("direct:getSinglePerson")
                .post("").id("rest-person-post").consumes("application/json").produces("application/json").type(Person.class).to("direct:postPerson");

        from("direct:getAllPerson")
                .id("direct-getAllPerson")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .process(personProcessor);

        from("direct:getSinglePerson")
                .id("direct-getSinglePerson")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .bean(personProcessor, "getPerson");

        from("direct:postPerson")
                .id("direct-postPerson")
                .to("log:DEBUG?showBody=true&showHeaders=true")
                .bean(personProcessor, "insertPerson");

//      --------------------------------------------------
//        Usage of multicast, seda & aggregation example
//      --------------------------------------------------

        rest("/person/name")
                // "/person/name?firstName=First&lastName=Last"
                .get("").id("rest-person-name").consumes("application/json").produces("application/json").to("direct:getFirstAndLastNames");

        from("direct:getFirstAndLastNames")
                .multicast()
                .aggregationStrategy(personAggregationStrategy)
                .parallelProcessing()
                .streaming()
                .to("seda:getPeopleByFirstName", "seda:getPeopleByLastName");

        from("seda:getPeopleByFirstName")
                .bean(personProcessor, "getPeopleByFirstName");

        from("seda:getPeopleByLastName")
                .bean(personProcessor, "getPeopleByLastName");
    }
}
