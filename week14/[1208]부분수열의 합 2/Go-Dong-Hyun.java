package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ1208 {

	static int N, S;
	static int[] arr;
	static ArrayList<Integer> leftList, rightList;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		leftList = new ArrayList<Integer>();
		rightList = new ArrayList<Integer>();

		findCnt(0, N / 2, 0, leftList);
		findCnt(N / 2, N, 0, rightList);

		Collections.sort(leftList);
		Collections.sort(rightList);

		long ans = 0;
		int left = 0;
		int right = rightList.size() - 1;

		while (left < leftList.size() && right >= 0) {
			long cnt = leftList.get(left) + rightList.get(right);

			if (cnt == S) {
				long leftVal = leftList.get(left);
				long rightVal = rightList.get(right);

				long leftCnt = 0;

				while (left < leftList.size() && leftList.get(left) == leftVal) {
					leftCnt++;
					left++;
				}

				long rightCnt = 0;
				while (right >= 0 && rightList.get(right) == rightVal) {
					rightCnt++;
					right--;
				}
				
				ans += leftCnt * rightCnt;
				
			} else if (cnt < S) {
				left++;
			} else if (cnt > S) {
				right--;
			}
		}
		
		if (S == 0) {
			System.out.println(ans-1);
		} else {
			System.out.println(ans);
		}

	}

	private static void findCnt(int idx, int end, int cnt, ArrayList<Integer> list) {

		if (idx == end) {
			list.add(cnt);
			return;
		}

		findCnt(idx + 1, end, cnt + arr[idx], list);
		findCnt(idx + 1, end, cnt, list);

	}
}
