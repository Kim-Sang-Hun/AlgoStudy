package notFin;

import java.io.*;
import java.util.*;

/*
* 제목
* <종이 조각> G3
* 링크
* https://www.acmicpc.net/problem/14391
* 요약
* 
* 풀이
* dfs
* 메모리
* 35440KB
* 시간
* 808ms
*/
public class boj_14391 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int N, M;
	static int[][] arr;
	static boolean[][] visit;

	static int maxSum = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(str.charAt(j) + "");
				visit[i][j] = false;
			}
		}

		// 풀이
		dfs(0); // sum

		// 출력
		bw.write(maxSum+"");
		bw.flush();
	}

	private static void dfs(int sum) {
		if (wholeChecked()) {
			maxSum = Math.max(maxSum, sum);
			return;
		} else {
			// 시작위치 찾기
			int ci = -1, cj = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!visit[i][j]) {
						ci = i;
						cj = j;
						break;
					}
				}
				if (ci != -1 && cj != -1) {
					break;
				}
			}

			// 오른쪽으로 쭉
			int currentRightSum = 0;
			int ej = -1;
			for (int j = cj; j < M; j++) {
				if (!visit[ci][j]) {
					for (int sj = cj; sj <= j; sj++)
						visit[ci][sj] = true;
					currentRightSum += arr[ci][j];
					dfs(sum + currentRightSum);
					for (int sj = cj; sj <= j; sj++)
						visit[ci][sj] = false;
					currentRightSum *= 10;
				} else {
					ej = j;
					break;
				}
			}

			// 밑으로 쭉
			int currentDownSum = 0;
			int ei = -1;
			for (int i = ci; i < N; i++) {
				if (!visit[i][cj]) {
					for (int si = ci; si <= i; si++)
						visit[si][cj] = true;
					currentDownSum += arr[i][cj];
					dfs(sum + currentDownSum);
					for (int si = ci; si <= i; si++)
						visit[si][cj] = false;
					currentDownSum *= 10;
				} else {
					ei = i;
					break;
				}
			}
		}
	}

	private static boolean wholeChecked() {
		boolean check = true;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visit[i][j]) {
					check = false;
					break;
				}
			}
			if (!check) {
				break;
			}
		}
		return check;
	}
}
