import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20168_골목대장호석_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, M, A, B, C;
	static ArrayList<Edge> adj[];
	static boolean[] isVisited;
	static int answer;

	static class Edge {
		int from;
		int to;
		int cost;

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		adj = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			adj[i] = new ArrayList<>();
		}

		isVisited = new boolean[N + 1];
		answer = Integer.MAX_VALUE;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			adj[a].add(new Edge(a, b, c));
			adj[b].add(new Edge(b, a, c));
		}

		isVisited[A] = true;
		DFS(A, C, -1);

		answer = answer == Integer.MAX_VALUE ? -1 : answer;

		System.out.println(answer);
	}

	static void DFS(int currentNode, int money, int maxCost) {
		if (currentNode == B) {
			answer = Math.min(answer, maxCost);
			return;
		}

		if (money <= 0)
			return;

		for (Edge edge : adj[currentNode]) {
			if (!isVisited[edge.to] && edge.cost <= money) {
				isVisited[edge.to] = true;
				DFS(edge.to, money - edge.cost, Math.max(edge.cost, maxCost));
				isVisited[edge.to] = false;
			}
		}
	}
}
