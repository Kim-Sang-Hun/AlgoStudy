package Algo_week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17404 {
	
	static int N;
	static int[][] arr,dp;
	static int[] paint;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][3];
		dp = new int[N][3];
		paint = new int[3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) {
					dp[0][j] = arr[0][j];
				}
				else { 
					dp[0][j] = 1000;
				}
			}
			
			for (int j = 1; j < N; j++) {
				dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + arr[j][0];
				dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + arr[j][1];
				dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + arr[j][2];
				if (j == N-1) {
					if (i == 0) {
						paint[i] = Math.min(dp[N-1][1], dp[N-1][2]);
					} else if (i == 1) {
						paint[i] = Math.min(dp[N-1][0], dp[N-1][2]);
					} else if (i == 2) {
						paint[i] = Math.min(dp[N-1][0], dp[N-1][1]);
					}
				}
			}
			
		}
		
		System.out.println(Math.min(paint[0], Math.min(paint[1], paint[2])));
		
	}
}
