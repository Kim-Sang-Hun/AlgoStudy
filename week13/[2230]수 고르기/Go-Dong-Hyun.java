package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2230 {
	
	static int N;
	static long M,ans;
	static long[] arr;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		
		arr = new long[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		
		Arrays.sort(arr);
		
		ans = Long.MAX_VALUE;
		
		int start = 0;
		int end = 0;
		
		while (end < N && start < N) {
			long diff = arr[end] - arr[start];
			
			if (diff < M) {
				end++;
			} else {
				start++;
				ans = Math.min(ans, diff);
			}
			
		}
		
		System.out.println(ans);
		
	}
}
