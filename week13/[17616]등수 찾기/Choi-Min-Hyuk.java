import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17616_등수찾기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, X;
	static int U, V;
	static ArrayList<ArrayList<Integer>> highAdjList, lowAdjList;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		highAdjList = new ArrayList<>();
		lowAdjList = new ArrayList<>();

		for (int i = 0; i < N + 1; i++) {
			highAdjList.add(new ArrayList<Integer>());
			lowAdjList.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			highAdjList.get(A).add(B);
			lowAdjList.get(B).add(A);
		}

		System.out.print((1 + bfs(X, lowAdjList)) + " " + (N - bfs(X, highAdjList)));
	}

	private static int bfs(int start, ArrayList<ArrayList<Integer>> graph) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;
		
		int result = 0;
		
		while (!q.isEmpty()) {
			int currentStudent = q.poll();

			for (int i = 0; i < graph.get(currentStudent).size(); i++) {
				int nextStudent = graph.get(currentStudent).get(i);

				if (!visited[nextStudent]) {
					q.offer(nextStudent);
					visited[nextStudent] = true;
					result++;
				}
			}
		}
		
		return result;
	}
}
