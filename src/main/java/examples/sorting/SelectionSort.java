package examples.sorting;

import java.util.List;

import static examples.sorting.Swapper.swap;

public class SelectionSort implements Sorter {
    private int minIndex(List<Integer> list, int start, int end) {
        int min = list.get(start);
        int minIndex = start;

        for (int i = start; i < end; i++) {
            int ith = list.get(i);

            if (ith < min) {
                min = ith;
                minIndex = i;
            }
        }

        return minIndex;
    }

    @Override
    public List<Integer> sort(List<Integer> list) {
        int sorted = 0;
        int size = list.size();

        while (sorted < size) {
            int minIndex = minIndex(list, sorted, size - 1);
            if (minIndex != sorted) swap(list, minIndex, sorted);

            sorted++;
        }

        return list;
    }
}
