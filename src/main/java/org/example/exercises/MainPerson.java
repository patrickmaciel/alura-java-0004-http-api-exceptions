package org.example.exercises;

import com.google.gson.Gson;

public class MainPerson {

  public static void main(String[] args) {
    String pessoaJson = "{\"nome\":\"João\",\"idade\":\"25\",\"cidade\":\"São Paulo\"}";

    Gson gson = new Gson();
    Person pessoa = gson.fromJson(pessoaJson, Person.class);
    System.out.println(pessoa);
  }
}
