package gsonclassrequests;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode // required to perform equals assertion, otherwise uses parent Object equals (which compares references)
public class Todo {
    @Getter
    private int userId;
    private int id;
    @Getter
    private String title;
    @Setter(AccessLevel.PACKAGE)
    boolean completed;

    @Override
    public String toString() {
        return id + " " + title + " " + completed;
    }
}
