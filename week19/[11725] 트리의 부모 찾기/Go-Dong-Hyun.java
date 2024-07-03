package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ11725 {
	
	static int N;
	static ArrayList<Integer>[] arr;
	static Queue<Integer> q;
	static boolean[] visited;
	static int[] ans;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList[N+1];
		ans = new int[N+1];
		
		for (int i = 0; i < N+1; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a].add(b);
			arr[b].add(a);
		}
		
		bfs(1);
		
		for (int i = 2; i < N+1; i++) {
			System.out.println(ans[i]);
		}
		
	}

	private static void bfs(int idx) {
		
		q = new ArrayDeque<Integer>();
		visited = new boolean[N+1];
		visited[1] = true;
		
		q.add(idx);
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			for (Integer i : arr[cur]) {
				if (visited[i]) continue;
				
				visited[i] = true;
				ans[i] = cur;
				q.add(i);
			}
		}
		
	}
}
