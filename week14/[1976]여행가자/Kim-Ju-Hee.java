package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN1976_여행가자_김주희 {
	static int N, M, cnt;
	static boolean adj[][];
	
	private static boolean bfs(int start, int dest) {
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		boolean visited[] = new boolean[N+1];
		visited[start] = true;
		boolean flag = false;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			if(cur == dest) {
				flag = true;
				break;
			}
			
			for (int i = 1; i <= N; i++) {
				 if(adj[cur][i] && !visited[i]) {
					 q.add(i);
					 visited[i] = true;
				 }
			}
			
		}
		return flag;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adj = new boolean[N+1][N+1];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					cnt++;
					adj[i][j] = true;
				}
			}
		}
		

		int[] route = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			route[i] = Integer.parseInt(st.nextToken());
		}
		
		
		boolean flag = true;
		for (int i = 0; i < M-1; i++) {
			flag = bfs(route[i], route[i+1]);
			if(!flag) break;
		}
		
		if(flag) System.out.println("YES");
		else System.out.println("NO");

	}

}
