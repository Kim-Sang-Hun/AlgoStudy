package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <로봇 조종하기> G2
* 링크
* https://www.acmicpc.net/problem/2169
* 요약
* [1][1] 시작
* 왼쪽, 오른쪽, 아래쪽으로만 이동
* 한 번 탐사한 지역은 재방문 x
* [N][M] 종료 
* 탐사한 지역들의 합이 최대가 되는 경우의 값 출력
* 풀이
* dp
*/
public class boj_2169 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static int[][] mars;
	static int[][] maxVal;

	static int[] di = { 1, 0, 0 }; // 하 좌 우
	static int[] dj = { 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		mars = new int[N + 1][M + 2];
		maxVal = new int[N + 1][M + 2];

		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 1; j <= M; ++j) {
				mars[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 풀이
		dp();

		// 출력
		bw.write(maxVal[N][M]+"");
		bw.flush();
	}

	private static void dp() {
		maxVal[1][1] = mars[1][1];

		// 1행에서는 왼쪽에서만 [1][j]칸에 도착 가능
		for(int j=2; j<=M; ++j) {
			maxVal[1][j] += maxVal[1][j-1] + mars[1][j];
		}
		
		 
		// 2행 ~ N행은 좌우에서 오는 경우 고려 필요
		for(int i=2; i<=N; ++i) {
			int[] leftStart = new int[M+1];
			int[] rightStart = new int[M+1];
			// 좌 -> 우
			leftStart[1] = maxVal[i-1][1] + mars[i][1];
			for(int j=2; j<=M; ++j) {
				leftStart[j] = Math.max(maxVal[i-1][j], leftStart[j-1]) + mars[i][j];
			}
			
			// 우 -> 좌
			rightStart[M] = maxVal[i-1][M] + mars[i][M];
			for(int j=M-1; j>=1; --j) {
				rightStart[j] = Math.max(maxVal[i-1][j], rightStart[j+1]) + mars[i][j];
			}
			
			// 더 큰 값으로 dp 채우기
			for(int j=1; j<=M; ++j) {
				maxVal[i][j] = Math.max(leftStart[j], rightStart[j]);
			}
		}
	}
}
