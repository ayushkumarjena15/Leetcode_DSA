import java.util.*;

class Solution {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            int x = points[i][0], y = points[i][1];
            if (y == 0) arr[i] = x;
            else if (x == side) arr[i] = side + y;
            else if (y == side) arr[i] = 3L * side - x;
            else arr[i] = 4L * side - y;
        }

        Arrays.sort(arr);

        int low = 0, high = 2 * side, ans = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (check(arr, k, side, mid)) {
                ans = mid;
                low = mid + 1;
            } else high = mid - 1;
        }

        return ans;
    }

    private boolean check(long[] arr, int k, int side, int dist) {
        int n = arr.length;
        long perimeter = 4L * side;

        long[] doubled = new long[2 * n];
        for (int i = 0; i < n; i++) {
            doubled[i] = arr[i];
            doubled[i + n] = arr[i] + perimeter;
        }

        for (int start = 0; start < n; start++) {
            int cnt = 1;
            long last = doubled[start];
            int idx = start + 1;

            while (cnt < k) {
                int next = lowerBound(doubled, idx, start + n - 1, last + dist);
                if (next > start + n - 1) break;
                cnt++;
                last = doubled[next];
                idx = next + 1;
            }

            // Must also check the closing gap: from last point back to start
            if (cnt == k && (doubled[start] + perimeter) - last >= dist) {
                return true;
            }
        }

        return false;
    }

    private int lowerBound(long[] arr, int l, int r, long target) {
        int ans = r + 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] >= target) {
                ans = mid;
                r = mid - 1;
            } else l = mid + 1;
        }
        return ans;
    }
}