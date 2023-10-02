package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "What's your name?" );
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println("Hello, " + input + "!");

        try {
            HttpRequest requestGet = HttpRequest.newBuilder()
                    .uri(new URI("https://postman-echo.com/get"))
                    .header("key1", "value1")
                    .header("key2", "value2")
                    .GET()
                    .build();

            HttpRequest requestPost = HttpRequest.newBuilder()
                    .uri(new URI("https://postman-echo.com/post"))
                    .headers("Content-Type", "text/plain;charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString("Sample request body")) // use ofString to send Json!
                    .build();

            HttpClient client = HttpClient.newBuilder().build();

            HttpResponse<String> responseGet = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> responsePost = client.send(requestPost, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response body for GET request:\n" + responseGet.body() +
                    "\nResponse body for POST request:\n" + responsePost.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
