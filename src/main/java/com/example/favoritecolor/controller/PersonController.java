package com.example.favoritecolor.controller;

import com.example.favoritecolor.model.Person;
import com.example.favoritecolor.services.IPersonService;
import com.example.favoritecolor.services.PersonServiceH2;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value="/persons")
public class PersonController {
  @Qualifier("personServiceCSV")
  @Autowired
  private IPersonService service;


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

  @GetMapping(value="/", produces = {"application/json"})
  public ResponseEntity<ArrayList<Person>> getAllPersons(){
    try{
      ArrayList<Person> list= service.findAll();
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    catch (ObjectNotFoundException e){
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
