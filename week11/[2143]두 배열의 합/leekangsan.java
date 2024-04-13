package BOJ;

import java.io.*;
import java.util.*;
/*
* 제목
* <두 배열의 합> G3
* 링크
* https://www.acmicpc.net/problem/2143
* 요약
* 
* 풀이
* 누적합, 이분탐색
*/

public class boj_2143 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static long T; // -1,000,000,000 ≤ T ≤ 1,000,000,000
	static int n; // A배열 원소 개수, 1 ≤ n ≤ 1,000
	static int[] A;
	static int m; // B배열 원소 개수, 1 ≤ m ≤ 1,000
	static int[] B;

	static long[] dpA; // A배열 누적합
	static long[] dpB; // B배열 누적합

	static ArrayList<Long> listA = new ArrayList<>(); // A배열의 부배열 모든 경우 저장
	static ArrayList<Long> listB = new ArrayList<>(); // B배열의 부배열 모든 경우 저장

	public static void main(String[] args) throws IOException {
		// 입력
		T = Integer.parseInt(br.readLine().trim());
		n = Integer.parseInt(br.readLine().trim());
		A = new int[n + 1];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 1; i <= n; i++)
			A[i] = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine().trim());
		B = new int[m + 1];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 1; i <= m; i++)
			B[i] = Integer.parseInt(st.nextToken());

		// 풀이
		dpA = new long[n + 1];
		dpB = new long[m + 1];
		for (int i = 1; i <= n; i++)
			dpA[i] = dpA[i - 1] + A[i];
		for (int i = 1; i <= m; i++)
			dpB[i] = dpB[i - 1] + B[i];

		// 모든 부배열 경우 저장
		getWholeSubArray();

		// T 되는 경우 구하기
		solution();

		// 출력
		bw.write(ansCnt + "");
		bw.flush();
	}

	static long ansCnt = 0;
	private static void solution() {
		for (long num : listA) {
			long target = T - num;
			ansCnt += getRange(target);
		}
	}

	// target = T - sub(A)
	private static long getRange(long target) {
		int start = 0;
		int end = listB.size() - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (listB.get(mid) >= target) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		long left = end;

		start = 0;
		end = listB.size() - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (listB.get(mid) <= target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		long right = end;
		return right - left;
	}

	// 모든 부배열 경우 저장
	private static void getWholeSubArray() {
		for (int startA = 1; startA <= n; startA++) {
			for (int endA = startA; endA <= n; endA++) {
				listA.add(subSum('A', startA, endA));
			}
		}
		Collections.sort(listA);

		for (int startB = 1; startB <= m; startB++) {
			for (int endB = startB; endB <= m; endB++) {
				listB.add(subSum('B', startB, endB));
			}
		}
		Collections.sort(listB);
	}

	// 부배열 합
	private static long subSum(char c, int start, int end) {
		if (c == 'A') {
			return dpA[end] - dpA[start] + A[start];
		} else { // c=='B'
			return dpB[end] - dpB[start] + B[start];
		}
	}
}
