package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <파티> G3
* 링크
* https://www.acmicpc.net/problem/1238
* 요약
* 
* 풀이
* 플로이드 워셜
*/
public class boj_1238 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static final int INF = 123456789;

	static int N, M, X; // 학생수 N, 도로 개수 M, 파티 열린 마을 번호 X
	static int[][] arr; // 인접행렬
	static int[] distToX;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(arr[i], INF);
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			arr[from][to] = weight;
		}
		
		// 풀이
//		printArr();
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if(i==j) continue;
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		
//		printArr();

		distToX = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			if(i==X) {
				distToX[i] = 0; // X번 마을에 사는 학생은 거리 0
				continue;
			}
			distToX[i] = arr[i][X] + arr[X][i];
		}
		
//		System.out.println(Arrays.toString(distToX));
		Arrays.sort(distToX);
		
		// 출력
		System.out.println(distToX[N]);
	}

	private static void printArr() {
		for(int i=1; i<=N; i++) {
			for (int j = 1; j <= N; j++) {
				if(arr[i][j]==INF) {
					System.out.print("x ");
				} else {
					System.out.print(arr[i][j]+" ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}
