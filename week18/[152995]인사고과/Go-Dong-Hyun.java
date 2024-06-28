// 96점나옴 안함 ㅅㄱ

class Solution {
    public int solution(int[][] scores) {
        int ans = 1;
        int sub_ans = 0;
        int N = scores.length;
        int[] sum = new int[N];
        sum[0] = scores[0][0] + scores[0][1];
        
        for (int i = 0; i < N; i++) {
            if (scores[0][0] < scores[i][0] && scores[0][1] < scores[i][1]) 
                return -1;

            sum[i] = scores[i][0] + scores[i][1];
            
            if (sum[0] > sum[i]) continue;
            
            boolean flag = false;
            for (int j = 0; j < N; j++) {
                if (scores[i][0] < scores[j][0] && scores[i][1] < scores[j][1]) {
                    flag = true;
                    break;
                }
            }
            
            if (flag && sum[i] > sum[0]) sub_ans++;
            if (sum[0] < sum[i]) ans++;
        }
        
        return ans-sub_ans;
    }
}
