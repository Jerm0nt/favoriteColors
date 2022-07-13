package com.example.favoritecolor.controller;

import com.example.favoritecolor.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/persons")
public class PersonController {

  @GetMapping(value="/{id}", produces = {"application/json"})
  public ResponseEntity<Person> getPerson(@PathVariable(value="id") Integer id){
    return null;
  }

  @GetMapping(value="/", produces = {"application/json"})
  public ResponseEntity<Person> getAllPersons(){
    return null;
  }

}
