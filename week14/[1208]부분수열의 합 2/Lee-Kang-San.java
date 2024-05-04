import java.io.*;
import java.util.*;

/*
* 제목
* <부분수열의 합 2> G1
* 링크
* https://www.acmicpc.net/problem/1208
* 요약
* -
* 풀이
* 2^40 -> 2^20, 2^20, 수열 반 나눠서 각각 부분집합들을 구하고 S 만족하는 합 찾기
*/
public class boj_1208 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, S; // 수열 이루는 정수 개수 N, 목표합 S

	static int[] arr1;
	static int[] arr2;
	static int n1, n2; // 배열 크기

	static ArrayList<Integer> list1 = new ArrayList<>(); // 모든 부분집합 저장 2^n1개 (최대 100만)
	static ArrayList<Integer> list2 = new ArrayList<>(); // 모든 부분집합 저장 2^n2개 (최대 100만)

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		n1 = N / 2;
		n2 = n1;
		if (N % 2 == 1)
			n2++;

		arr1 = new int[n1];
		arr2 = new int[n2];

		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < n1; i++) {
			arr1[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < n2; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}

		// 풀이
		subset(list1, arr1, n1, 0, 0);
		subset(list2, arr2, n2, 0, 0);
		
		Collections.sort(list1);
		Collections.sort(list2);

		long cnt = 0;
		int p1 = 0, p2 = list2.size()-1;
		while(p1 < list1.size() && 0 <= p2) {
			long res = list1.get(p1) + list2.get(p2);
			if(res == S) {
				long cnt1 = 0;
				long leftVal = list1.get(p1);
				while (p1 < list1.size() && list1.get(p1) == leftVal) {
					p1++;
					cnt1++;
				}
				
				long cnt2 = 0;
				long rightVal = list2.get(p2);
				while (0 <= p2 && list2.get(p2) == rightVal) {
					p2--;
					cnt2++;
				}				
				cnt += cnt1 * cnt2;
				
			} else if(res < S) {
				p1++;
			} else { // res > S
				p2--;
			}
		}

		// 목표합이 0일 때, 양 쪽 모두에서 아무것도 선택하지 않는 경우 제외
		if (S == 0)
			cnt--;

		// 출력
		bw.write(cnt + "");
		bw.flush();
	}

	private static void subset(ArrayList<Integer> list, int[] arr, int size, int depth, int sum) {
		if (depth == size) {
			list.add(sum);
			return;
		}
		subset(list, arr, size, depth + 1, sum); // i번째 원소 미포함
		subset(list, arr, size, depth + 1, sum + arr[depth]); // i번째 원소 포함
	}
}
