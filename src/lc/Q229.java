package lc;

import java.util.ArrayList;
import java.util.List;

public class Q229 {


    // Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.


    public List<Integer> majorityElement(int[] nums) {

        Integer i0 = null;
        Integer i1 = null;
        int c0 = 0;
        int c1 = 0;

        for (int num : nums) {

            if (i0 != null && num == i0) {
                c0 ++;
            } else if (i1 != null && num == i1) {
                c1 ++;
            } else if (c0 == 0) {
                i0 = num;
            } else if (c1 == 0) {
                i1 = num;
            } else {
                c0 --;
                c1 --;
            }

        }

        c0 = 0;
        c1 = 0;
        for (int num : nums) {
            if (num == i0) {
                c0 ++;
            } else if (num == i1) {
                c1 ++;
            }
        }

        List<Integer> list = new ArrayList<>();
        if (c0 > nums.length/3) {
            list.add(i0);
        }
        if (c1 > nums.length/3) {
            list.add(i1);
        }

        return list;

    }

}
