package org.example;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import org.example.exceptions.TitleYearConversionException;

public class MainWithRecord {
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    String movieTitle = "";

    Properties properties = new Properties();
    properties.load(new FileInputStream("config.properties"));
    String apiKey = properties.getProperty("omdbKey");

    List<Title> titles = new ArrayList<>();

    Gson gsonWithBuilder = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
        .setPrettyPrinting()
        .create();

    while (!movieTitle.equalsIgnoreCase("leave")) {
      try {
        System.out.println("Enter the movie title: (try Divertidamente)");
        movieTitle = scanner.nextLine();

        if (movieTitle.equalsIgnoreCase("leave")) {
          break;
        }

        String apiUrl =
            "http://www.omdbapi.com/?apikey=" + apiKey + "&t=" + movieTitle.replace(" ", "+");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        System.out.println(json);

        TitleOmdb titleRecord = gsonWithBuilder.fromJson(json, TitleOmdb.class);
        System.out.println(titleRecord);

        System.out.println("");
        System.out.println("Converting TitleOmdb to Title");

        Title newTitle = new Title(titleRecord);
        System.out.println(newTitle);

        titles.add(newTitle);
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
        System.out.println("");
      }
    }

    System.out.println(titles);

//    Save the title to a file
    File file = new File("title.txt");
    FileWriter writer = new FileWriter(file);
    writer.write(gsonWithBuilder.toJson(titles));
    writer.close();

    System.out.println("File created: " + file.getAbsolutePath());
    System.out.println("File size: " + file.length() + " bytes");
    System.out.println("File content: ");
    Scanner fileScanner = new Scanner(file);
    while (fileScanner.hasNextLine()) {
      System.out.println(fileScanner.nextLine());
    }

    System.out.println("End of the program");
  }
}
