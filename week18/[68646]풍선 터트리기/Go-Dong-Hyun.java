class Solution {
    
    public int solution(int[] a) {
        int ans = 0;
        
        int N = a.length;
        int min_val = Integer.MAX_VALUE;
        int min_idx = 0;
        
        for (int i = 0; i < N; i++) {
            if (a[i] < min_val) {
                min_val = a[i];
                min_idx = i;
            }
        }
        
        int tmp = Integer.MAX_VALUE;
        for (int i = 0; i < min_idx; i++) {
            if (tmp > a[i]) {
                ans++;
                tmp = a[i];
            }
        }
        
        tmp = Integer.MAX_VALUE;
        for (int i = N-1; i > min_idx; i--) {
            if (tmp > a[i]) {
                ans++;
                tmp = a[i];
            }
        }
        
        return ans +1;
    }
}
