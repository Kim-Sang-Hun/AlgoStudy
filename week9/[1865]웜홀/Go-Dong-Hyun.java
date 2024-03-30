package week08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ1865 {
	
	static class Edge {
		int to;
		int weight;
		
		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	static int N,M,W;
	static ArrayList<Edge>[] arr;
	static boolean flag;
	static int[] visited;
	static final int maxV = 9999999;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int TC = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); //지점수
			M = Integer.parseInt(st.nextToken()); //도로개수
			W = Integer.parseInt(st.nextToken()); //웜홀개수
			
			arr = new ArrayList[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = new ArrayList<Edge>();
			}
			
			for (int i = 0; i < M; i++) { //도로
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken())-1;
				int e = Integer.parseInt(st.nextToken())-1;
				int t = Integer.parseInt(st.nextToken());
				arr[s].add(new Edge(e, t));
				arr[e].add(new Edge(s, t));
			}
			
			for (int i = 0; i < W; i++) { //웜
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken())-1;
				int e = Integer.parseInt(st.nextToken())-1;
				int t = Integer.parseInt(st.nextToken());
				arr[s].add(new Edge(e,-t));
			}
			
			for (int i = 0; i < N; i++) {
				flag = sol(i);
				if (flag) {
					System.out.println("YES");
					break;
				}
			}
			
			if (!flag)
				System.out.println("NO");
			
		}
	}

	private static boolean sol(int idx) {
		visited = new int[N];
		
		Arrays.fill(visited, maxV);
		visited[idx] = 0;
		boolean update = false;
		
		for (int i = 0; i < N; i++) {
			update = false;
			
			for (int j = 0; j < N; j++) {
				for (Edge e : arr[j]) {
					if (visited[j] != maxV && visited[e.to] > visited[j] + e.weight) {
						visited[e.to] = visited[j] + e.weight;
						update = true;
					}
				}
			}
			
			if (!update) break;
		}
		
		if(update) {
			for (int i = 0; i < N; i++) {
				for (Edge e : arr[i]) {
					if (visited[i] != maxV && visited[e.to] > visited[i] + e.weight) 
						return true;
				}
			}
		}
			
		return false;
	}
}
