package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.NoArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor // only used for testing
public class GsonApp implements Runnable {
    private final Gson gson = new GsonBuilder().create();
    private ArrayList<User> users;

    public GsonApp(List<User> users) {
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
        return response.body();
    }

    public List<Todo> syncGson() throws Exception {
        String body = sendRequest();
        return gson.fromJson(body, new TypeToken<List<Todo>>(){}.getType());
    }

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
            System.out.println(users);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(1, "Alice"));
        users.add(new User(2, "Bob"));
        GsonApp app = new GsonApp(users);
        app.run();
    }
}
