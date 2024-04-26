import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1025_제곱수찾기 {
    static int N, M;
    static int[][] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                nums[i][j] = line.charAt(j) - 48;
            }
        }

        int ans = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int di = -N; di < N; di++) {
                    for (int dj = -M; dj < M; dj++) {
                        if (di == 0 && dj == 0) {
                            continue;
                        }

                        int num = 0;
                        int nextRow = i;
                        int nextCol = j;

                        while (nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M) {
                            num = num * 10 + nums[nextRow][nextCol];

                            double sqrtNum = Math.sqrt(num);
                            if ((int) sqrtNum == sqrtNum) {
                                ans = Math.max(ans, num);
                            }

                            nextRow += di;
                            nextCol += dj;
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
