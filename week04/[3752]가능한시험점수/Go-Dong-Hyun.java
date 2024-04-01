package Algo_week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA3752 {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			arr = new int[N];
			int maxSum = 0;
			
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				maxSum += arr[i];
			}
			
			boolean[] dp = new boolean[maxSum+1];
			dp[0] = true;
			int cnt = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = maxSum; j >= 0; j--) {
					if (dp[j]) {
						dp[j+arr[i]] = true;
					}
				}
			}
			
			for (boolean b : dp) {
				if(b) {
					cnt++;
				}
			}
			
			System.out.println("#" + t + " " + cnt);
		}
	}

}
