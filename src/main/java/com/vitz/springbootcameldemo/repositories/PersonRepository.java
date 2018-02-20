package com.vitz.springbootcameldemo.repositories;

import com.vitz.springbootcameldemo.models.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by vsinha on 2/19/2018.
 */
public interface PersonRepository extends MongoRepository<Person, String> {

    public List<Person> findByFirstName(String firstName);
    public List<Person> findByLastName(String lastName);
    public List<Person> findAll();
}
