package org.example.exercises;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainPerson {

  public static void main(String[] args) throws IOException {
    String jsonResponse = "{\"nome\":\"João\",\"idade\":\"25\",\"cidade\":\"São Paulo\"}";

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    PersonJson personJson = gson.fromJson(jsonResponse, PersonJson.class);
    Person person = new Person(personJson);
    System.out.println("PersonJson: " + personJson);
    System.out.println("Person: " + person);

    File file = new File("person.json");
    FileWriter fileWriter = new FileWriter(file);
    fileWriter.write(gson.toJson(person));
    fileWriter.close();
  }
}
