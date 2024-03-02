import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
    static int N;
    static int[][] grid;
    static int totalPeople = 0;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                totalPeople += grid[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int d1 = 1; d1 < N; d1++) {
                    for (int d2 = 1; d2 < N; d2++) {
                        if (i + d1 + d2 >= N) continue;
                        if (j - d1 < 0 || j + d2 >= N) continue;

                        basePoint(i, j, d1, d2);
                    }
                }
            }
        }
        System.out.println(min);
    }

    static void basePoint(int r, int c, int d1, int d2) {
        int[][] div = new int[N][N];

        for (int i = 0; i <= d1; i++) {
            div[r + i][c - i] = 5;
            div[r + d2 + i][c + d2 - i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            div[r + i][c + i] = 5;
            div[r + d1 + i][c - d1 + i] = 5;
        }
        int[] sum = new int[5];

        for (int i = 0; i < r + d1; i++) {
            for (int j = 0; j <= c; j++) {
                if (div[i][j] == 5) break;
                sum[0] += grid[i][j];
            }
        }
        for (int i = 0; i <= r + d2; i++) {
            for (int j = N - 1; j > c; j--) {
                if (div[i][j] == 5) break;
                sum[1] += grid[i][j];
            }
        }
        for (int i = r + d1; i < N; i++) {
            for (int j = 0; j < c - d1 + d2; j++) {
                if (div[i][j] == 5) break;
                sum[2] += grid[i][j];
            }
        }
        for (int i = r + d2 + 1; i < N; i++) {
            for (int j = N - 1; j >= c - d1 + d2; j--) {
                if (div[i][j] == 5) break;
                sum[3] += grid[i][j];
            }
        }
        sum[4] = totalPeople;
        for (int i = 0; i < 4; i++) sum[4] -= sum[i];

        Arrays.sort(sum);
        min = Math.min(min, sum[4] - sum[0]);
    }
}
