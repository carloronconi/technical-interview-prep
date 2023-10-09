package gsonclassrequests;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@RequiredArgsConstructor
public class User {
    @Getter
    private final int id;
    private final String name;
    @Getter
    private final ArrayList<Todo> todos = new ArrayList<>();

    @Override
    public String toString() {
        return "\n" + id + " " + name + " " + todos;
    }
}
