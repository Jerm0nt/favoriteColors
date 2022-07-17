package com.example.favoritecolor.services;

import com.example.favoritecolor.model.Person;
import org.hibernate.ObjectNotFoundException;

import java.util.ArrayList;

public interface PersonService {

  ArrayList<Person> findAll() throws IllegalArgumentException;

  Person findPerson(Integer id) throws ObjectNotFoundException;

  ArrayList<Person> findPersonsByColor(String color_name) throws IllegalArgumentException;
}
