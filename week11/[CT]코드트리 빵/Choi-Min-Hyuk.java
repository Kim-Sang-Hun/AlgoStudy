package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CT_코드트리빵_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int n, m, time = 0;
	static int[][] map; // 사용한 베이스캠프와 편의점은 사용자 번호
	static ArrayList<Node> stores; // 편의점
	static ArrayList<Node> basecamps; // 베이스캠프
	static ArrayList<Node> peopleLocations; // 사용자 위치
	static int[] dx = { -1, 0, 0, 1 };
	static int[] dy = { 0, -1, 1, 0 };

	static class Node {
		int x, y;
		boolean arrive;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		Node(int x, int y, boolean arrive) {
			this.x = x;
			this.y = y;
			this.arrive = arrive;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		stores = new ArrayList<>();
		basecamps = new ArrayList<>();
		peopleLocations = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				if (Integer.parseInt(st.nextToken()) == 1)
					basecamps.add(new Node(i, j));
			}
		}

		for (int i = 1; i < m + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			stores.add(new Node(x - 1, y - 1));
		}

		doSimulation();

		System.out.print(time);
	}

	private static void doSimulation() {
		while (true) {
			time++;
			
			move();
			
			if (time <= m)
				findAndGoBaseCamp();
			
			if (allArrive()) {
				break;
			}
		}
	}

	private static void move() {
		for (int i = 0; i < peopleLocations.size(); i++) {
			Node person = peopleLocations.get(i);
			
			if (person.arrive)
				continue;
			
			Node store = stores.get(i);
			Queue<Node> q = new LinkedList<>();
			q.add(person);
			boolean visited[][] = new boolean[n][n];
			visited[person.x][person.y] = true;
			Node[][] come = new Node[n][n];

			while (!q.isEmpty()) {
				Node node = q.poll();
				for (int j = 0; j < 4; j++) {
					int nx = node.x + dx[j];
					int ny = node.y + dy[j];

					if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || (map[nx][ny] > 0 && map[nx][ny] != i + 1))
						continue;

					q.add(new Node(nx, ny));
					visited[nx][ny] = true;
					come[nx][ny] = new Node(node.x, node.y);
				}
			}

			if (come[store.x][store.y] == null)
				continue;
			
			int cx = come[store.x][store.y].x;
			int cy = come[store.x][store.y].y;
			if (cx == person.x && cy == person.y) {
				person.x = cx;
				person.y = cy;
				store.arrive = true;
				person.arrive = true;
				map[store.x][store.y] = i + 1;
				continue;
			}
			
			while (true) {
				int ccx = come[cx][cy].x;
				int ccy = come[cx][cy].y;
				if (ccx == person.x && ccy == person.y) {
					person.x = cx;
					person.y = cy;
					break;
				}
				cx = ccx;
				cy = ccy;
			}
		}

	}

	private static void findAndGoBaseCamp() {
		Node store = stores.get(time - 1);
		int minDist = Integer.MAX_VALUE;
		int minX = 0;
		int minY = 0;
		int baseNo = 0;
		
		Queue<Node> q = new LinkedList<>();
		q.add(store);
		boolean visited[][] = new boolean[n][n];
		visited[store.x][store.y] = true;
		int[][] distance = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				distance[i][j] = Integer.MAX_VALUE;
			}
		}
		distance[store.x][store.y] = 0;
		
		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int j = 0; j < 4; j++) {
				int nx = node.x + dx[j];
				int ny = node.y + dy[j];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n || visited[nx][ny] || (map[nx][ny] > 0 && map[nx][ny] != time))
					continue;

				q.add(new Node(nx, ny));
				visited[nx][ny] = true;
				distance[nx][ny] = distance[node.x][node.y] + 1;
			}
		}

		for (Node base : basecamps) {
			if (base.arrive)
				continue;
			
			int dist = distance[base.x][base.y];
			if (minDist > dist) {
				minDist = dist;
				minX = base.x;
				minY = base.y;
				baseNo = basecamps.indexOf(base);
			}
			
			else if (minDist == dist) {
				if (minX > base.x) {
					minDist = dist;
					minX = base.x;
					minY = base.y;
					baseNo = basecamps.indexOf(base);
				}
				
				else if (minX == base.x && minY > base.y) {
					minDist = dist;
					minX = base.x;
					minY = base.y;
					baseNo = basecamps.indexOf(base);
				}
			}
		}
		
		basecamps.get(baseNo).arrive = true;
		peopleLocations.add(new Node(minX, minY, false));
		map[minX][minY] = time; // 사용한 베이스캠프 표시
	}

	private static boolean allArrive() {
		for (int i = 0; i < m; i++) {
			if (peopleLocations.get(i) == null || !peopleLocations.get(i).arrive)
				return false;
		}
		
		return true;
	}
}
