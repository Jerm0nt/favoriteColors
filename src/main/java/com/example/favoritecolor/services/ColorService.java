package com.example.favoritecolor.services;

import com.example.favoritecolor.model.Person;
import com.example.favoritecolor.repository.ColorRepository;
import com.example.favoritecolor.repository.PersonRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ColorService implements IColorService {
  @Autowired
  private PersonRepository personRepository;

  @Override
  public ArrayList<Person> findPersonsByColor(String color) throws IllegalArgumentException {
    ArrayList<Person> selection = new ArrayList<>();
    try{
      ArrayList<Person> personList = (ArrayList<Person>) personRepository.findAll();
      for (Person person:personList){
        if(color.equals(person.getColor())){
          selection.add(person);
        }
      }
      return selection;
    }catch(Exception e){
      throw new IllegalArgumentException("No Persons found with given Color!");
    }
  }
}
