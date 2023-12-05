package interviews.taskscheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@Getter
public class Task {
    private int id;
    @Setter
    private int start;
    @Setter
    private int end;
    private List<Integer> dependencies;
}
