package org.example.exercises;

public class Address {
  private String postalCode;
  private String street;
  private String neighborhood;
  private String city;
  private String state;

  public Address(ViaCepResponse viaCepResponse) {
    this.postalCode = viaCepResponse.cep();
    this.street = viaCepResponse.logradouro();
    this.neighborhood = viaCepResponse.bairro();
    this.city = viaCepResponse.localidade();
    this.state = viaCepResponse.uf();
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getNeighborhood() {
    return neighborhood;
  }

  public void setNeighborhood(String neighborhood) {
    this.neighborhood = neighborhood;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "Address{" +
        "postalCode='" + postalCode + '\'' +
        ", street='" + street + '\'' +
        ", neighborhood='" + neighborhood + '\'' +
        ", city='" + city + '\'' +
        ", state='" + state + '\'' +
        '}';
  }
}
