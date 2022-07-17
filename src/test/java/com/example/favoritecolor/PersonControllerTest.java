package com.example.favoritecolor;

import com.example.favoritecolor.controller.PersonController;
import com.example.favoritecolor.model.Color;
import com.example.favoritecolor.model.Person;
import com.example.favoritecolor.services.PersonService;
import org.hamcrest.Matchers;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

  @MockBean
  private PersonService service;

  @Autowired
  private WebApplicationContext webApplicationContext;
  @Autowired
  private MockMvc mockMvc;

  private Person person1;
  private Person person2;
  private Person person3;

  private List<Person> personList;

  @BeforeEach
  void setUp() {
    person1 = new Person(1, "max", "mustermann", 88471, "laupheim", Color.BLAU);
    person2 = new Person(2, "maximiliane", "musterfrau", 12347, "berlin-neukölln", Color.ROT);
    person3 = new Person(3, "kim", "mustermensch", 13086, "berlin-weißensee", Color.TÜRKIS);
    personList = new ArrayList<>();
    personList.add(person1);
    personList.add(person2);
    personList.add(person3);
  }

  @Test
  void shouldCreateMockMvc() {
    assertNotNull(mockMvc);
  }

  @Test
  void shouldReturnListOfPersonsAllPersons() throws Exception {
    when(service.findAll()).thenReturn((ArrayList<Person>) personList);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(3)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(person2.getName()));
  }

  @Test
  void shouldReturnNotFoundHasEmptyDatabaseAllPersons() throws Exception {
    when(service.findAll()).thenThrow(IllegalArgumentException.class);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons"))
      .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void shouldReturnNotAcceptableHasAcceptTypeXmlAllPersons() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons").accept("application/xml"))
      .andExpect(MockMvcResultMatchers.status().isNotAcceptable());
  }

  @Test
  void shouldReturnPersonPersonById() throws Exception {
    when(service.findPerson(3)).thenReturn(person3);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons/3"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(person3.getName()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(person3.getLastName()))
      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(person3.getId()));
  }

  @Test
  void shouldReturnBadRequestHasStringAsIdPersonById() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons/string"))
      .andExpect(MockMvcResultMatchers.status().isBadRequest());
  }

  @Test
  void shouldReturnNotFoundHasNonExistingIdPersonById() throws Exception {
    when(service.findPerson(100)).thenThrow(ObjectNotFoundException.class);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons/100"))
      .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void shouldReturnNotAcceptableHasAcceptTypeXmlPersonById() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons/1").accept("application/xml"))
      .andExpect(MockMvcResultMatchers.status().isNotAcceptable());
  }

  @Test
  void shouldReturnNotFoundHasColorWhichNoPersonHasPersonByColor() throws Exception {
    when(service.findPersonsByColor("grün")).thenThrow(IllegalArgumentException.class);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons/color/grün"))
      .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void shouldReturnNotFoundHasColorWhichDoesntExistPersonByColor() throws Exception {
    when(service.findPersonsByColor("lilablassblau")).thenThrow(IllegalArgumentException.class);

    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons/color/lilablassblau"))
      .andExpect(MockMvcResultMatchers.status().isNotFound());
  }

  @Test
  void shouldReturnNotAcceptableHasAcceptTypeXmlPersonByColor() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/persons/color/grün").accept("application/xml"))
      .andExpect(MockMvcResultMatchers.status().isNotAcceptable());
  }
}
