package Algo_week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3584 {
	
	static int T,N;
	static int[] parent;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			parent = new int[N+1];
			visited = new boolean[N+1];
			
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				parent[b] = a;
				
			}
			
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			sol(a);
			sol(b);
			
		}
	}

	private static void sol(int idx) {
		
		if (visited[idx]) {
			System.out.println(idx);
			return;
		}
		
		visited[idx] = true;
		
		if (idx == 0) return;
		
		sol(parent[idx]);
		
	}
}
