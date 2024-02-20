import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int N, M;
	static int[][] map;
	static int[][] newArr;
	static boolean[][] isVisited;
	static int[] dr = { 0, 0, 1, -1 };
	static int[] dc = { 1, -1, 0, 0 };
	static int maxCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 행
		M = Integer.parseInt(st.nextToken()); // 열
		map = new int[N][M];
		maxCnt = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N * M; i++) {
			if (map[i / M][i % M] != 0) {
				continue;
			}

			for (int j = i + 1; j < N * M; j++) {
				if (map[j / M][j % M] != 0) {
					continue;
				}

				for (int r = j + 1; r < N * M; r++) {
					if (map[r / M][r % M] != 0) {
						continue;
					}

					newArr = copyArr();

					newArr[i / M][i % M] = 1;
					newArr[j / M][j % M] = 1;
					newArr[r / M][r % M] = 1;

					for (int k = 0; k < N; k++) {
						for (int l = 0; l < M; l++) {
							if (newArr[k][l] == 2) {
								bfs(k, l);
							}
						}
					}

					int cnt = 0;
					for (int k = 0; k < N; k++) {
						for (int l = 0; l < M; l++) {
							if (newArr[k][l] == 0) {
								cnt++;
							}
						}
					}

					maxCnt = Math.max(maxCnt, cnt);
				}
			}
		}
		System.out.println(maxCnt);

	}

	static void bfs(int row, int col) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] { row, col });
		newArr[row][col] = -1;

		while (!queue.isEmpty()) {
			int[] curV = queue.poll();
			int curRow = curV[0];
			int curCol = curV[1];

			for (int i = 0; i < 4; i++) {
				int nextRow = curRow + dr[i];
				int nextCol = curCol + dc[i];

				if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
					continue;
				}

				if (newArr[nextRow][nextCol] == 1 || newArr[nextRow][nextCol] == -1) {
					continue;
				}

				queue.add(new int[] { nextRow, nextCol });
				newArr[nextRow][nextCol] = -1;
			}

		}

	}

	static int[][] copyArr() {
		int[][] newArr = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				newArr[i][j] = map[i][j];
			}
		}
		return newArr;
	}
}
