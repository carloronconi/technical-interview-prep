package gsonrequests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GsonApp implements Runnable {
    private final Gson gson = new GsonBuilder().create();
    private final Logger logger = Logger.getAnonymousLogger();
    private ArrayList<User> users;

    public GsonApp() {
        try {
            logger.addHandler(new FileHandler("log.txt")); // console is already the default handler, here we add a second handler which is a file
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Couldn't add file logger: logging only in console!");
        }
    }

    public GsonApp(List<User> users) {
        this();
        this.users = new ArrayList<>(users);
    }

    /**
     * Send http GET request
     * @return body of the response
     */
    public String sendRequest() throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/todos"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        // System.out.println("Request answered with body response:\n" + body);
        return body;
    }

    /**
     * Extract class objects from json request using gson
     * @return Todos retrieved from the GET request
     */
    public List<Todo> syncGson() throws Exception {
        String body = sendRequest();
        return gson.fromJson(body, new TypeToken<List<Todo>>(){}.getType());
    }

    /**
     * Update user's todos from the GET request
     */
    public void updateUsersTodos() throws Exception {
        List<Todo> updatedTodos = syncGson();
        users.parallelStream().forEach(user -> user.getTodos().addAll(
                updatedTodos.stream().filter(todo -> todo.getUserId() == user.getId()).toList()
        ));
    }

    @Override
    public void run() {
        try {
            updateUsersTodos();
            logger.log(Level.INFO, users.toString());
        } catch (Exception e) {
            logger.log(Level.WARNING, "Couldn't reach the server!");
        }
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice"));
        users.add(new User(5, "Bob"));
        users.add(new User(2, "Carl"));
        GsonApp app = new GsonApp(users);
        app.run();
    }
}
