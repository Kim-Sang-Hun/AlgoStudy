package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <스티커> S1
 * 링크
 * https://www.acmicpc.net/problem/9465
 * 요약
 * 2행으로 이루어진 2n개의 스티커를 서로 인접한 스티커 없이 선택했을 때, 값의 최대치 구하기
 * 풀이
 * dp
 * i번째 위치에서 위, 아래 우표 선택 시 가능한 경우로 나누어 생각하기
 * i번째 위치에서 위쪽 우표 선택 시 가능한 경우의 수
 * 		직전(i-1)에서 아래쪽 선택+현재(i) 위쪽 선택
 * 		직전(i-1)에서 선택x, 그 전에서(i-2) 위쪽 선택했을 때 최댓값 + 현재(i) 위쪽 선택
 * 		두 경우 중 더 큰 값이 i번째 위치에서 위쪽 우표 선택 시 얻을 수 있는 최댓값
 */
public class boj_9465 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int T; // 테스트 케이스 개수
	static int n; // 열 개수
	static int[][] arr;
	static int[][] dp;
	static int maxScore;
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc=0; tc<T; tc++) {
			// 입력
			n = Integer.parseInt(br.readLine());
			arr = new int[2][n];
			dp = new int[2][n];
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// 풀이
			dp();
			// 출력
			sb.append(maxScore+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dp() {
		dp[0][0] = arr[0][0];
		dp[1][0] = arr[1][0];
		if(n>1) {
			dp[0][1] = dp[1][0] + arr[0][1];
			dp[1][1] = dp[0][0] + arr[1][1];
		}
		for(int i=2; i<n; i++) { 
			dp[0][i] = Math.max(dp[1][i-1]+arr[0][i], dp[1][i-2]+arr[0][i]); // i번째 위치에서 위쪽 우표 선택 시 최대값 
			dp[1][i] = Math.max(dp[0][i-1]+arr[1][i], dp[0][i-2]+arr[1][i]); // i번째 위치에서 아래쪽 우표 선택 시 최대값
		}
		maxScore = Math.max(dp[0][n-1], dp[1][n-1]);
	}
}
