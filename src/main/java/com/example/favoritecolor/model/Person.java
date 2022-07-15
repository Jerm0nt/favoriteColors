package com.example.favoritecolor.model;

import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
public class Person {
  public Person(int id, String name, String lastName, int zipCode, String city, Color color) {
    this.id = id;
    this.name = name;
    this.lastName = lastName;
    this.zipCode = zipCode;
    this.city = city;
    this.color = color;
  }

  public Person() {

  }

  @javax.persistence.Id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String lastName;

  private int zipCode;

  private String city;

  private Color color;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getZipCode() {
    return zipCode;
  }

  public void setZipCode(int zipCode) {
    this.zipCode = zipCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public String getColor() {
    return color.getName();
  }

  public void setColor(Color color) {
    this.color = color;
  }
}


