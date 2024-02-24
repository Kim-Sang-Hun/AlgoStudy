import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_연구소_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, map[][], maxSafeAreaCount = Integer.MIN_VALUE;
	static int dRow[] = { 0, 0, 1, -1 };
	static int dCol[] = { 1, -1, 0, 0 };
	
	static class Point {
		int row, col;

		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		setWall(0);
		System.out.print(maxSafeAreaCount);
	}

	// 벽을 설치할 수 있는 모든 경우의 수를 다 해봄
	public static void setWall(int wallCount) {
		// 벽 3개를 다 설치하면 바이러스 전파
		if (wallCount == 3) {
			spreadVirus();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					setWall(wallCount + 1);
					map[i][j] = 0;
				}
			}
		}
	}

	// BFS로 바이러스 전파
	public static void spreadVirus() {
		Queue<Point> queue = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					queue.add(new Point(i, j));
				}
			}
		}

		int copyMap[][] = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				copyMap[i][j] = map[i][j];
		}

		while (!queue.isEmpty()) {
			Point point = queue.poll();
			int currentRow = point.row;
			int cuurentCol = point.col;

			for (int i = 0; i < 4; i++) {
				int nextRow = currentRow + dRow[i];
				int nextCol = cuurentCol + dCol[i];

				if (0 <= nextRow && nextRow < N && 0 <= nextCol && nextCol < M && copyMap[nextRow][nextCol] == 0) {
					queue.add(new Point(nextRow, nextCol));
					copyMap[nextRow][nextCol] = 2;
				}
			}
		}

		int SafeAreaCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0) {
					SafeAreaCount++;
				}
			}
		}

		if (maxSafeAreaCount < SafeAreaCount)
			maxSafeAreaCount = SafeAreaCount;
	}
}
