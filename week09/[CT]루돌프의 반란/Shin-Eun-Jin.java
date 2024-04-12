import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 루돌프의반란 {
	static int N, M, P, C, D;
	static int rudolphRow, rudolphCol;
	static int[][] map;
	static Santa[] santas;
	static int[] dr = { -1, 0, 1, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 1, 0, -1, -1, 1, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 게임판의 크기
		M = Integer.parseInt(st.nextToken()); // 게임 턴 수
		P = Integer.parseInt(st.nextToken()); // 산타의 수
		C = Integer.parseInt(st.nextToken()); // 루돌프의 힘
		D = Integer.parseInt(st.nextToken()); // 산타의 힘

		// 루돌프 초기 위치 입력
		st = new StringTokenizer(br.readLine());
		rudolphRow = Integer.parseInt(st.nextToken()) - 1;
		rudolphCol = Integer.parseInt(st.nextToken()) - 1;

		// 산타 위치 입력
		map = new int[N][N];
		santas = new Santa[P + 1];
		for (int santa = 1; santa <= P; santa++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;

			map[row][col] = idx; // 해당 위치에 산타 번호 표시
			santas[idx] = new Santa(row, col);
		}

		// M개의 턴
		for (int m = 1; m <= M; m++) {

			// 1. 루돌프 움직임
			moveRudolph();

			// 2. 산타 1~P번까지 순서대로 움직임
			for (int idx = 1; idx <= P; idx++) {
				// 탈락한 산타 제외
				if (santas[idx].isFail)
					continue;

				// 기절한 산타 2번 쉼
				if (santas[idx].isFaint != 0) {
					santas[idx].isFaint--;
					continue;
				}

				// 루돌프에게 돌진
				moveSanta(idx);

			}

			// 3. 탈락하지 않은 산타에게 1점씩 줌
			addScore();

			// 4. 산타 모두 탈락했다면 게임 종료
			if (isFinish()) {
				break;
			}
		}

		// 산타 1 ~ P번까지의 최종 점수 출력
		for (int idx = 1; idx <= P; idx++) {
			System.out.print(santas[idx].score + " ");
		}

	}

	public static void moveRudolph() {

		// 가장 가까운 산타 선택
		int minDistance = Integer.MAX_VALUE;
		int maxRow = -1;
		int maxCol = -1;
		for (int santa = 1; santa <= P; santa++) {
			if (santas[santa].isFail) {
				continue;
			}
			int distance = (int) (Math.pow(santas[santa].r - rudolphRow, 2)
					+ Math.pow(santas[santa].c - rudolphCol, 2));

			if (minDistance > distance || (minDistance == distance && maxRow < santas[santa].r)
					|| (minDistance == distance && maxRow == santas[santa].r && maxCol < santas[santa].c)) {
				minDistance = distance;
				maxRow = santas[santa].r;
				maxCol = santas[santa].c;
			}

		}

		minDistance = Integer.MAX_VALUE;
		int dir = -1;
		for (int d = 0; d < 8; d++) {
			int nextRow = rudolphRow + dr[d];
			int nextCol = rudolphCol + dc[d];

			if (!checkRange(nextRow, nextCol))
				continue;

			int distance = (int) (Math.pow(nextRow - maxRow, 2) + Math.pow(nextCol - maxCol, 2));

			if (minDistance > distance) {
				minDistance = distance;
				dir = d;
			}

		}

		// 가장 가까운 산타를 향해 1칸 돌진
		rudolphRow += dr[dir];
		rudolphCol += dc[dir];

		// 충돌 했을 경우
		if (map[rudolphRow][rudolphCol] != 0) {
			// 루돌프 -> 산타 : 해당 산타 C만큼의 점수 얻음, 루돌프가 이동해온 방향으로 C칸 만큼 밀려남
			clash(C, dir, map[rudolphRow][rudolphCol], 2);
		}

	}

	public static void moveSanta(int santa) {
		int santaRow = santas[santa].r;
		int santaCol = santas[santa].c;

		// 루돌프에게 거리가 가장 가까워지는 방향으로 1칸 이동
		int distance = (int) (Math.pow(santaRow - rudolphRow, 2) + Math.pow(santaCol - rudolphCol, 2));
		int finalSantaRow = -1;
		int finalSantaCol = -1;
		int finalDir = -1;
		for (int d = 0; d < 4; d++) {
			int nextRow = santaRow + dr[d];
			int nextCol = santaCol + dc[d];

			// 범위 벗어났거나, 다른 산타가 있을 경우
			if (!checkRange(nextRow, nextCol) || map[nextRow][nextCol] != 0) {
				continue;
			}

			int nextDistance = (int) (Math.pow(nextRow - rudolphRow, 2) + Math.pow(nextCol - rudolphCol, 2));

			if (nextDistance < distance) {
				finalSantaRow = nextRow;
				finalSantaCol = nextCol;
				finalDir = d;
				distance = nextDistance;
			}
		}

		// 움직일 수 있는 칸이 없다면 산타 못 움직임, 움직일 수 있더라도 가까워지지 않는다면 안 움직임
		if (finalSantaRow == -1) {
			return;
		}

		// 산타 움직임
		map[santaRow][santaCol] = 0;
		map[finalSantaRow][finalSantaCol] = santa;
		santas[santa].r = finalSantaRow;
		santas[santa].c = finalSantaCol;

		// 충돌
		if (santas[santa].r == rudolphRow && santas[santa].c == rudolphCol) {
			// 산타 -> 루돌프 : 해당 산타 D만큼의 점수 얻음, 자신이 이동해온 반대방향으로 D칸 만큼 밀려남
			clash(D, (finalDir + 2) % 4, santa, 1);
		}

	}

	public static void clash(int score, int dir, int santa, int faintNum) {
		santas[santa].score += score; // 점수 획득
		santas[santa].isFaint = faintNum; // 기절

		pushSanta(santa, dir, score);

	}

	public static void pushSanta(int santa, int dir, int distance) {
		int nextRow = santas[santa].r + dr[dir] * distance;
		int nextCol = santas[santa].c + dc[dir] * distance;

		// 산타 탈락
		if (!checkRange(nextRow, nextCol)) {
			map[santas[santa].r][santas[santa].c] = 0;
			santas[santa].isFail = true;
			return;
		}

		// 다른 산타가 있는 경우
		if (map[nextRow][nextCol] != 0) {
			pushSanta(map[nextRow][nextCol], dir, 1);
		}

		// 산타 이동
		map[santas[santa].r][santas[santa].c] = 0;
		map[nextRow][nextCol] = santa;
		santas[santa].r = nextRow;
		santas[santa].c = nextCol;

	}

	public static void addScore() {
		for (int idx = 1; idx <= P; idx++) {
			// 탈락한 산타 제외
			if (santas[idx].isFail)
				continue;

			santas[idx].score += 1;
		}
	}

	public static boolean isFinish() {
		int cnt = 0;

		for (int idx = 1; idx <= P; idx++) {
			// 탈락한 산타 카운트
			if (santas[idx].isFail) {
				cnt++;
			}
		}

		return cnt == P;
	}

	public static boolean checkRange(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}

	static class Santa {
		int r;
		int c;
		int score;
		int isFaint;
		boolean isFail;

		public Santa(int r, int c) {
			this.r = r;
			this.c = c;
			this.score = 0;
			this.isFaint = 0;
			this.isFail = false;
		}
	}

	static class Pos {
		int r;
		int c;
		int distance;
		int dir;

		public Pos(int r, int c, int distance, int dir) {
			this.r = r;
			this.c = c;
			this.distance = distance;
			this.dir = dir;
		}
	}

}
