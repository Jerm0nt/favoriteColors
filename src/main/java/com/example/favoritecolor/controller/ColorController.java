package com.example.favoritecolor.controller;

import com.example.favoritecolor.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/persons/color")
public class ColorController {

  @GetMapping(value = "/{color}", produces = {"application/json"})
  public ResponseEntity<Person> getColor(@PathVariable(value = "color") String color) {
    return null;
  }

}
