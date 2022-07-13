package com.example.favoritecolor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

@Entity
public class Person {

  @javax.persistence.Id
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String lastName;

  private int zipCode;

  private String city;

  @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
  private Color color;

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }
}
