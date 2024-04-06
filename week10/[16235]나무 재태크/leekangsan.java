package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <나무 재테크> G3
* 링크
* https://www.acmicpc.net/problem/16235
* 요약
* 
* 풀이
* 시뮬레이션
*/
public class boj_16235 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Tree implements Comparable<Tree> {
		int i, j, age;

		public Tree(int i, int j, int age) {
			this.i = i;
			this.j = j;
			this.age = age;
		}

		@Override
		public String toString() {
			return "Tree [i=" + i + ", j=" + j + ", age=" + age + "]";
		}

		@Override
		public int compareTo(Tree o) {
			return this.age - o.age;
		}
	}

	static int N; // 땅 한 변 길이 N
	static int M; // 묘목 수 M
	static int K; // 경과 년수 K
	static int[][] ground; // N*N 크기의 땅, 값은 해당 칸이 갖고 있는 양분, [0] 사용x
	static int[][] A; // 겨울에 추가되는 양분 값, [0] 사용x
	static PriorityQueue<Tree> tree; // 나무 정보 (위치, 나이)
	static Queue<Tree> live = new ArrayDeque<>();
	static Queue<Tree> dead = new ArrayDeque<>();

	static int[] di = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dj = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		// 첫째 줄에 N, M, K가 주어진다.
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 가장 처음에 양분은 모든 칸에 5만큼 들어있다.
		ground = new int[N + 1][N + 1];
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= N; ++j) {
				ground[i][j] = 5;
			}
		}

		// 둘째 줄부터 N개의 줄에 A배열의 값이 주어진다
		A = new int[N + 1][N + 1];
		for (int i = 1; i <= N; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 1; j <= N; ++j) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 다음 M개의 줄에는 상도가 심은 나무의 정보를 나타내는 세 정수 x, y, z가 주어진다. (위치, 나이)
		tree = new PriorityQueue<>();
		for (int i = 1; i <= M; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken()); // 하나의 좌표에 여러 나무 존재 가능
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken()); // 나이는 모두 다르다
			tree.add(new Tree(x, y, z));
		}
		
		for(int i=0; i<K; ++i ) {
			spring();
			summer();
			fall();
			winter();		
		}

		// K년이 지난 후 살아남은 나무의 수를 출력한다.
		bw.write(tree.size()+"");
		bw.flush();
	}

	// 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다.
	// 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
	private static void winter() {
		for (int i = 1; i <= N; ++i) {
			for (int j = 1; j <= N; ++j) {
				ground[i][j] += A[i][j];
			}
		}

	}

	// 가을에는 나무가 번식한다.
	// 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
	// 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1),
	// (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
	// 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
	private static void fall() {
		while (!tree.isEmpty()) {
			Tree curTree = tree.poll();
			if (curTree.age % 5 == 0) { // 번식 가능한 나무
				for (int k = 0; k < 8; ++k) {
					int ni = curTree.i + di[k];
					int nj = curTree.j + dj[k];
					if (!isIn(ni, nj))
						continue;
					live.offer(new Tree(ni, nj, 1));
				}
			}
			live.offer(curTree);
		}
		while (!live.isEmpty()) {
			tree.offer(live.poll());
		}
	}

	private static boolean isIn(int i, int j) {
		if (1 <= i && i <= N && 1 <= j && j <= N)
			return true;
		return false;
	}

	// 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
	// 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다.
	// 소수점 아래는 버린다.
	private static void summer() {
		while (!dead.isEmpty()) {
			Tree curTree = dead.poll();
			ground[curTree.i][curTree.j] += curTree.age / 2;
		}
	}

	// 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다.
	// 각각의 나무는 나무가 있는 1×1 크기의 칸에 있는 양분만 먹을 수 있다.
	// 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다.
	// 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
	private static void spring() {
		while (!tree.isEmpty()) {
			Tree curTree = tree.poll();
			if (curTree.age > ground[curTree.i][curTree.j]) {
				dead.offer(curTree);
				continue;
			}
			ground[curTree.i][curTree.j] -= curTree.age;
			curTree.age++;
			live.offer(curTree); // 양분 먹고 살아남은 나무만 큐에 삽입.
		}
		while (!live.isEmpty()) {
			tree.offer(live.poll());
		}
	}
}
