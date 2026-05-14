import java.util.Arrays;

class Solution {
    public boolean isGood(int[] nums) {

        // Minimum valid size is 2
        if (nums.length < 2) {
            return false;
        }

        Arrays.sort(nums);

        int n = nums.length;

        // Last two elements must be equal
        if (nums[n - 1] != nums[n - 2]) {
            return false;
        }

        // Check sequence 1,2,3...(n-1)
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] != i + 1) {
                return false;
            }
        }

        return true;
    }
}