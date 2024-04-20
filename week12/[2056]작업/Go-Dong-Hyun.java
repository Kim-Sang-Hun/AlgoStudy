package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2056 {
	
	static int N;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		dp = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			int time = Integer.parseInt(st.nextToken());
			dp[i] = time;
			
			int n = Integer.parseInt(st.nextToken());
			if (n == 0) continue;
			
			for (int j = 0; j < n; j++) {
				int before = Integer.parseInt(st.nextToken())-1;
				dp[i] = Math.max(dp[i], dp[before]+time);
			}
		}
		
		int ans = 0;
		for (int i = 0; i < N; i++) {
			if (ans < dp[i]) {
				ans = dp[i];
			}
		}
		
		System.out.println(ans);
	}
}
