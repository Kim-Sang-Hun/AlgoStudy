import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

// 못풀겠어요 이거...
// bfs로 돌면서 하나의 집합에 좌표들(Point들)을 넣어주고
// 그 좌표들의 평균을 구해서 그 좌표들에 다시 쏴주고
// 그 다음 bfs()를 호출해서 끝날 때까지 반복해줬는데
// 시간초과가 나네요 어떻게 풀지 모르겠읍니다
public class Main {

	static int N, L, R;
	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	static int time;
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
		bfs();
		System.out.println(time);
	}
	
	private static void bfs() {
		visited = new boolean[N][N];
		Map<Integer, List<Point>> union = new HashMap<>();
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j]) continue;
				++count;
				Queue<Point> qu = new LinkedList<>();
				qu.add(new Point(i, j));
				visited[i][j] = true;
				List<Point> points = new ArrayList<>();
				points.add(new Point(i,j));
				while (!qu.isEmpty()) {
					Point cur = qu.poll();
					
					for (int k = 0; k < 4; k++) {
						int ny = cur.y + dy[k];
						int nx = cur.x + dx[k];
						if (!isValid(ny, nx)) continue;
						int diff = Math.abs(map[ny][nx] - map[cur.y][cur.x]);
						if (diff >= L && diff <= R) {
							visited[ny][nx] = true;
							qu.add(new Point(ny, nx));
							points.add(new Point(ny, nx));
						}
					}
				}
				union.put(count, points);
			}
		}
		
		for (List<Point> points : union.values()) {
			int sum = 0;
			for (Point point : points) {
				sum += map[point.y][point.x];
			}
			int average = sum / points.size();
			for (Point point : points) {
				map[point.y][point.x] = average;
			}
		}
		
		++time;
		if (union.size() == 1) return;
		bfs();
	}
	
	private static class Point {
		int y, x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	private static boolean isValid(int y, int x) {
		if (y >= N || y < 0 || x >= N || x < 0 || visited[y][x]) return false;
		return true;
	}
}
