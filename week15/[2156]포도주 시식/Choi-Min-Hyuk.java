import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2156_포도주시식_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;

	public static void main(String[] args) throws Exception {
		n = Integer.parseInt(br.readLine());
		int[] wines = new int[n];
		int[] dp = new int[n];
		
		for (int i = 0; i < n; i++)
			wines[i] = Integer.parseInt(br.readLine());

		dp[0] = wines[0];

		for (int i = 1; i < n; i++) {
			if (i == 1) {
				dp[1] = wines[0] + wines[1];
				continue;
			}
			
			if (i == 2) {
				dp[2] = Math.max(dp[1], Math.max(wines[0] + wines[2], wines[1] + wines[2]));
				continue;
			}
			
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wines[i], dp[i - 3] + wines[i - 1] + wines[i]));
		}

		System.out.print(dp[n - 1]);
	}
}
