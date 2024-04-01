package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ9465 {
	
	//N이 1,2일때 예외처리해야됨 ㄹㅇ벌레문제
	
	static int N;
	static int[][] arr,dp;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			arr = new int[2][N];
			dp = new int[2][N];
			
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];
			if (N == 1) {
				System.out.println(Math.max(dp[0][0], dp[1][0]));
				continue;
			}
			dp[0][1] = dp[1][0] + arr[0][1];
			dp[1][1] = dp[0][0] + arr[1][1];
			if (N == 2) {
				System.out.println(Math.max(dp[1][1], dp[0][1]));
				continue;
			}
			
			for (int i = 2; i < N; i++) {
				int down = i%2;
				int up = Math.abs(down-1);
				dp[up][i] = Math.max(dp[down][i-1], dp[down][i-2]) + arr[up][i];
				dp[down][i] = Math.max(dp[up][i-1], dp[up][i-2]) + arr[down][i];
			}
			
			System.out.println(Math.max(dp[0][N-1], dp[1][N-1]));
			
		}
		
	}
}
