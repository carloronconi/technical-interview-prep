package questions.sets;

import java.util.*;

/**
 * https://leetcode.com/problems/find-the-difference-of-two-arrays/
 * Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
 *
 *     answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
 *     answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
 *
 * Note that the integers in the lists may be returned in any order.

 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,3], nums2 = [2,4,6]
 * Output: [[1,3],[4,6]]
 * Explanation:
 * For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3 are not present in nums2. Therefore, answer[0] = [1,3].
 * For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6 are not present in nums2. Therefore, answer[1] = [4,6].
 *
 * Example 2:
 *
 * Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
 * Output: [[3],[]]
 * Explanation:
 * For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3], their value is only included once and answer[0] = [3].
 * Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
 *
 *
 *
 * Constraints:
 *
 *     1 <= nums1.length, nums2.length <= 1000
 *     -1000 <= nums1[i], nums2[i] <= 1000
 */
public class SetsApp {
    public static void main(String[] args) {
        SetsApp app = new SetsApp();
        int[] input1 = new int[] {1, 2, 3, 3};
        int[] input2 = new int[] {2, 4, 6};

        System.out.println(Arrays.toString(input1));
        System.out.println(Arrays.toString(input2));

        var diff = app.findDifference(input1, input2);
        System.out.println(diff);

        diff = app.findDifferenceImproved(input1, input2);
        System.out.println(diff);
    }

    /*  # Notes on converting int[] into List<Integer>

        int[] arr = new int[] {2, 4, 6};
        List<Integer> list = Arrays.asList(arr);
        Set<Integer> set = new HashSet<Integer>(list);

        this doesn't compile because the first line fails: int[] inherits directly from Object,
        so `Arrays.asList(input)` creates a List<int[]> and not a List<Integer>.
        For int[], manually adding to the list/set is required, due to existence of int and Integer.
        Instead, for a String[] you can do that and just use Arrays.asList:

        String[] arr = new String[] {"aaa", "bbb"};
        List<String> list = Arrays.asList(arr);
    */

    /**
     * Basic implementation using HashSet
     * Note: recall that HashSet's are based on HashMap's!
     */
    private List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s1c;
        Set<Integer> s2 = new HashSet<>();

        for(int e: nums1) s1.add(e);
        s1c = new HashSet<>(s1);
        for(int e: nums2) s2.add(e);

        List<List<Integer>> result = new ArrayList<>(2);

        s1.removeAll(s2);
        result.add(new ArrayList<>(s1));
        s2.removeAll(s1c);
        result.add(new ArrayList<>(s2));

        return result;
    }

    /**
     * leverage the constraints to use frequency arrays: the values contained in arrays are between -1000 and +1000
     */
    private List<List<Integer>> findDifferenceImproved(int[] nums1, int[] nums2) {
        boolean[] freq1 = new boolean[2001];
        boolean[] freq2 = new boolean[2001];

        for (int e: nums1) freq1[e + 1000] = true; // min: e = -1000 -> e + 1000 = 0
        for (int e: nums2) freq2[e + 1000] = true; // max: e = 1000 -> e + 1000 = 2000

        List<Integer> r1 = new ArrayList<>();
        List<Integer> r2 = new ArrayList<>();
        for (int i = 0; i < 2001; i++) if (freq1[i] && !freq2[i]) r1.add(i - 1000);
        for (int i = 0; i < 2001; i++) if (freq2[i] && !freq1[i]) r2.add(i - 1000);

        List<List<Integer>> result = new ArrayList<>(2);
        result.add(r1);
        result.add(r2);

        return result;
    }
}
