package week14;

import java.io.*;
import java.util.*;

public class BOJ9466 {
	
	static int T,N,ans;
	static int[] arr;
	static boolean[] visited, finish;
	static boolean flag;

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; ++t) {
			
			N = Integer.parseInt(br.readLine());
			ans = 0;
			arr = new int[N];
			visited = new boolean[N];
			finish = new boolean[N];
			
			st = new StringTokenizer(br.readLine());
			
			for (int i = 0; i < N; ++i) {
				arr[i] = Integer.parseInt(st.nextToken()) - 1;
				if (arr[i] == i) {
					finish[i] = true;
					ans++;
				}
			}
			
			for (int i = 0; i < N; ++i) {
				if (!finish[i]) {
					sol(i);
				}
			}
			
			System.out.println(N-ans);
		}
	}

	private static void sol(int idx) {
		
		if (visited[idx]) {
			finish[idx] = true;
			ans++;
		} else {
			visited[idx] = true;
		}
		
		if (!finish[arr[idx]]) {
			sol(arr[idx]);
		}
		
		visited[idx] = false;
		finish[idx] = true;
		
	}
}
