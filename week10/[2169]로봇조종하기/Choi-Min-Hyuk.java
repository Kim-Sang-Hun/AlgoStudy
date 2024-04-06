import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2169_로봇조종하기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int[][] map;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		dp = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[0][0] = map[0][0];

		// 첫번째 배열은 무조건 왼쪽으로만 가능
		for (int j = 1; j < M; j++) {
			dp[0][j] = map[0][j] + dp[0][j - 1];
		}

		for (int i = 1; i < N; i++) {
			// 왼쪽이랑 위쪽에서 오는 것중 최대값 저장
			int[] leftDp = new int[M];
			leftDp[0] = dp[i - 1][0] + map[i][0];

			for (int j = 1; j < M; j++) {
				leftDp[j] = Math.max(leftDp[j - 1], dp[i - 1][j]) + map[i][j];
			}

			// 오른쪽쪽이랑 위쪽에서 오는 것중 최대값 저장
			int[] rightDp = new int[M];
			rightDp[M - 1] = dp[i - 1][M - 1] + map[i][M - 1];

			for (int j = M - 2; j >= 0; j--) {
				rightDp[j] = Math.max(rightDp[j + 1], dp[i - 1][j]) + map[i][j];
			}

			// 그 중 최대값으로 저장
			for (int j = 0; j < M; j++) {
				dp[i][j] = Math.max(leftDp[j], rightDp[j]);
			}
		}

		System.out.print(dp[N - 1][M - 1]);
	}
}
