package com.example.apirest.restapi.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.apirest.restapi.entity.Person;
import com.example.apirest.restapi.repository.PersonRepository;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository _personRepository;


    @RequestMapping(value= "/person", method = RequestMethod.GET)
    public List<Person> Get() {
        return _personRepository.findAll();
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> GetById(@PathVariable(value = "id") long id) {
        Optional<Person> person = _personRepository.findById(id);

        if (person.isPresent()) {
            return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public Person Post(@Valid @RequestBody Person person) {
        return _personRepository.save(person);
    }

    @RequestMapping(value = "/person/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Person> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Person newPessoa) {

        Optional<Person> oldPerson = _personRepository.findById(id);
        if(oldPerson.isPresent()) {
            Person person = oldPerson.get();
            person.setName(newPessoa.getName());
            _personRepository.save(person);
            return new ResponseEntity<Person>(person, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id) {
        Optional<Person> person = _personRepository.findById(id);

        if(person.isPresent()) {
            _personRepository.delete(person.get());

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
