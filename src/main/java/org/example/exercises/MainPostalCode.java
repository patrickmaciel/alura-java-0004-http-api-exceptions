package org.example.exercises;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainPostalCode {

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    String option = "";

    HttpClient client = HttpClient.newHttpClient();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    File file = new File("address.txt");
    FileWriter fileWriter = new FileWriter(file, true);

    display();
    option = scanner.nextLine();

    while (!option.equalsIgnoreCase("leave")) {
      try {
        switch (option) {
          case "1":
            System.out.print("Enter the postal code: ");
            String postalCode = scanner.nextLine();

            // remove all special characters
            postalCode = postalCode.replaceAll("[^a-zA-Z0-9]", "");

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + postalCode + "/json/"))
                .build();

            System.out.println("Request: " + request);

            HttpResponse<String> response = client.send(request,
                HttpResponse.BodyHandlers.ofString());
            ViaCepResponse viaCepResponse = gson.fromJson(response.body(), ViaCepResponse.class);
            System.out.println(viaCepResponse);

            // check if cep from viaCepResponse is null
            if (viaCepResponse.cep() == null) {
              System.out.println("Postal code not found");
              System.out.println("");
              display();
              option = scanner.nextLine();
              continue;
            }

            Address address = new Address(viaCepResponse);
            System.out.println(address);

            fileWriter.append(gson.toJson(address) + ",\n");
            fileWriter.flush();
            break;
          case "2":
            System.out.print("Enter the State (example: MG): ");
            String state = scanner.nextLine().replace(" ",  "+");

            System.out.print("Enter the City: ");
            String city = scanner.nextLine().replace(" ",  "+");

            System.out.print("Enter the Location / Street / Avenue (without the number): ");
            String location = scanner.nextLine().replace(" ",  "+");
            location = location.replace(" ", "+");

            HttpRequest requestDetailed = HttpRequest.newBuilder()
                .uri(URI.create(
                    "https://viacep.com.br/ws/"
                        + state + "/"
                        + city + "/"
                        + location + "/json/"))
                .build();

            System.out.println("Request: " + requestDetailed);

            HttpResponse<String> responseDetailed = client.send(requestDetailed,
                HttpResponse.BodyHandlers.ofString());

            Type listType = new TypeToken<ArrayList<ViaCepResponse>>(){}.getType();
            List<ViaCepResponse> viaCepResponses = gson.fromJson(responseDetailed.body(), listType);
            System.out.println(viaCepResponses);

            for (ViaCepResponse viaCepResponseItem : viaCepResponses) {
              Address addressItem = new Address(viaCepResponseItem);
              System.out.println(addressItem);

              fileWriter.append(gson.toJson(addressItem) + ",\n");
              fileWriter.flush();
            }
            break;
          case "leave":
            break;
          default:
            System.out.println("Invalid option");
            continue;
        }
      } catch (IOException e) {
        System.out.println("IOException: " + e.getMessage());
      } catch (Exception e) {
        System.out.println("Exception: " + e.getMessage());
      } finally {
        System.out.println("");
        display();
        option = scanner.nextLine();
      }
    }

    scanner.close();
    fileWriter.close();

    System.out.println("Addresses saved to file");
    System.out.println(file);

    System.out.println("Leaving the program");
  }

  private static void display()
  {
    System.out.println("###############################");
    System.out.println("##### POSTAL CODE SEARCH #####");
    System.out.println("How do you want to search for a postal code?");
    System.out.println("1 - By postal code");
    System.out.println("2 - By address");
    System.out.println("Type 'leave' to exit");
    System.out.println("");
    System.out.println("Enter your option: ");
  }
}
