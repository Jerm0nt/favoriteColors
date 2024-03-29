package com.example.favoritecolor.controller;

import com.example.favoritecolor.model.Person;
import com.example.favoritecolor.services.PersonService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value="/persons")
public class PersonController {
  private PersonService service;
  @Autowired
  public PersonController(PersonService service) {
    this.service = service;
  }

  @GetMapping(value="/{id}", produces = {"application/json"})
  public ResponseEntity<Person> getPerson(@PathVariable(value="id") Integer id){
    try{
      Person person = service.findPerson(id);
      return new ResponseEntity<>(person, HttpStatus.OK);
    }
    catch (ObjectNotFoundException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(produces = {"application/json"})
  public ResponseEntity<ArrayList<Person>> getAllPersons(){
    try{
      ArrayList<Person> list= service.findAll();
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    catch (IllegalArgumentException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping(value = "/color/{color}", produces = {"application/json"})
  public ResponseEntity<ArrayList<Person>> getPersonsByColor(@PathVariable(value = "color") String color) {
    try{
      ArrayList<Person> list = service.findPersonsByColor(color);
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    catch (IllegalArgumentException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
