import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int N;
	static int[][] map;
	static long[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		memo = new long[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp();

		System.out.println(memo[N - 1][N - 1]);

	}

	static void dp() {
		memo[0][0] = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (memo[i][j] == 0 || (i == N - 1 && j == N - 1))
					continue;

				// 아래
				int nextRow = i + map[i][j];
				if (nextRow < N) {
					memo[nextRow][j] += memo[i][j];
				}

				// 오른쪽
				int nextCol = j + map[i][j];
				if (nextCol < N) {
					memo[i][nextCol] += memo[i][j];

				}
			}
		}

	}

}
