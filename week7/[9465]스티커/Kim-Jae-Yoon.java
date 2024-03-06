import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[][] sticker;
    static int[][] dp;

    static void solution() {
        for (int i = 1; i <= n; ++i) {
            //이전 스티커를 떼고 왔다면 다른 높이에서 왔어야 한다.
            //떼고 온 것이 아니라면 그냥 직전 dp값만 가져온다.
            dp[0][i] = Math.max(dp[0][i - 1], dp[1][i - 1] + sticker[0][i - 1]);
            dp[1][i] = Math.max(dp[1][i - 1], dp[0][i - 1] + sticker[1][i - 1]);
        }
        sb.append(Math.max(dp[0][n], dp[1][n])).append('\n');
    }

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        sticker = new int[2][n + 2];
        dp = new int[2][n + 2];
        for (int i = 0; i < 2; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                sticker[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; ++i) {
            input();
            solution();
        }
        System.out.println(sb);
    }
}
