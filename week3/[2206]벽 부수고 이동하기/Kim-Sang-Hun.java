import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answer;
	static int[][] map;
	static int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static boolean[][] visited;
	
	public static void DFS(int y, int x, int count, boolean canBreakWall) { 
		if (y == N - 1 && x == M - 1) {
			answer = Math.min(answer, count);
			return;
		}
		if (answer < count + 1) return;
		for (int i = 0; i < 4; i++) {
			int nY = y + dirs[i][0];
			int nX = x + dirs[i][1];

			if (nY >= N || nX >= M || nY < 0 || nX < 0 || visited[nY][nX]) {
				continue;
			}
			
			if (map[nY][nX] == 1) {
				if (canBreakWall) {
					visited[nY][nX] = true;
					DFS(nY, nX, count + 1, false);
					visited[nY][nX] = false;
				}
			} else {
				visited[nY][nX] = true;
				DFS(nY, nX, count + 1, canBreakWall);
				visited[nY][nX] = false;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		answer = N * M;
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}
		DFS(0, 0, 1, true);
		System.out.print(answer == N * M ? -1 : answer);
	}
}
