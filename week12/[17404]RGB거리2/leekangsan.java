package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <RGB거리 2> G4
* 링크
* https://www.acmicpc.net/problem/17404
* 요약
* 
* 풀이
* dp
* 1번째 집을 R, G, B로 칠할 때 각각 고려하기
*/
public class boj_17404 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[][] arr = new int[1001][3];
	static int[][] dp = new int[1001][3];
	static int minCost = Integer.MAX_VALUE;

	static final int R = 0, G = 1, B = 2;
	static final int INF = 12345678;

	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine().trim());
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			arr[i][R] = Integer.parseInt(st.nextToken());
			arr[i][G] = Integer.parseInt(st.nextToken());
			arr[i][B] = Integer.parseInt(st.nextToken());
		}
		
		// 풀이
		dynamicProgramming();
		
		// 출력
		sb.append(minCost);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dynamicProgramming() throws IOException {
		// 1번 집 = R
		dp[1][R] = arr[1][R];
		dp[1][G] = INF;
		dp[1][B] = INF;
		for (int i = 2; i <= N; i++) {
			dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B]) + arr[i][R];
			dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B]) + arr[i][G];
			dp[i][B] = Math.min(dp[i-1][R], dp[i-1][G]) + arr[i][B];
		}
		minCost = Math.min(Math.min(minCost, dp[N][G]), dp[N][B]);
		
		// 1번 집 = G
		dp[1][R] = INF;
		dp[1][G] = arr[1][G];
		dp[1][B] = INF;
		for (int i = 2; i <= N; i++) {
			dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B]) + arr[i][R];
			dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B]) + arr[i][G];
			dp[i][B] = Math.min(dp[i-1][R], dp[i-1][G]) + arr[i][B];
		}
		minCost = Math.min(Math.min(minCost, dp[N][R]), dp[N][B]);
		
		// 1번 집 = B
		dp[1][R] = INF;
		dp[1][G] = INF;
		dp[1][B] = arr[1][B];
		for (int i = 2; i <= N; i++) {
			dp[i][R] = Math.min(dp[i-1][G], dp[i-1][B]) + arr[i][R];
			dp[i][G] = Math.min(dp[i-1][R], dp[i-1][B]) + arr[i][G];
			dp[i][B] = Math.min(dp[i-1][R], dp[i-1][G]) + arr[i][B];
		}
		minCost = Math.min(Math.min(minCost, dp[N][R]), dp[N][G]);
	}
}
