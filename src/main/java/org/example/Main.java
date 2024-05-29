package org.example;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the movie title: ");
    String movieTitle = scanner.nextLine();

    String apiKey = "43e78112";
    String apiUrl = "http://www.omdbapi.com/?apikey=" + apiKey + "&t=" + movieTitle;
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(apiUrl))
        .build();

    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String json = response.body();
      System.out.println(json);

      Gson gson = new Gson();
      Title myTitle = gson.fromJson(json, Title.class);
      System.out.println(myTitle);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
