import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][] a;
    static long[][] dp;
    static final int[] dx = {0, 1};
    static final int[] dy = {1, 0};

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        a = new int[n][n];
        dp = new long[n + 10][n + 10];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    //그냥 진짜 칸 마다 점프하면서 체크해주면 된다.
    //다만 9보다 작거나 같은 정수이므로 10정도 더 크게 배열 사이즈를 잡아줘 OutOfBounds를 제어한다.
    static void solution(){
        dp[0][0] = 1;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                int mul = a[i][j];
                if(mul == 0) continue;
                dp[i][j + mul] += dp[i][j];
                dp[i + mul][j] += dp[i][j];
            }
        }
        System.out.println(dp[n - 1][n - 1]);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
