import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ4803_트리_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static String lineSeparator = System.lineSeparator();
	static int N, M;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		int testcase = 1;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0)
				break;

			int ans = 0;
			adjList = new ArrayList[N + 1];
			visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++)
				adjList[i] = new ArrayList<>();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjList[a].add(b);
				adjList[b].add(a);
			}

			for (int i = 1; i <= N; i++) {
				if (!visited[i]) {
					visited[i] = true;
					if (dfs(i, i))
						ans++;
				}
			}

			sb.append("Case ").append(testcase).append(": ");

			if (ans == 0) {
				sb.append("No trees.").append(lineSeparator);
			} else if (ans == 1) {
				sb.append("There is one tree.").append(lineSeparator);
			} else {
				sb.append("A forest of ").append(ans).append(" trees.").append(lineSeparator);
			}

			testcase++;
		}

		System.out.println(sb);
	}

	// DFS를 통해 탐색
	public static boolean dfs(int parent, int num) {
		for (int i : adjList[num]) {
			if (i == parent)
				continue;
			
			// 이미 방문된 부분이 있으면 사이클이 있는 것
			if (visited[i])
				return false;

			visited[i] = true;
			// 이미 방문된 부분이 있으면 사이클이 있는 것
			if (!dfs(num, i))
				return false;
		}

		return true;
	}
}
