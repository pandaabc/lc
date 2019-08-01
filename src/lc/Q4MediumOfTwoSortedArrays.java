package lc;

public class Q4MediumOfTwoSortedArrays {

    public static void main(String[] args) {
        Q4MediumOfTwoSortedArrays q4 = new Q4MediumOfTwoSortedArrays();
        int[] n1 = new int[]{1, 2};
        int[] n2 = new int[]{4, 5};
        System.out.println(q4.findMedianSortedArrays(n1, n2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] n1 = nums1.length > nums2.length ? nums2 : nums1;
        int[] n2 = n1 == nums1 ? nums2 : nums1;

        int totalLen = n1.length + n2.length;

        int p0 = 0;
        int p1 = n1.length - 1;

        while (p0 <= p1) {
            int i1 = p0 + (p1 - p0) / 2;
            int i2 = n2.length - i1 - 2;

            int l1 = i1 < 0 || i1 >= n1.length ? Integer.MIN_VALUE : n1[i1];
            int l2 = i2 < 0 || i2 >= n2.length ? Integer.MIN_VALUE : n2[i2];
            int r1 = i1 + 1 < 0 || i1 + 1>= n1.length ? Integer.MAX_VALUE : n1[i1 + 1];
            int r2 = i2 + 1< 0 || i2 + 1>= n2.length ? Integer.MAX_VALUE : n2[i2 + 1];

            if (l1 > r2) {
                p0 = i1 - 1;
            } else if (l2 > r1) {
                p0 = i1 + 1;
            } else {
                // there are several more conditions to consider
                if (i1 <= 0) {
                    l1 = l2 = n2[totalLen / 2];
                    r1 = r2 = totalLen / 2 + 1 == n2.length ? n1[0] : n2[totalLen / 2 + 1];
                } else if (i1 >= n1.length - 1) {
                    l1 = l2 = n2[(totalLen - 1) / 2 - n1.length];
                    r1 = r2 = (totalLen - 1) / 2 - n1.length + 1 == n2.length ? n1[n1.length - 1] : n2[(totalLen - 1) / 2 - n1.length + 1];
                }
                if (totalLen % 2 == 1) {
                    return Math.max(l1, l2);
                }
                return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
            }
        }
        return -1;
    }

}
