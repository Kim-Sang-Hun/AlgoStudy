import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN19237_어른상어 {
	
	static int[] dirY = {-1, 1, 0, 0};
	static int[] dirX = {0, 0, -1, 1};
	static int N, sharkCnt, scentDurability, time;
	static Shark[][] map;
	// 상어 순서 저장
	static Shark[] sharks;
	// 상어의 우선순위 저장
	static int[][][] sharkPriority;

	static class Shark {
		int num, y, x, time, dir;

		public Shark(int num, int time) {
			this.num = num;
			this.time = time;
		}

		public Shark(int num, int y, int x, int time, int dir) {
			this.num = num;
			this.y = y;
			this.x = x;
			this.time = time;
			this.dir = dir;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		sharkCnt = Integer.parseInt(st.nextToken());
		scentDurability = Integer.parseInt(st.nextToken());
		sharks = new Shark[sharkCnt + 1];
		sharkPriority = new int[sharkCnt + 1][4][4];
		map = new Shark[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if (cur != 0) {
					sharks[cur] = new Shark(cur, i, j, 0, -1);
				}
				map[i][j] = new Shark(0, -1);
			}
		}
		st = new StringTokenizer(br.readLine());
		// 현재 방향 저장
		for (int i = 1; i < sharks.length; i++) {
			sharks[i].dir = Integer.parseInt(st.nextToken()) - 1;
		}
		for (int i = 0; i < sharkCnt * 4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				sharkPriority[(i / 4) + 1][i % 4][j] = Integer.parseInt(st.nextToken()) - 1;
			}
		}
		bfs();
		System.out.println(time >= 1001 ? -1 : time);
	}
	
	private static void bfs() {
		Queue<Shark> qu = new LinkedList<>();
		for (int i = 1; i < sharks.length; i++) {
			qu.add(sharks[i]);
		}
		
		while (qu.size() != 1) {
			Shark cur = qu.poll();
			// 시간 업데이트
			time = cur.time;
			// 이미 낮은 번호의 상어가 그 구역에 있는 경우 상어를 격자에서 없앰
			if (map[cur.y][cur.x].time == cur.time) {
				continue;
			}
			map[cur.y][cur.x] = cur;
			
			// 다음 방향 탐색
			int ndir = checkDir(cur.num, cur.dir, cur.y, cur.x);
			if (ndir == -1) {
				time = 1001;
			}
			if (time == 1001) break;
			int ny = cur.y + dirY[ndir];
			int nx = cur.x + dirX[ndir];
			qu.add(new Shark(cur.num, ny, nx, cur.time + 1, ndir));
		}
	}
	// 우선순위에 따라 갈 수 있는 곳 탐색. 갈 수 있는 곳이 없으면 -1 리턴한다.
	public static int checkDir(int sharkNum, int dir, int y, int x) {
		int[] priority = sharkPriority[sharkNum][dir];
		for (int i = 0; i < priority.length; i++) {
			int ndir = priority[i];
			int ny = y + dirY[ndir];
			int nx = x + dirX[ndir];
			
			if (isOut(ny, nx)) continue;
			Shark next = map[ny][nx];
			// 그냥 빈 공간일 경우
			if (next.num == 0) return ndir;
			// 시간이 지나 냄새가 사라진 경우
			else if (next.time + scentDurability <= time) {
				return ndir;
			}
		}
		// 비어있는 공간이 없는 경우 우선순위에 따라 갈 수 있는 곳 탐색(자기 번호와 같은 곳)
		for (int i = 0; i < priority.length; i++) {
			int ndir = priority[i];
			int ny = y + dirY[ndir];
			int nx = x + dirX[ndir];
			
			if (isOut(ny, nx)) continue;
			Shark next = map[ny][nx];
			if (next.num == sharkNum) return ndir;
		}
		return -1;
	}
	
	public static boolean isOut(int y, int x) {
		if (y >= N || y < 0 || x >= N || x < 0) return true;
		else return false;
	}

}
