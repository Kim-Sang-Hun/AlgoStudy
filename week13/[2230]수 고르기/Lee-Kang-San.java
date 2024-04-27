package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <수 고르기> G5
* 링크
* https://www.acmicpc.net/problem/2230
* 요약
* 
* 풀이
* 투 포인터
*/
public class boj_2230 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static int[] A;
	static int minM = Integer.MAX_VALUE;
	static int p, q; // pivot

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken()); // [1]~[N]
		M = Integer.parseInt(st.nextToken()); // 최소 숫자 차이
		A = new int[N];

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(br.readLine().trim());
		}

		// 풀이
		Arrays.sort(A);
		p = q = 0;
		while (p < N && q < N) {
			int curM = A[q] - A[p];
			if(curM < M) {
				q++;
			} else {
				minM = Math.min(minM, curM);
				p++;
			}
		}

		// 출력
		bw.write(minM + "");
		bw.flush();
	}
}
