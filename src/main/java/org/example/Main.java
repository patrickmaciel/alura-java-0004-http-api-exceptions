package org.example;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the movie title: (try Matrix for example)");
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

      System.out.println("Using title + annotations");
      Gson gson = new Gson();
      Title myTitle = gson.fromJson(json, Title.class);
      System.out.println(myTitle);
    } catch (IOException | InterruptedException e) {
      System.out.println("IO / Interrupted: " + e.getMessage());
    } catch (NumberFormatException e) {
      System.out.println("NumberFormatException: " + e.getMessage());
    } catch (JsonSyntaxException e) {
      System.out.println("JsonSyntaxException: " + e.getMessage());
    } finally {
      scanner.close();
    }
  }
}
