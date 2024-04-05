package questions.lakesvolume;

import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://techdevguide.withgoogle.com/resources/former-interview-question-volume-of-lakes/#">Google Tech Dev Guide</a>!
 * Imagine an island that is in the shape of a bar graph. When it rains, certain areas of the island fill up with rainwater to form lakes.
 * Any excess rainwater the island cannot hold in lakes will run off the island to the west or east and drain into the ocean.
 * Given an array of positive integers representing 2-D bar heights, design an algorithm (or write a function) that can compute
 * the total volume (capacity) of water that could be held in all lakes on such an island given an array of the heights of the bars.
 * Assume an elevation map where the width of each bar is 1.
 * Example: Given [1,3,2,4,1,3,1,4,5,2,2,1,4,2,2], return 15 (3 bodies of water with volumes of 1,7,7 yields total volume of 15)
 */
public class Volume implements Runnable {
    @Override
    public void run() {
        List<Integer> heights = Arrays.asList(1, 3, 2, 4, 1, 3, 1, 4, 5, 2, 2, 1, 4, 2, 2);

        int sum = 0;
        int curr = 0;
        int max = heights.getFirst();
        int maxIndex = 0;
        for (int i = 0; i < heights.size(); i++) {
            int h = heights.get(i);
            if (h >= max) {
                sum += curr;
                curr = 0;
                max = h;
                maxIndex = i;
            } else {
                curr += max - h;
            }
        }

        // additional step: re-perform right-to-left until meeting maxIndex
        curr = 0;
        max = heights.getLast();
        for (int i = heights.size() - 1; i >= maxIndex; i--) {
            int h = heights.get(i);
            if (h >= max) {
                sum += curr;
                curr = 0;
                max = h;
            } else {
                curr += max - h;
            }
        }

        System.out.printf("The sum is: %d\n", sum);
    }

    public static void main(String[] args) {
        Volume volume = new Volume();
        volume.run();
    }
}
