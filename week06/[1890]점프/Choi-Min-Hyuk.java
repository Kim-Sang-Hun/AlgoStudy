import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1890_점프_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		// dp[i][j]: i행과 j열에 해당하는 칸까지 이동하는 경로의 개수
		long[][] dp = new long[N + 1][N + 1];
		dp[1][1] = 1;

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 1; j <= N; j++) {
				int jumpDistance = Integer.parseInt(st.nextToken());
				
				// 현재 자리에 올 수 있는 경우가 있고 점프할 수 있을 때
				if (dp[i][j] >= 1 && jumpDistance != 0) {
					// 오른쪽으로 점프할 수 있으면
					if (j + jumpDistance <= N)
						dp[i][j + jumpDistance] += dp[i][j];
					// 아래쪽으로 점프할 수 있으면
					if (i + jumpDistance <= N)
						dp[i + jumpDistance][j] += dp[i][j];
				}
			}
		}
		
		System.out.print(dp[N][N]);
	}
}
