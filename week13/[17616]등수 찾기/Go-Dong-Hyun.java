package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1025 {
	
	static int N,M,X;
	static int U,V;
	static ArrayList[] top, bottom;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken())-1;
		
		top = new ArrayList[N];
		bottom = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			top[i] = new ArrayList<Integer>();
			bottom[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			top[b].add(a);
			bottom[a].add(b);
		}
		
		int ans1 = bfs(X, top);
		int ans2 = bfs(X, bottom);
		
		System.out.println((ans1+1) + " " + (N-ans2));
	}

	private static int bfs(int x, ArrayList<Integer>[] arr) {
		
		visited = new boolean[N];
		visited[x] = true;
		int cnt = 0;
		
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.add(x);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (Integer num: arr[cur]) {
				if (!visited[num]) {
					cnt++;
					visited[num] = true;
					q.add(num);
				}
			}
		}
		
		return cnt;
	}
}
