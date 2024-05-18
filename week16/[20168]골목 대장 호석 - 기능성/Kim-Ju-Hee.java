package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JUN20168_골목대장호석_김주희 {
	static int N, M, A, B, C, answer;
	static List<Golmok>[] adj;
	static boolean visited[];
	
	static class Golmok{
		int dest, charge;
		
		public Golmok(int dest, int charge){
			this.dest = dest;
			this.charge = charge;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int g1 = Integer.parseInt(st.nextToken());
			int g2 = Integer.parseInt(st.nextToken());
			int charge = Integer.parseInt(st.nextToken());
			
			adj[g1].add(new Golmok(g2, charge));
			adj[g2].add(new Golmok(g1, charge));
		}
		
		visited = new boolean[N+1];
		answer = Integer.MAX_VALUE;
		dfs(A, C, 0);
		
		if(answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
		
	}

	private static void dfs(int cur, int myMoney, int maxValue) {
		if(cur == B) {
			answer = Math.min(maxValue, answer);
			return;
		}
		
		if(myMoney <= 0) return;
		
		for (Golmok g : adj[cur]) {
			if(visited[g.dest] || myMoney - g.charge < 0) continue;
			
			visited[g.dest] = true;
			dfs(g.dest, myMoney - g.charge, Math.max(maxValue,g.charge));
			visited[g.dest] = false;
		}
	}

}
