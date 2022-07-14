package com.example.favoritecolor.controller;

import com.example.favoritecolor.model.Person;
import com.example.favoritecolor.services.IColorService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/persons/color")
public class ColorController {

  @Autowired
  private IColorService service;

  @GetMapping(value = "/{color}", produces = {"application/json"})
  public ResponseEntity<ArrayList<Person>> getColor(@PathVariable(value = "color") String color) {
    try{
      ArrayList<Person> list = service.findPersonsByColor(color);
      return new ResponseEntity<>(list, HttpStatus.OK);
    }
    catch (IllegalArgumentException e){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
