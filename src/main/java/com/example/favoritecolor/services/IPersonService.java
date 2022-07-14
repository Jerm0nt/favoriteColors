package com.example.favoritecolor.services;

import com.example.favoritecolor.model.Person;
import org.hibernate.ObjectNotFoundException;

import java.util.ArrayList;

public interface IPersonService {

  ArrayList<Person> findAll() throws IllegalArgumentException;

  Person findPerson(Integer id) throws ObjectNotFoundException;
}
