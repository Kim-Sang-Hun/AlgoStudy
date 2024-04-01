import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ9465_스티커_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();
	static int T, n, stickerScores[][];
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			n = Integer.parseInt(br.readLine());
			stickerScores = new int[2][n + 1];
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++)
					stickerScores[i][j] = Integer.parseInt(st.nextToken());
			}
			
			// dp[i][j]: 1열부터 j열까지 순차적으로 고를 때 i행 j열 스티커를 골랐을 때 스티커의 점수의 최댓값
			int dp[][] = new int[2][n + 1];
			dp[0][0] = dp[1][0] = 0;
			dp[0][1] = stickerScores[0][1];
			dp[1][1] = stickerScores[1][1];
			
			for (int i = 2; i <= n; i++) {
				dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + stickerScores[0][i];
				dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + stickerScores[1][i];
			}
			
			sb.append(Math.max(dp[0][n], dp[1][n])).append(lineSeparator);
		}
		
		System.out.print(sb);
	}
}
