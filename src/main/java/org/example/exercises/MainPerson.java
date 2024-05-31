package org.example.exercises;

import com.google.gson.Gson;

public class MainPerson {

  public static void main(String[] args) {
    String jsonResponse = "{\"nome\":\"João\",\"idade\":\"25\",\"cidade\":\"São Paulo\"}";

    Gson gson = new Gson();
    PersonJson personJson = gson.fromJson(jsonResponse, PersonJson.class);
    Person person = new Person(personJson);
    System.out.println("PersonJson: " + personJson);
    System.out.println("Person: " + person);
  }
}
