import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17404_RGB거리2_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, arr[][], dp[][];
	static final int MAX_VALUE = 1000 * 1000;
	static int answer = MAX_VALUE;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][3];
		dp = new int[N + 1][3];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// i가 0: RED, 1: GREEN, 2: BLUE로 첫 집을 칠하는 경우
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j)
					dp[1][j] = arr[1][j];
				else
					dp[1][j] = MAX_VALUE;
			}

			for (int j = 2; j <= N; j++) {
				dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + arr[j][0];
				dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + arr[j][1];
				dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + arr[j][2];
			}

			for (int j = 0; j < 3; j++)
				if (i != j)
					answer = Math.min(answer, dp[N][j]);
		}

		System.out.print(answer);
	}
}
