package Algo_week08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ1167 {
	
	static class Edge {
		int to;
		int weight;
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int V;
	static ArrayList<Edge>[] arr;
	static int ans;
	static int maxVertex;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		V = Integer.parseInt(br.readLine());
		arr = new ArrayList[V];
		
		for (int i = 0; i < V; i++) {
			arr[i] = new ArrayList<Edge>();
		}
		
		for (int i = 0; i < V; i++) {
			st = new StringTokenizer(br.readLine());
			
			int vertex = Integer.parseInt(st.nextToken())-1;
			
			while (true) {
				int a = Integer.parseInt(st.nextToken())-1;
				if (a == -2) break;
				int b = Integer.parseInt(st.nextToken());
				arr[vertex].add(new Edge(a, b));
			}
		}
		
		visited = new boolean[V];
		ans = 0;
		int result1 = dfs(0,0);
		visited = new boolean[V];
		int result2 = dfs(maxVertex,0);
		
		System.out.println(Math.max(result1, result2));
		
	}

	private static int dfs(int x, int cnt) {
		
		visited[x] = true;
		
		if (ans < cnt) {
			ans = cnt;
			maxVertex = x;
		}
		
		for (Edge e : arr[x]) {
			if (!visited[e.to]) {
				visited[e.to] = true;
				dfs(e.to,cnt + e.weight);
				visited[e.to] = false;
			}
		}
		
		return ans;
	}
}
