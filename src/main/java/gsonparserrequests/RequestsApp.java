package gsonparserrequests;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class RequestsApp {
    public static void main( String[] args ) throws Exception {
        System.out.println( "What's your name?" );
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        System.out.println("Hello, " + input + "!");

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

        Gson gson = new Gson();
        Response response = gson.fromJson(responseGet.body(), Response.class); // TODO https://stackoverflow.com/questions/16595493/gson-parsing-without-a-lot-of-classes
        System.out.println(response);
    }
}
