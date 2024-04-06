package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <용액 합성하기> G5
* 링크
* https://www.acmicpc.net/problem/14921
* 요약
* 두 용액의 합이 0과 가장 가까운 경우 구하기
* 풀이
* 투 포인터
*/
public class boj_14921 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 용액 개수 N
	static int[] liquid;
	static int min = Integer.MAX_VALUE;
	static int minP, minQ;
	static int p, q; // pivots for two pointer algorithm

	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine().trim());
		liquid = new int[N + 1]; // A[1], A[2], .... A[N] 용액
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 1; i <= N; ++i)
			liquid[i] = Integer.parseInt(st.nextToken());
		// 풀이
		selectLiquid();
		// 출력
		bw.write(min + "");
		bw.flush();
	}

	private static void selectLiquid() {
		p = 1;
		q = N;
		while(p<q) {
			int sum = liquid[p] + liquid[q]; // 두 용액의 특성값
			if(Math.abs(min) > Math.abs(sum)) {
				min = sum;
			}
			if(sum == 0) {
				break;
			} else if(sum > 0) {
				q--;
			} else {
				p++;
			}
		}
	}
}
