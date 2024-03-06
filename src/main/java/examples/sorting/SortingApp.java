package examples.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingApp {
    public static void main(String[] args) {
        List<Sorter> sorters = new ArrayList<>();

        sorters.add(new BubbleSort());
        sorters.add(new SelectionSort());

        List<Integer> list = Arrays.asList(4, 2, 1, 5, 7, 3, 8);
        System.out.println("Starting list:\t" + list);

        sorters.stream()
                .map(s ->
                        s.getClass().getSimpleName() + ": \t" +
                        s.sort(new ArrayList<>(list)))
                .forEach(System.out::println);
    }
}
