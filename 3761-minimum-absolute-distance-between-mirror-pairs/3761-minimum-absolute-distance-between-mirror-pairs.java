class Solution {
    public int minMirrorPairDistance(int[] nums) {
        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int rev = reverse(nums[i]);
            if (map.containsKey(nums[i])) {
                ans = Math.min(ans, i - map.get(nums[i]));
            }
            map.put(rev, i);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    private int reverse(int n) {
        int rev = 0;
        while (n > 0) {
            rev = rev * 10 + (n % 10);
            n /= 10;
        }
        return rev;
    }
}