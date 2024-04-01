import java.util.*;
import java.io.*;

public class isayaksh {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t < T+1; t++) {

            int N = Integer.parseInt(br.readLine());
            int[][] dp = new int[2][N];

            for(int i = 0; i < 2; i++) {
                dp[i] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            }

            // init
            if(N != 1) {
                dp[0][1] += dp[1][0];
                dp[1][1] += dp[0][0];
            }

            // dynamic programming
            for(int i = 2; i < N; i++) {
                dp[0][i] += Math.max(dp[1][i-1], dp[1][i-2]);
                dp[1][i] += Math.max(dp[0][i-1], dp[0][i-2]);
            }

            sb.append(Math.max(dp[0][N-1], dp[1][N-1]) + "\n");

        }

        System.out.println(sb);

    }

}
