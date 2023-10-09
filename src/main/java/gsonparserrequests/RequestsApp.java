package gsonparserrequests;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

        /*
        Instead of using classes to model response, which would require to model sub-classes:
        Response response = gson.fromJson(responseGet.body(), Response.class);
        System.out.println(response);

        https://stackoverflow.com/questions/16595493/gson-parsing-without-a-lot-of-classes
        When you want to simply extract field without modeling with classes, use JsonParser with body like this:
        {
          "args": {},
          "headers": {
            "x-forwarded-proto": "https",
            "x-forwarded-port": "443",
            "host": "postman-echo.com",
            "x-amzn-trace-id": "Root=1-6523f4b9-2b93bd2712ce07a37bdd5815",
            "key1": "value1",
            "key2": "value2",
            "user-agent": "Java-http-client/21"
          },
          "url": "https://postman-echo.com/get"
        }
         */
        JsonObject rootObject = JsonParser.parseString(responseGet.body()).getAsJsonObject();
        JsonObject headersObject = rootObject.get("headers").getAsJsonObject();
        String userAgent = headersObject.get("user-agent").getAsString();
        System.out.println("Extracted user agent info from GET response: " + userAgent);
    }
}
