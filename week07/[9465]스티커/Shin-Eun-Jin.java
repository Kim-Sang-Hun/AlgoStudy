
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int N;
	static int[][] sticker;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			sticker = new int[2][N + 1];
			memo = new int[2][N + 1];

			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dp();

			System.out.println(Math.max(memo[0][N], memo[1][N]));
		}

	}

	static void dp() {
		memo[0][0] = 0;
		memo[1][0] = 0;
		memo[0][1] = sticker[0][1];
		memo[1][1] = sticker[1][1];

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 2; j++) {
				memo[j][i] = Math.max(memo[j ^ 1][i - 1], memo[j ^ 1][i - 2]) + sticker[j][i];
			}
		}

	}

}
