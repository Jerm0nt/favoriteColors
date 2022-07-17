package com.example.favoritecolor.services;

import com.example.favoritecolor.model.Person;
import com.example.favoritecolor.repository.PersonRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PersonServiceH2 implements PersonService {

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
    catch(Exception e){throw new ObjectNotFoundException(id, "User not found");}
  }

  @Override
  public ArrayList<Person> findPersonsByColor(String color_name) throws IllegalArgumentException {
    ArrayList<Person> personsByColor = new ArrayList<>();
    try{
      ArrayList<Person> allPersons = (ArrayList<Person>) repository.findAll();
      for (Person person:allPersons) {
        if(person.getColor().equals(color_name)){
          personsByColor.add(person);
        }
      }
      if (personsByColor.isEmpty()){
        throw new IllegalArgumentException("User not found with color "+color_name);
      }else{
        return personsByColor;
      }
    }catch (Exception e){
      throw new IllegalArgumentException("No persons found!");
    }
  }
}
