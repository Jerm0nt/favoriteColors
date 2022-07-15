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
public class PersonServiceCSV implements IPersonService{

  private ArrayList<Person> persons;

  @PostConstruct
  public void init() throws FileNotFoundException {
    persons = new ArrayList<>();
    //File file = new File(getClass().getResource("sample-input.csv").getFile());
    try (CSVReader csvReader = new CSVReader(new FileReader("src/main/resources/sample-input.csv"))){
      String[] values = null;
      int index = 1;
      while ((values = csvReader.readNext()) != null){
        if (values.length == 4){
          try {
            int plz = Integer.valueOf(values[2].substring(1,6));
            String city = values[2].substring(6).trim();
            persons.add(new Person(index, values[0], values[1], plz, city, Color.valueOfId(Integer.valueOf(values[3].trim()))));
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
    return persons;
  }

  @Override
  public Person findPerson(Integer id) throws ObjectNotFoundException {
    for (Person person:persons) {
      if (person.getId()==id){
        return person;
      }
    }
    throw new ObjectNotFoundException(id, "Person konnte nicht gefunden werden!");
  }

  @Override
  public ArrayList<Person> findPersonsByColor(String color_name) throws IllegalArgumentException {
    ArrayList<Person> personsByColor = new ArrayList<>();
    for (Person person : persons) {
      if (person.getColor().equals(color_name)){
        personsByColor.add(person);
      }
    }
    if(personsByColor.isEmpty()){
      throw new IllegalArgumentException("Keine Personen mit dieser Farbe!");
    }else{
      return personsByColor;
    }
  }
}
