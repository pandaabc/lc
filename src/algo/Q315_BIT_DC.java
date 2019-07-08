package algo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 */
public class Q315_BIT_DC {

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Collections.EMPTY_LIST;
        }
        int[] sortedDistinctNum = Arrays.stream(nums).distinct().sorted().toArray();
        Map<Integer, Integer> numIndex = IntStream.range(0, sortedDistinctNum.length).boxed().collect(Collectors.toMap(i -> sortedDistinctNum[i], i -> i));
        int[] bit = new int[sortedDistinctNum.length + 1];
        int[] reverseRes = IntStream.range(0, nums.length)
                .map(i -> nums.length - 1 - i).mapToObj(e -> {
            int i = numIndex.get(nums[e]);
            int res = bitSum(bit, i);
            bitUpdate(bit, i);
            return res;
        }).mapToInt(e -> e).toArray();
        return IntStream.range(0, reverseRes.length).map((i -> reverseRes.length - 1 - i)).mapToObj(i -> reverseRes[i]).collect(Collectors.toList());

    }

    // always update count to plus 1
    private void bitUpdate(int[] bit, int index) {
        index = index + 1;
        while (index < bit.length) {
            bit[index] += 1;
            index += index & (-index);
        }
    }

    private int bitSum(int[] bit, int index) {
        index = index; // usually it should be index = index + 1;  However, in this case, it is looking for values small than the current number.  So we dont need to include itself.
        int sum = 0;
        while (index > 0) {
            sum += bit[index];
            index = index - index & (-index);
        }
        return sum;
    }

    public List<Integer> countSmallerTreeMapImpl(int[] nums) {
        SortedMap<Integer, Integer> map = new TreeMap();
        int[] count = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i --) {
            count[i] = map.subMap(Integer.MIN_VALUE, nums[i]).values().stream().mapToInt(e -> e).sum();
            map.compute(nums[i], (k, v) -> v == null ? 1 : v + 1);
        }
        return Arrays.stream(count).boxed().collect(Collectors.toList());
    }

}
