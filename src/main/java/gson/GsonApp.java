package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class GsonApp implements Runnable {
    private final Gson gson;

    public GsonApp() {
        gson = new GsonBuilder().create();
    }

    public String sendRequest() throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public List<Todo> syncGson() throws Exception {
        String body = sendRequest();
        return gson.fromJson(body, new TypeToken<List<Todo>>(){}.getType());
    }

    @Override
    public void run() {
        try {
            System.out.println(sendRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        GsonApp app = new GsonApp();
        app.run();
    }
}
