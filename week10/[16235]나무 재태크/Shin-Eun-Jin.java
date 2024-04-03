import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int N, M, K;
	static int[][] feeds, land;
	static Tree[][] trees;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 배열 크기
		M = Integer.parseInt(st.nextToken()); // 나무의 개수
		K = Integer.parseInt(st.nextToken()); // 연도

		feeds = new int[N + 1][N + 1];

		land = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(land[i], 5);
		}

		trees = new Tree[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				trees[i][j] = new Tree();
			}
		}

		// 겨울에 줄 양분 입력
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				feeds[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 나무의 정보 입력
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees[row][col].alive.add(age);
		}

		for (int i = 0; i < K; i++) {
			// 봄
			spring();

			// 여름
			summer();

			// 가을
			fall();

			// 겨울
			winter();
		}

		int aliveTreeNum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				aliveTreeNum += trees[i][j].alive.size();
			}
		}

		System.out.println(aliveTreeNum);
	}

	static void spring() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (trees[i][j].alive.isEmpty())
					continue;

				Collections.sort(trees[i][j].alive);

				ArrayList<Integer> newTrees = new ArrayList<>();
				ArrayList<Integer> deadTrees = new ArrayList<>();

				for (int age : trees[i][j].alive) {
					if (land[i][j] >= age) {
						land[i][j] -= age;
						newTrees.add(age + 1);
					} else {
						deadTrees.add(age);
					}
				}

				trees[i][j].alive = newTrees;
				trees[i][j].dead = deadTrees;
			}

		}
	}

	static void summer() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {

				if (trees[i][j].dead.isEmpty())
					continue;

				for (int age : trees[i][j].dead) {
					land[i][j] += age / 2;
				}

				trees[i][j].dead.clear();
			}
		}
	}

	static void fall() {
		int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (trees[i][j].alive.isEmpty())
					continue;

				for (int tree : trees[i][j].alive) {
					if (tree % 5 != 0)
						continue;

					for (int d = 0; d < dr.length; d++) {
						int nextRow = i + dr[d];
						int nextCol = j + dc[d];

						if (nextRow < 1 || nextRow > N || nextCol < 1 || nextCol > N) {
							continue;
						}

						trees[nextRow][nextCol].alive.add(1);
					}
				}

			}
		}

	}

	static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				land[i][j] += feeds[i][j];
			}
		}
	}

	static class Tree {
		ArrayList<Integer> alive;
		ArrayList<Integer> dead;

		public Tree() {
			alive = new ArrayList<>();
			dead = new ArrayList<>();
		}
	}
}
