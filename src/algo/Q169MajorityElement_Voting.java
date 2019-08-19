package algo;

public class Q169MajorityElement_Voting {

    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] == candidate) {
                count ++;
            } else if (count > 0){
                count --;
            } else {
                candidate = nums[i];
                count = 1;
            }
        }
        return candidate;
    }

}
