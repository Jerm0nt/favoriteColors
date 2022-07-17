package com.example.favoritecolor.services;

import com.example.favoritecolor.model.Color;
import com.example.favoritecolor.model.Person;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class PersonServiceCSV implements PersonService {

  private ArrayList<Person> persons;
  private String dataPath = "src/main/resources/sample-input.csv";

  public void setDataPath(String dataPath) {
    this.dataPath = dataPath;
  }
  @PostConstruct
  public void init() throws FileNotFoundException {
    persons = new ArrayList<>();
    try (CSVReader csvReader = new CSVReader(new FileReader(dataPath))){
      String[] values = null;
      int index = 1;
      while ((values = csvReader.readNext()) != null){
        if (values.length == 4){
          try {
            int plz = Integer.valueOf(values[2].substring(1,6));
            String city = values[2].substring(6).trim();
            persons.add(new Person(index, values[1], values[0], plz, city, Color.valueOfId(Integer.valueOf(values[3].trim()))));
          }catch(Exception e){
            System.out.print(e);
          }
        }
        index +=1;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (CsvValidationException e) {
      throw new RuntimeException(e);
    }
  }
  @Override
  public ArrayList<Person> findAll() throws IllegalArgumentException {
    if(persons.isEmpty()){
     throw new IllegalArgumentException("Person Collection must not be null!");
    }else {
      return persons;
    }
  }

  @Override
  public Person findPerson(Integer id) throws ObjectNotFoundException {
    if (!persons.isEmpty()) {
      for (Person person : persons) {
        if (person.getId() == id) {
          return person;
        }
      }
    }
    throw new ObjectNotFoundException(id, "User not found");
  }

  @Override
  public ArrayList<Person> findPersonsByColor(String color_name) throws IllegalArgumentException {
    ArrayList<Person> personsByColor = new ArrayList<>();
    try {
      for (Person person : persons) {
        if (person.getColor().equals(color_name)){
          personsByColor.add(person);
        }
      }
      if(personsByColor.isEmpty()){
        throw new IllegalArgumentException("User not found with color "+color_name);
      }else{
        return personsByColor;
      }
    } catch (Exception e){
      throw new IllegalArgumentException("No persons found!");
    }

  }
}
