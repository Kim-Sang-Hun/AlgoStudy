package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <암벽 등반> G4
* 링크
* https://www.acmicpc.net/problem/2412
* 요약
* 
* 풀이
* map+bfs
*/
public class boj_2412 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			Node temp = (Node) obj;
			if (this.x == temp.x && this.y == temp.y)
				return true;
			return false;
		}

		@Override
		public int hashCode() {
			return this.x + this.y + this.x * this.y + this.x - this.y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
	}

	static final int INF = 123456789;

	static int n, T;
	static HashMap<Node, Integer> map;
	static int[] visit;

	static ArrayList<Integer> top = new ArrayList<>();
	static int minMove = INF;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken()); // 홈 개수
		T = Integer.parseInt(st.nextToken()); // 목표 높이

		map = new HashMap<>(); // 홈 위치 저장
		map.put(new Node(0, 0), 0); // 시작 노드
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			Node n = new Node(x, y);
			map.put(n, i);
			if (y == T)
				top.add(i);
		}

		// 풀이
		bfs();
		// 출력
		if (minMove == INF)
			minMove = -1;
		bw.write(minMove + "");
		bw.flush();
	}

	// 중심 기준 거리 4 이내인 곳들
	static int[] dy = { 2, 2, 2, 2, 2, 1, 1, 1, 1, 1, 0, 0, 0, 0, -1, -1, -1, -1, -1, -2, -2, -2, -2, -2 };
	static int[] dx = { -2, -1, 0, 1, 2, -2, -1, 0, 1, 2, -2, -1, 1, 2, -2, -1, 0, 1, 2, -2, -1, 0, 1, 2 };

	private static void bfs() {
		visit = new int[n + 1]; // 방문 여부
		Arrays.fill(visit, INF);

		Queue<Node> q = new ArrayDeque<>();

		Node start = new Node(0, 0);
		q.offer(start); // 시작 위치
		visit[0] = 0;

		while (!q.isEmpty()) {
			Node cur = q.poll();
			for (int d = 0; d < 24; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				if (!isIn(nx, ny))
					continue; // 음수 범위면 continue
				Node nNode = new Node(nx, ny);
				if (!map.containsKey(nNode))
					continue; // 홈 없으면 continue
				int curNum = map.get(cur);
				int nNum = map.get(nNode);
				if (visit[nNum] <= visit[curNum] + 1)
					continue; // 더 멀리 돌아가는 경우면 continue
				q.offer(nNode);
				visit[nNum] = visit[curNum] + 1;
			}
		}

		for (int i = 0; i < top.size(); i++) {
			minMove = Math.min(minMove, visit[top.get(i)]);
		}
	}

	private static boolean isIn(int nx, int ny) {
		if (nx >= 0 && ny >= 0)
			return true;
		return false;
	}
}
