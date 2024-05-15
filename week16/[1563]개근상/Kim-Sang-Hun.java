import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JUN1563_개근상 {
    static int n;
    static final int dividend = 1_000_000;
    static int[][][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        // 0~2 : 연속 결석일
        // 0~1 : 지각
        dp = new int[n + 1][3][2];
        dp[0][0][0] = 1; // 첫날 출석
        dp[0][0][1] = 1; // 첫날 지각
        dp[0][1][0] = 1; // 첫날 결석
        for (int i = 1; i < n; i++) {
            dp[i][0][0] = (dp[i-1][0][0] + dp[i-1][1][0] + dp[i-1][2][0]) % dividend; // 출석, 지각 0은 전날 출석 + 결석1 + 결석2의 경우의 수에서 가능
            dp[i][0][1] = (dp[i][0][0] + dp[i-1][0][1] + dp[i-1][1][1] + dp[i-1][2][1]) % dividend; // 출석, 지각 1은 전날의 모든 경우의 수에서 가능
            dp[i][1][0] = dp[i-1][0][0];
            dp[i][1][1] = dp[i-1][0][1];
            dp[i][2][0] = dp[i-1][1][0];
            dp[i][2][1] = dp[i-1][1][1];
        }
        int answer = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                answer += dp[n-1][i][j];
            }
        }
        System.out.println(answer % dividend);
    }
}
