package com.example.favoritecolor.services;

import com.example.favoritecolor.model.Person;
import org.hibernate.ObjectNotFoundException;

import java.util.ArrayList;

public interface IColorService {

  ArrayList<Person> findPersonsByColor(String color) throws ObjectNotFoundException;
}
