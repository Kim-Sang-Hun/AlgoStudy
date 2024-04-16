import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17404_RGB거리2 {
    static int N;
    static int[][] cost;
    static int[][] memo;
    static int[] paint;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1][3];
        memo = new int[N + 1][3];
        paint = new int[3];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());

            cost[i][0] = red;
            cost[i][1] = green;
            cost[i][2] = blue;
        }


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j)
                    memo[1][j] = cost[1][j];
                else
                    memo[1][j] = 1001;
            }

            for (int k = 2; k < N + 1; k++) {
                memo[k][0] = Math.min(memo[k - 1][1], memo[k - 1][2]) + cost[k][0];
                memo[k][1] = Math.min(memo[k - 1][0], memo[k - 1][2]) + cost[k][1];
                memo[k][2] = Math.min(memo[k - 1][0], memo[k - 1][1]) + cost[k][2];
                if (k == N) {
                    if (i == 0) {
                        paint[i] = Math.min(memo[N][1], memo[N][2]);
                    }
                    if (i == 1) {
                        paint[i] = Math.min(memo[N][0], memo[N][2]);
                    }
                    if (i == 2) {
                        paint[i] = Math.min(memo[N][0], memo[N][1]);
                    }

                }
            }

        }

        System.out.print(Math.min(paint[0], Math.min(paint[1], paint[2])));
    }

}
