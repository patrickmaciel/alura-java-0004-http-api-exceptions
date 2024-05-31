package org.example;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import org.example.exceptions.TitleYearConversionException;

public class MainWithRecord {
  public static void main(String[] args) {
    try {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the movie title: (try Divertidamente)");
      String movieTitle = scanner.nextLine();

      String apiKey = "43e78112";
      String apiUrl = "http://www.omdbapi.com/?apikey=" + apiKey + "&t=" + movieTitle.replace(" ", "+");
      HttpClient client = HttpClient.newHttpClient();
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(apiUrl))
          .build();

      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
      String json = response.body();
      System.out.println(json);

      Gson gsonWithBuilder = new GsonBuilder()
          .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
      TitleOmdb titleRecord = gsonWithBuilder.fromJson(json, TitleOmdb.class);
      System.out.println(titleRecord);

      System.out.println("");
      System.out.println("Converting TitleOmdb to Title");

      Title newTitle = new Title(titleRecord);
      System.out.println(newTitle);

      // Save the title to a file
      FileWriter file = new FileWriter("title.txt");
      file.write(newTitle.toString());
      file.close();
    } catch (IOException | InterruptedException e) {
      System.out.println("IO / Interrupted: " + e.getMessage());
    } catch (NumberFormatException e) {
      System.out.println("NumberFormatException: " + e.getMessage());
    } catch (JsonSyntaxException e) {
      System.out.println("JsonSyntaxException: " + e.getMessage());
    } catch (IllegalArgumentException e) {
      System.out.println("IllegalArgumentException: " + e.getMessage());
    } catch (TitleYearConversionException e) {
      System.out.println("TitleYearConversionException: " + e.getMessage());
    } catch (Exception e) {
      System.out.println("Exception: " + e.getMessage());
    } finally {
      System.out.println("Finalizing the program");
    }
  }
}
