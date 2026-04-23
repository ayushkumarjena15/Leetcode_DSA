class Solution {
    public long[] distance(int[] nums) {
        int n = nums.length;
        long[] ans = new long[n];
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        // Store indices of each number
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }
        
        // Process each group
        for (List<Integer> list : map.values()) {
            int size = list.size();
            long[] prefix = new long[size];
            
            prefix[0] = list.get(0);
            for (int i = 1; i < size; i++) {
                prefix[i] = prefix[i - 1] + list.get(i);
            }
            
            for (int i = 0; i < size; i++) {
                long left = 0, right = 0;
                
                if (i > 0) {
                    left = (long) list.get(i) * i - prefix[i - 1];
                }
                
                if (i < size - 1) {
                    right = (prefix[size - 1] - prefix[i]) - (long) list.get(i) * (size - i - 1);
                }
                
                ans[list.get(i)] = left + right;
            }
        }
        
        return ans;
    }
}