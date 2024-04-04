import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m;
    static int[][] area;
    static int[][] dp;
    static final int MIN_VAL = (int)1e9;

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        area = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; ++j) {
                area[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solution() {
        dp[1][1] = area[1][1];
        for (int j = 2; j <= m; j++) {
            dp[1][j] = dp[1][j - 1] + area[1][j];
        }
        for (int i = 2; i <= n; i++) {
            int[] left = new int[m + 1];
            int[] right = new int[m + 1];
            left[1] = dp[i - 1][1] + area[i][1];
            for (int j = 2; j <= m; j++) {
                left[j] = Math.max(left[j - 1], dp[i - 1][j]) + area[i][j];
            }
            right[m] = dp[i - 1][m] + area[i][m];
            for (int j = m - 1; j >= 1; j--) {
                right[j] = Math.max(right[j + 1], dp[i - 1][j]) + area[i][j];
            }
            for (int j = 1; j <= m; j++) {
                dp[i][j] = Math.max(left[j], right[j]);
            }
        }
        System.out.println(dp[n][m]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
