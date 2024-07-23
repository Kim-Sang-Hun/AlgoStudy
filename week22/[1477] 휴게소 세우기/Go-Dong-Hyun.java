package Algo_week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1477 {
	
	static int N,M,L;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		arr = new int[N+2];
		arr[0] = 0;
		arr[N+1] = L;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		int left = 1;
		int right = L-1;
		
		while (left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;
			
			for (int i = 1; i < N+2; i++) {
				sum += (arr[i] - arr[i-1] -1) / mid;
			}
			
			if (sum > M) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		
		System.out.println(left);
		
	}
}
