import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int N, L, R;
	static int[][] map;
	static boolean[][] isVisit;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int dayCnt = 0;
		for (int day = 0; day < 2000; day++) {
			isVisit = new boolean[N][N];

			int combineCnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					if (isVisit[i][j])
						continue;

					combineCnt += bfs(i, j);
				}
			}

			if (combineCnt > 0) {
				dayCnt++;
			} else {
				break;
			}
		}

		System.out.println(dayCnt);

	}

	static int bfs(int row, int col) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { row, col });
		isVisit[row][col] = true;

		ArrayList<int[]> list = new ArrayList<>();
		int sum = 0;
		int nodeNum = 0;

		while (!queue.isEmpty()) {
			int[] curV = queue.poll();
			int curRow = curV[0];
			int curCol = curV[1];

			list.add(new int[] { curRow, curCol });
			sum += map[curRow][curCol];
			nodeNum++;

			for (int d = 0; d < 4; d++) {
				int nextRow = curRow + dr[d];
				int nextCol = curCol + dc[d];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= N || isVisit[nextRow][nextCol]) {
					continue;
				}

				if (Math.abs(map[curRow][curCol] - map[nextRow][nextCol]) < L
						|| Math.abs(map[curRow][curCol] - map[nextRow][nextCol]) > R) {
					continue;
				}

				queue.offer(new int[] { nextRow, nextCol });
				isVisit[nextRow][nextCol] = true;
			}
		}

		if (nodeNum == 1) {
			return 0;
		}

		int avg = sum / nodeNum;
		for (int[] node : list) {
			map[node[0]][node[1]] = avg;
		}

		return 1;
	}

}
