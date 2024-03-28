import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 벨만 포드로 풀면 되는데 N개의 지점이 모두 연결되었다는 보장이 없다는 점만 조심하면 됨
public class BOJ1865_웜홀_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static String lineSeparator = System.lineSeparator();
	static int TC, N, M, W;
	static ArrayList<Edge>[] adjList;
	static final int INF = 25000001;

	static class Edge {
		int end;
		int cost;

		public Edge(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		TC = Integer.parseInt(br.readLine());

		for (int testcase = 1; testcase <= TC; testcase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			adjList = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				adjList[i] = new ArrayList<>();
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				adjList[S].add(new Edge(E, T));
				adjList[E].add(new Edge(S, T));
			}

			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = -1 * Integer.parseInt(st.nextToken());

				adjList[S].add(new Edge(E, T));
			}
			
			String result = BellmanFord() ? "NO" : "YES";
			sb.append(result).append(lineSeparator);
		}

		System.out.print(sb);
	}

	private static boolean BellmanFord() {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[1] = 0;
		
		// 그래프가 어떻게 생겼는지 따져주기 힘들어서 BellmanFord를 지점의 수만큼 반복함
		for (int start = 1; start <= N; start++) {
			for (int vertexIndex = 1; vertexIndex <= N; vertexIndex++) {
				int edgeCount = adjList[vertexIndex].size();
				
				for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
					int from = vertexIndex;
					int to = adjList[vertexIndex].get(edgeIndex).end;
					int cost = adjList[vertexIndex].get(edgeIndex).cost;
					
					if (dist[to] > dist[from] + cost) {
						dist[to] = dist[from] + cost;
						
						if (start == N)
							return false;
					}
				}
			}
		}
		
		return true;
	}
}
