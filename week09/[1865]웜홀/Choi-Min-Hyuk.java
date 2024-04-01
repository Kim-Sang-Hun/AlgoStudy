import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 벨만 포드로 풀면 됨
public class BOJ1865_웜홀_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static String lineSeparator = System.lineSeparator();
	static int TC, N, M, W;
	static ArrayList<Node>[] adjList;
	static final int INF = 25000001;

	static class Node {
		int end;
		int cost;

		public Node(int end, int cost) {
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

				adjList[S].add(new Node(E, T));
				adjList[E].add(new Node(S, T));
			}

			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = -1 * Integer.parseInt(st.nextToken());

				adjList[S].add(new Node(E, T));
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
		
		// 1번 지점부터 N-1번 업데이트 이후 마지막 N번째 for문에서 거리가 업데이트 되면 가중치가 줄어드는 음수 사이클이 있음
		for (int i= 1; i <= N; i++) {
			for (int NodeIndex = 1; NodeIndex <= N; NodeIndex++) {
				int edgeCount = adjList[NodeIndex].size();
				
				for (int edgeIndex = 0; edgeIndex < edgeCount; edgeIndex++) {
					int from = NodeIndex;
					int to = adjList[NodeIndex].get(edgeIndex).end;
					int cost = adjList[NodeIndex].get(edgeIndex).cost;
					
					if (dist[to] > dist[from] + cost) {
						dist[to] = dist[from] + cost;
						
						if (i == N)
							return false;
					}
				}
			}
		}
		
		return true;
	}
}
