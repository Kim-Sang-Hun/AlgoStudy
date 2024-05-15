package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <개근상> G4
* 링크
* https://www.acmicpc.net/problem/1563
* 요약
* 지각 횟수, 연속 결석 횟수 정보 유지 필요 -> 상태 전이 그래프 그려보기
* 풀이
* dp
*/
public class boj_1563 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static final long MOD = 1000000;
	static int N;
	static long[][] dp;

	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine().trim());

		dp = new long[6][N + 1];
		// 다음 = 출석 / 결석 / 지각
		// [0] : 연결0, 지각0 -> 0, 1, 3
		// [1] : 연결1, 지각0 -> 0, 2, 3
		// [2] : 연결2, 지각0 -> 0, 3
		// [3] : 연결0, 지각1 -> 3, 4
		// [4] : 연결1, 지각1 -> 3, 5
		// [5] : 연결2, 지각1 -> 3

		// 풀이
		dp[0][1] = 1;
		dp[1][1] = 1; 
		dp[2][1] = 0; 
		dp[3][1] = 1; 
		dp[4][1] = 0; 
		dp[5][1] = 0; 

		for (int i = 2; i <= N; i++) {
			dp[0][i] = (dp[0][i-1] + dp[1][i-1] + dp[2][i-1]) % MOD;
			dp[1][i] = (dp[0][i-1]) % MOD;
			dp[2][i] = (dp[1][i-1]) % MOD;
			dp[3][i] = (dp[0][i-1] + dp[1][i-1] + dp[2][i-1] + dp[3][i-1] + dp[4][i-1] + dp[5][i-1]) % MOD;
			dp[4][i] = (dp[3][i-1]) % MOD;
			dp[5][i] = (dp[4][i-1]) % MOD;
		}
		
		//출력
		long sum = 0;
		for(int i=0; i<6; i++) {
			sum += dp[i][N];
		}
		sum %= MOD;
		System.out.println(sum);

	}
}
