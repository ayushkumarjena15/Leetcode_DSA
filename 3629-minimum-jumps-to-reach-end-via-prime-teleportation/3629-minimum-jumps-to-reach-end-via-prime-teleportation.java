class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;

        // store indices divisible by each prime
        List<Integer>[] div = new ArrayList[1000001];

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            for (int p : getPrimeFactors(x)) {
                if (div[p] == null) {
                    div[p] = new ArrayList<>();
                }
                div[p].add(i);
            }
        }

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[n];
        boolean[] usedPrime = new boolean[1000001];

        q.offer(0);
        vis[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int i = q.poll();

                if (i == n - 1) return steps;

                // move left
                if (i - 1 >= 0 && !vis[i - 1]) {
                    vis[i - 1] = true;
                    q.offer(i - 1);
                }

                // move right
                if (i + 1 < n && !vis[i + 1]) {
                    vis[i + 1] = true;
                    q.offer(i + 1);
                }

                // teleport only if nums[i] is prime
                int val = nums[i];

                if (isPrime(val) && !usedPrime[val]) {
                    usedPrime[val] = true;

                    if (div[val] != null) {
                        for (int nxt : div[val]) {
                            if (!vis[nxt]) {
                                vis[nxt] = true;
                                q.offer(nxt);
                            }
                        }
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    private boolean isPrime(int x) {
        if (x < 2) return false;

        for (int i = 2; i * i <= x; i++) {
            if (x % i == 0) return false;
        }

        return true;
    }

    private List<Integer> getPrimeFactors(int x) {
        List<Integer> res = new ArrayList<>();

        for (int p = 2; p * p <= x; p++) {
            if (x % p == 0) {
                res.add(p);

                while (x % p == 0) {
                    x /= p;
                }
            }
        }

        if (x > 1) {
            res.add(x);
        }

        return res;
    }
}