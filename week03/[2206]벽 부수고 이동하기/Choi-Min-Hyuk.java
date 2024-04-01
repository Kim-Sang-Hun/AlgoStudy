import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206_벽_부수고_이동하기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String LineSeparator = System.lineSeparator();
	static StringTokenizer st;
	static int N, M, answer;
	static char[][] arr;
	static int[] dRow = { -1, 1,  0, 0 };
	static int[] dCol = {  0, 0, -1, 1 };
	// 해당 좌표까지 거리를 담는 배열
	static int[][] distance;
	// 벽을 부순 여부에 따라 방문 여부 기록
	static boolean[][][] visited;
	static Queue<int[]> queue = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		distance = new int[N][M];
		visited = new boolean[2][N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}

		// 시작 지점과 도착 지점이 같을 경우
		if (N - 1 == 0 && M - 1 == 0)
			answer = 1;
		else
			bfs();

		System.out.println(answer);
	}

	public static void bfs() {
		// (row, column, 부쉈는지 여부)
		queue.offer(new int[] { 0, 0, 0 });

		while (!queue.isEmpty()) {
			int[] current = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nRow = current[0] + dRow[i];
				int nCol = current[1] + dCol[i];

				if (nRow < 0 || N <= nRow || nCol < 0 || M <= nCol)
					continue;

				// 다음 칸에 벽이 있을 때
				// 벽을 부순 적이 있는지 체크
				// 그 벽을 방문한 적이 있는지 체크
				if (arr[nRow][nCol] == '1') {
					if (current[2] == 0 && !visited[1][nRow][nCol]) {
						visited[current[2]][nRow][nCol] = true;
						distance[nRow][nCol] = distance[current[0]][current[1]] + 1;
						queue.offer(new int[] { nRow, nCol, 1 });
					}
				}

				// 벽이 아닐 경우, 벽을 부순 여부에 따른 방문을 했는지 체크
				else {
					// 해당 칸을 방문을 안했을 때
					if (!visited[current[2]][nRow][nCol]) {
						visited[current[2]][nRow][nCol] = true;
						distance[nRow][nCol] = distance[current[0]][current[1]] + 1;
						queue.offer(new int[] { nRow, nCol, current[2] });
					}
				}

				if (nRow == N - 1 && nCol == M - 1) {
					answer = distance[nRow][nCol] + 1;
					return;
				}
			}
		}

		// 도달을 못한 경우
		answer = -1;
	}
}
