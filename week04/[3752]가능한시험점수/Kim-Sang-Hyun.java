import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        int N;
        int[] scores;

        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            
            scores = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
            
            
            sb.append("#" + t + " " + solution(N, scores) + "\n");
        }
        System.out.println(sb);
        
    }
    /**
     * 반복 사용 가능한 경우는 `target`을 0부터 오름차순으로 계산할 수 있지만,
     * 사용 횟수에 제한이 존재할 경우 `target`의 값을 최대값부터 내림차순으로 동적 프로그래밍을 적용할 수 있다.
    **/
    public static int solution(int N, int[] scores) {

        int count = 1;

        int totalScore = Arrays.stream(scores).sum();
        boolean[] dp = new boolean[totalScore+1];
        dp[0] = true;
        for(int score : scores) {
            for(int target = totalScore; score <= target; target--) {
                if(dp[target] || !dp[target - score]) continue;
                dp[target] = true;
                count++;
            }
        }

        return count;
    }
    
}