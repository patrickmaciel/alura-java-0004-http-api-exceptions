package org.example.exercises;

import com.google.gson.annotations.SerializedName;

public class Person {
  @SerializedName("nome")
  private String name;
  @SerializedName("idade")
  private String age;
  @SerializedName("cidade")
  private String city;

  public Person(String name, String age, String city) {
    this.name = name;
    this.age = age;
    this.city = city;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", age='" + age + '\'' +
        ", city='" + city + '\'' +
        '}';
  }
}
