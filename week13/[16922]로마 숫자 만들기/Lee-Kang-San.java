package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <로마 숫자 만들기> S3
* 링크
* https://www.acmicpc.net/problem/16922
* 요약
* 
* 풀이
* bt
*/
public class boj_16922 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static boolean[] canMake;
	static int[] roman = { 1, 5, 10, 50 };

	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine().trim());
		canMake = new boolean[50 * N + 1];
		// 풀이
		bt(0, 0, 0); // depth, sum
		// 출력
		int ans = getAns();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void bt(int depth, int start, int currentSum) {
		if (depth == N) {
			canMake[currentSum] = true;
			return;
		}
		for (int i = start; i < 4; i++) {
			bt(depth + 1, i, currentSum + roman[i]);
		}
	}

	private static int getAns() {
		int cnt = 0;
		for (boolean b : canMake) {
			if (b) {
				cnt++;
			}
		}
		return cnt;
	}
}
