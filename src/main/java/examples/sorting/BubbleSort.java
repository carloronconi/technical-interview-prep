package examples.sorting;

import java.util.List;

import static examples.sorting.Swapper.swap;

public class BubbleSort implements Sorter {
    @Override
    public List<Integer> sort(List<Integer> list) {
        int sorted = list.size();

        while (sorted > 0) {
            for (int i = 0; i < sorted - 1; i++) {
                int l = list.get(i);
                int r = list.get(i + 1);

                if (l > r) swap(list, i, i + 1);
            }
            sorted--;
        }

        return list;
    }
}
