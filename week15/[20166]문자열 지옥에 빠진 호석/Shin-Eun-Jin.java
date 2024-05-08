import java.io.*;
import java.util.*;

public class day3 {
	static int N, M, K, maxLen;
	static char[][] map;
	static String[] words;
	static HashMap<String, Integer> hashMap;
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		words = new String[K];
		hashMap = new HashMap<>();

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}

		maxLen = Integer.MIN_VALUE;
		for (int i = 0; i < K; i++) {
			words[i] = br.readLine();
			hashMap.put(words[i], 0);

			maxLen = Math.max(maxLen, words[i].length());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bfs(i, j);
			}
		}

		for (int i = 0; i < K; i++) {
			System.out.println(hashMap.get(words[i]));
		}
	}

	public static void bfs(int row, int col) {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.offer(new Pos(row, col, 1, map[row][col] + ""));

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			if (cur.word.length() > maxLen) {
				continue;
			}

			if (hashMap.containsKey(cur.word)) {
				hashMap.put(cur.word, hashMap.get(cur.word) + 1);
			}

			for (int d = 0; d < 8; d++) {
				int nextRow = cur.r + dr[d];
				int nextCol = cur.c + dc[d];

				if (nextRow < 0) {
					nextRow += N;
				} else if (nextRow >= N) {
					nextRow -= N;
				}

				if (nextCol < 0) {
					nextCol += M;
				} else if (nextCol >= M) {
					nextCol -= M;
				}

				queue.offer(new Pos(nextRow, nextCol, cur.len + 1, cur.word + map[nextRow][nextCol]));
			}

		}

	}

	static class Pos {
		int r;
		int c;
		int len;
		String word;

		public Pos(int r, int c, int len, String word) {
			this.r = r;
			this.c = c;
			this.len = len;
			this.word = word;
		}
	}
}
