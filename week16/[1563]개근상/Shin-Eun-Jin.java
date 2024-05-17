import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1563_개근상 {
    static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][][] dp = new int[N + 1][2][3];

        dp[0][0][0] = 1;

        for (int n = 1; n <= N; n++) {
            for (int late = 0; late < 2; late++) {
                for (int absent = 0; absent < 3; absent++) {
                    dp[n][late][0] = (dp[n][late][0] + dp[n - 1][late][absent]) % MOD;

                    if (late < 1) {
                        dp[n][late + 1][0] = (dp[n][late + 1][0] + dp[n - 1][late][absent]) % MOD;
                    }

                    if (absent < 2) {
                        dp[n][late][absent + 1] = (dp[n][late][absent + 1] + dp[n - 1][late][absent]) % MOD;
                    }
                }
            }
        }

        int result = 0;
        for (int late = 0; late < 2; late++) {
            for (int absent = 0; absent < 3; absent++) {
                result = (result + dp[N][late][absent]) % MOD;
            }
        }

        System.out.println(result);
    }
}
