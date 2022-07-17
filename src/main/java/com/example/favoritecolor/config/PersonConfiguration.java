package com.example.favoritecolor.config;

import com.example.favoritecolor.controller.PersonController;
import com.example.favoritecolor.services.PersonService;
import com.example.favoritecolor.services.PersonServiceCSV;
import com.example.favoritecolor.services.PersonServiceH2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfiguration {

  @Bean
  public PersonService personService(){
    return new PersonServiceCSV();
  }

  @Bean
  public PersonController personController(PersonService personService){
    return new PersonController(personService);
  }

}
