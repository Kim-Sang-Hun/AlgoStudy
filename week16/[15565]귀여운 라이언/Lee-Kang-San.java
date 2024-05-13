import java.io.*;
import java.util.*;

/*
* 제목
* <귀여운 라이언> S1
* 링크
* https://www.acmicpc.net/problem/15565
* 요약
* 수열에서 1이 K개 이상인 가장 작은 연속된 부분 수열의 크기 구하기
* 풀이
* 투포인터
*/
public class boj_15565 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	// 라이언 = 1, 어피치 = 2
	static final int RYAN = 1;

	static int N, K; // 인형 N개, 기준 라이언 개수 K개
	static int[] arr;
	static int ryanCheck = 0;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] == RYAN) {
				ryanCheck++;
			}
		}

		// 예외처리 : 라이언이 K개 미만인 경우
		if (ryanCheck < K) {
			System.out.println("-1");
			return;
		}

		// 풀이
		int p = 0, q = 0;
		int ryanCount = 0;
		int minLength = Integer.MAX_VALUE;

		while (q < N) {
			if (arr[q] == RYAN) {
				ryanCount++;
			}

			while (ryanCount == K) {
				if (arr[p] == RYAN) {
					ryanCount--;
				}
				minLength = Math.min(minLength, q - p + 1);
				p++;
			}
			q++;
		}

		// 출력
		System.out.println(minLength);

	}
}
