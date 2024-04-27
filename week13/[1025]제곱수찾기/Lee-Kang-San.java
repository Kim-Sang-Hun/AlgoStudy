package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <제곱수 찾기> G5
* 링크
* https://www.acmicpc.net/problem/1025
* 요약
* 
* 풀이
* 수열이 꼭 할 수 있는 최대 길이일 필요 없음 -> 하나 붙일 때마다 제곱수인지 판정 필요
*/
public class boj_1025 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M; // N행 M열 (1~9)
	static int[][] A; // 표
	static long maxSquare = -1;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				A[i][j] = Integer.parseInt((str.charAt(j - 1) - '0') + "");
			}
		}

		// 풀이
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) { // [i][j] 시작위치
				for (int di = -N; di <= N; di++) { // 행의 공차
					for (int dj = -M; dj <= M; dj++) { // 열의 공차
						if (di == 0 && dj == 0) {// 공차 둘 다 0이면 시작 위치에서 이동하지 않으므로 바로 제곱수 판별
							long root = (long) Math.sqrt(A[i][j]);
							if (root * root == A[i][j]) {
								maxSquare = Math.max(maxSquare, A[i][j]);
							}
							continue;
						} 
						StringBuilder numberString = new StringBuilder();
						numberString.append(A[i][j]);
						int ni = i + di;
						int nj = j + dj;
						while (isIn(ni, nj)) {
							numberString.append(A[ni][nj]);
							// 숫자 하나 이을 때마다 제곱수 인지 판정하고 최대값 갱신
							long currentNum = Long.parseLong(numberString.toString());
							long root = (long) Math.sqrt(currentNum);
							if (root * root == currentNum) {
								maxSquare = Math.max(maxSquare, currentNum);
							}
							ni += di;
							nj += dj;
						}
					}
				}
			}
		}
		// 출력
		System.out.println(maxSquare);
	}

	private static boolean isIn(int ni, int nj) {
		if (1 <= ni && ni <= N && 1 <= nj && nj <= M)
			return true;
		return false;
	}
}
