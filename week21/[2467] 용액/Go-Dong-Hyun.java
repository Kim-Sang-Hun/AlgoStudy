package Algo_week21;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2467 {
	
	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = N-1;
		int left_ans = 0;
		int right_ans = N-1;
		
		long cnt = arr[left] + arr[right];
		
		while (left < right) {
			long sum = arr[left] + arr[right];
			
			if (Math.abs(sum) < Math.abs(cnt)) {
				cnt = sum;
				left_ans = left;
				right_ans = right;
			}
			
			if (sum > 0) {
				right--;
			} else {
				left++;
			}
		}
		
		System.out.println(arr[left_ans] + " " + arr[right_ans]);
		
	}
}
