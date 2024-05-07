import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] isVisit;
	static HashMap<Integer, Integer> hashMap;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isVisit = new boolean[N][M];
		hashMap = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j) - 48;
			}
		}

		int idx = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {

				if (map[i][j] == 0) {
					int num = bfs(i, j, idx);
					hashMap.put(idx, num);
					idx++;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					HashSet<Integer> set = new HashSet<>();
					int count = 1;
					for (int d = 0; d < 4; d++) {
						int nextRow = i + dr[d];
						int nextCol = j + dc[d];

						if (!checkRange(nextRow, nextCol) || map[nextRow][nextCol] == 1) {
							continue;
						}

						if (!set.contains(map[nextRow][nextCol])) {
							count += hashMap.get(map[nextRow][nextCol]);
							set.add(map[nextRow][nextCol]);
						}
					}

					sb.append(count % 10);
				} else {
					sb.append("0");
				}
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	public static int bfs(int sr, int sc, int idx) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(sr, sc));
		map[sr][sc] = idx;
		isVisit[sr][sc] = true;

		int cnt = 1;
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nextRow = cur.r + dr[i];
				int nextCol = cur.c + dc[i];

				if (!checkRange(nextRow, nextCol) || isVisit[nextRow][nextCol]) {
					continue;
				}

				if (map[nextRow][nextCol] != 0) {
					continue;
				}

				queue.offer(new Pos(nextRow, nextCol));
				isVisit[nextRow][nextCol] = true;
				map[nextRow][nextCol] = idx;
				cnt++;
			}
		}
		return cnt;
	}

	static boolean checkRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < M;
	}

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
