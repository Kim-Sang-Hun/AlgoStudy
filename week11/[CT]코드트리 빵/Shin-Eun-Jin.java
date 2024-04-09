import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 코드트리_빵 {
	static final int INACCESSIBLE = 2;
	static int n, m, num;
	static int[][] map;
	static Pos[] marts;
	static Human[] humans;
	static boolean[][] isVisit;
	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 격자의 크기
		m = Integer.parseInt(st.nextToken()); // 사람의 수
		num = 0; // 편의점 도착한 사람의 수
		map = new int[n][n];
		marts = new Pos[m + 1];
		humans = new Human[m + 1];

		// 격자 정보 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 사람이 가고자 하는 편의점 위치 입력
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			marts[i] = new Pos(row, col);
		}

		// 출발
		int time = 0;
		while (true) {
			time++;

			// 편의점으로 가는 방향 중 가장 가까운데로 이동
			for (int i = 1; i <= m; i++) {
				if (humans[i] == null)
					break; // 아직 베이스캠프로 이동하지 않은 경우
				if (humans[i].isArrive)
					continue; // 편의점에 도착한 경우

				move(i); // 한 칸 이동
			}

			// 격자 모두 이동 후 도착한 편의점 있을 경우 지나갈 수 없게 함
			checkArrive();

			// 모두 다 편의점으로 이동했을 경우
			if (num == m) {
				break;
			}

			// 해당 시간의 사람 베이스 캠프로 이동
			if (time <= m) {
				goBaseCamp(time);
			}

		}

		System.out.println(time);
	}

	public static void move(int idx) {
		Queue<Pos> queue = new ArrayDeque<>();
		isVisit = new boolean[n][n];
		queue.offer(new Pos(humans[idx].r, humans[idx].c, -1)); // (row, col, 방향)
		isVisit[humans[idx].r][humans[idx].c] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			if (cur.r == marts[idx].r && cur.c == marts[idx].c) {
				humans[idx].r += dr[cur.d];
				humans[idx].c += dc[cur.d];
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nextRow = cur.r + dr[d];
				int nextCol = cur.c + dc[d];
				int nextD = cur.d == -1 ? d : cur.d;

				if (!checkRange(nextRow, nextCol) || map[nextRow][nextCol] == INACCESSIBLE) {
					continue;
				}

				if (isVisit[nextRow][nextCol]) {
					continue;
				}

				queue.offer(new Pos(nextRow, nextCol, nextD));
				isVisit[nextRow][nextCol] = true;
			}
		}

	}

	public static void goBaseCamp(int idx) {
		Queue<Pos> queue = new ArrayDeque<>();
		isVisit = new boolean[n][n];
		queue.offer(new Pos(marts[idx].r, marts[idx].c, 0));
		isVisit[marts[idx].r][marts[idx].c] = true;

		int row = Integer.MAX_VALUE;
		int col = Integer.MAX_VALUE;
		int distance = Integer.MAX_VALUE;
		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			// 베이스캠프 도착했을 경우
			if (map[cur.r][cur.c] == 1) {

				// 더이상 가까운 베이스캠프가 없을 경우
				if (distance < cur.d) {
					break;
				}

				// 행 비교
				if (row > cur.r || (row == cur.r && col > cur.c)) {
					row = cur.r;
					col = cur.c;
					distance = cur.d;
				}
			}

			for (int d = 0; d < 4; d++) {
				int nextRow = cur.r + dr[d];
				int nextCol = cur.c + dc[d];

				if (!checkRange(nextRow, nextCol) || isVisit[nextRow][nextCol]
						|| map[nextRow][nextCol] == INACCESSIBLE) {
					continue;
				}

				queue.offer(new Pos(nextRow, nextCol, cur.d + 1));
				isVisit[nextRow][nextCol] = true;
			}
		}

		humans[idx] = new Human(row, col, false); // 현재 사람의 위치 저장
		map[row][col] = INACCESSIBLE; // 더이상 지나갈 수 없는 곳으로 표시
	}

	public static void checkArrive() {
		for (int i = 1; i <= m; i++) {
			if (humans[i] == null)
				break;

			if (!humans[i].isArrive && humans[i].r == marts[i].r && humans[i].c == marts[i].c) {
				humans[i].isArrive = true;
				map[marts[i].r][marts[i].c] = INACCESSIBLE;
				num++;
			}
		}
	}

	public static boolean checkRange(int row, int col) {
		return row >= 0 && row < n && col >= 0 && col < n;
	}

	static class Pos {
		int r;
		int c;
		int d;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}

		public Pos(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static class Human {
		int r;
		int c;
		boolean isArrive;

		public Human(int r, int c, boolean isArrive) {
			this.r = r;
			this.c = c;
			this.isArrive = isArrive;
		}
	}
}
