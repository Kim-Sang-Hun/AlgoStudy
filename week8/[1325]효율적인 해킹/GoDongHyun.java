package week08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;


//이문제 병신임

public class BOJ1325 {
	
	static int N,M,maxCnt;
	static ArrayList<Integer>[] arr;
	static int[] visited;
	static int[] ans;
	static StringBuilder sb = new StringBuilder(); 
	
	/*
	 * private static void dfs(int idx) { visited[idx] = 1;
	 * 
	 * for (int x : arr[idx]) { if (visited[x] == 0) { visited[x] = 1; ans[x] +=1;
	 * dfs(x); } } }
	 */

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N];
		ans = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			arr[a].add(b);
		}
		
		
		for (int i = 0; i < N; i++) {
			visited = new int[N];
			bfs(i);
		}
		
		maxCnt = 0;
		
		for (int i = 0; i < N; i++) {
			if (maxCnt < ans[i])
				maxCnt = ans[i];
		}
		
		for (int i = 0; i < N; i++) {
			if (ans[i] == maxCnt) {
				sb.append(i+1).append(" ");
			}
		}
		
		System.out.println(sb);
	}

	private static void bfs(int idx) {
		
		visited[idx] = 1;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(idx);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int x : arr[cur]) {
				if (visited[x] == 0) {
					visited[x] = 1;
					ans[x]++;
					q.add(x);
				}
			}
		}
		
	}
}
