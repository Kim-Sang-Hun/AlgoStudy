package Algo_week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2293 {
	
	static int N,K;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		dp = new int[K+1];
		
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = num;
			
		}
		
		dp[0] = 1;
		Arrays.sort(arr);
		
		for (int i = 0; i < N; i++) {
			for (int j = arr[i]; j <= K ; j++) {
				dp[j] += dp[j - arr[i]];
			}
		}
		
		System.out.println(dp[K]);
		
	}
}
