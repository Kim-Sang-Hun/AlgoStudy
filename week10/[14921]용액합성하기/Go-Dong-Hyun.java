package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14921 {
	
	static int N;
	static int[] arr;
	static int ans;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		ans = Integer.MAX_VALUE;
		
		int l = 0;
		int r = N-1;
		int sub_ans;
		
		while (true) {
			if (l == r) break;
			
			sub_ans = arr[l] + arr[r];
			if (Math.abs(sub_ans) < Math.abs(ans))
				ans = sub_ans;
			
			if (ans == 0) break;
			
			if ( (Math.abs(arr[l+1] + arr[r]) < Math.abs(arr[l] + arr[r-1])) ) {
				l++;
			} else {
				r--;
			}
			
		}
		
		System.out.println(ans);
	}
}
