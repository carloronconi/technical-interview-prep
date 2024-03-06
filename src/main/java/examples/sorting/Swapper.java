package examples.sorting;

import java.util.List;

public class Swapper {
    public static void swap(List<Integer> list, int first, int second) {
        int temp = list.get(first);
        list.set(first, list.get(second));
        list.set(second, temp);
    }
}
