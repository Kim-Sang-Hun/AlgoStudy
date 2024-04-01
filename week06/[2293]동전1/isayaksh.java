import java.util.*;
import java.io.*;

public class isayaksh {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        long[] dp = new long[K+1];

        for(int n = 0; n < N; n++) {
            coins[n] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;

        for(int coin : coins) {
            for(int k = 1; k < K+1; k++) {
                if(k - coin < 0) continue;
                dp[k] += dp[k-coin];
            }
        }

        System.out.println(dp[K]);

    }
}
