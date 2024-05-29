package org.example;

import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.Scanner;

public class GoogleBooks {

  public static void main(String[] args) {
    try {
      Properties properties = new Properties();
      properties.load(new FileInputStream("config.properties"));

      System.out.println("Hello, Google Books!");

      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the book title: ");
      String bookTitle = scanner.nextLine();

      String apiKey = properties.getProperty("apiKey");
      String urlAddress = "https://www.googleapis.com/books/v1/volumes?q=" + bookTitle + "&key=" + apiKey;

      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(urlAddress))
          .build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String json = response.body();

      Gson gson = new Gson();
      gson.fromJson(json, Object.class);

      System.out.println(response.body());
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
