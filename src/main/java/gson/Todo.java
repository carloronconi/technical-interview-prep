package gson;

import lombok.*;

@AllArgsConstructor
@EqualsAndHashCode // required to perform equals assertion, otherwise uses parent Object equals (which compares references)
public class Todo {
    int userId;
    int id;
    @Getter
    String title;
    @Setter(AccessLevel.PACKAGE)
    boolean completed;
}
