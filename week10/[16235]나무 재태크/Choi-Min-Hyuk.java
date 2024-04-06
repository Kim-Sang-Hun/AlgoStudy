import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16235_나무재테크_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, K;
	static int[][] map;
	static int[][] foodArr;
	static ArrayList<Tree> liveTreeList = new ArrayList<>();
	static ArrayDeque<Integer> deadTreeQueue = new ArrayDeque<>();
	static int[] dx = { 0, 0, 1, -1, 1, -1, 1, -1 };
	static int[] dy = { 1, -1, 0, 0, 1, -1, -1, 1 };

	static class Tree {
		int x;
		int y;
		int age;
		boolean isDead;

		public Tree(int x, int y, int age, boolean isDead) {
			this.x = x;
			this.y = y;
			this.age = age;
			this.isDead = isDead;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		foodArr = new int[N][N];
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				foodArr[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());

			liveTreeList.add(new Tree(x, y, age, false));
		}

		while (K-- > 0) {
			spring();
			summer();
			fall();
			winter();
		}

		System.out.print(liveTreeList.size());
	}
	
	private static void spring() {
		for (int i = 0; i < liveTreeList.size(); i++) {
			Tree tree = liveTreeList.get(i);

			// 나무가 영양분을 섭취할 수 있다면
			if (map[tree.x][tree.y] >= tree.age) {
				map[tree.x][tree.y] -= tree.age;
				tree.age += 1;
				continue;
			}

			deadTreeQueue.add(i);
		}
	}
	
	private static void summer() {
		while (!deadTreeQueue.isEmpty()) {
			Tree tree = liveTreeList.get(deadTreeQueue.poll());

			int age = tree.age;
			map[tree.x][tree.y] += age / 2;

			tree.isDead = true;
		}
	}
	
	private static void fall() {
		ArrayList<Tree> newTreeList = new ArrayList<>();

		for (int i = 0; i < liveTreeList.size(); i++) {
			Tree tree = liveTreeList.get(i);

			if (!tree.isDead) {
				int x = tree.x;
				int y = tree.y;

				if (tree.age % 5 == 0) {

					for (int j = 0; j < 8; j++) {
						int xf = x + dx[j];
						int yf = y + dy[j];

						if (xf >= 0 && xf < N && yf >= 0 && yf < N) {
							newTreeList.add(new Tree(xf, yf, 1, false));
						}
					}
				}
			}
		}

		// 새로운 나무가 나이가 적으므로 먼저 처리
		for (Tree tree : liveTreeList) {
			if (!tree.isDead) {
				newTreeList.add(tree);
			}
		}

		liveTreeList = newTreeList;
	}
	
	private static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += foodArr[i][j];
			}
		}
	}
}
