import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN17616_등수찾기_김주희 {
	static int N, M, X;
	
	private static int bfs(int start, List<Integer>[] adj) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		
		q.add(start);
		visited[start] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for (int next : adj[cur]) {
				if(visited[next]) continue;
				
				q.add(next);
				visited[next] = true;
				cnt++;
			}
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		List<Integer>[] adj1 = new ArrayList[N+1];
		List<Integer>[] adj2 = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			adj1[i] = new ArrayList<>();
			adj2[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adj1[a].add(b);
			adj2[b].add(a);
		}
		
		System.out.printf("%d %d",bfs(X, adj2)+1,N-bfs(X, adj1));

	}

}
