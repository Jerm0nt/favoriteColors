package com.example.favoritecolor.services;

import com.example.favoritecolor.model.Person;
import com.example.favoritecolor.repository.PersonRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonServiceH2 implements IPersonService{

  @Autowired
  private PersonRepository repository;

  @Override
  public ArrayList<Person> findAll() throws IllegalArgumentException {
    try{return (ArrayList<Person>) repository.findAll();}
    catch (Exception e){throw new IllegalArgumentException("Person Collection must not be null!");}
  }

  @Override
  public Person findPerson(Integer id) throws ObjectNotFoundException {
    try{return repository.findById(id).get();}
    catch(Exception e){throw new ObjectNotFoundException(id, "User mit ID "+id+" nicht gefunden");}
  }

  @Override
  public ArrayList<Person> findPersonsByColor(String color_name) throws IllegalArgumentException {
    return null;
  }
}
