package com.vitz.springbootcameldemo.processors;

import com.vitz.springbootcameldemo.models.Person;
import com.vitz.springbootcameldemo.models.Response;
import com.vitz.springbootcameldemo.repositories.PersonRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Processor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.vitz.springbootcameldemo.utils.Utility.createResponse;

/**
 * Created by vsinha on 2/19/2018.
 */

@Component
public class PersonProcessor implements Processor {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public void process(Exchange exchange) throws Exception {

        String firstName = (String) exchange.getIn().getHeader("firstName");
        String lastName = (String) exchange.getIn().getHeader("lastName");

        List<Person> people = null;

        if(StringUtils.isNotBlank(firstName)) {
            people =  personRepository.findByFirstName(firstName);
        } else if(StringUtils.isNotBlank(lastName)) {
            people = personRepository.findByLastName(lastName);
        } else {
            people = personRepository.findAll();
        }

        exchange.getIn().setBody(people);
    }

    public Response<Person> insertPerson(Exchange exchange) {
        Person person = personRepository.insert(exchange.getIn().getBody(Person.class));
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "201");
        return createResponse(person, "Successful creation", "201");
    }

    public Person getPerson(@Header("id") String id) {
        return personRepository.findOne(id);
    }

    public List<Person> getPeopleByFirstName(@Header("firstName") String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    public List<Person> getPeopleByLastName(@Header("lastName") String lastName) {
        return personRepository.findByLastName(lastName);
    }

}
