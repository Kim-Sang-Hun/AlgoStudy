package notFin;

import java.io.*;
import java.util.*;

/*
* 제목
* <미네랄 2> G1
* 링크
* https://www.acmicpc.net/problem/18500
* 요약
* 주어진 높이에서 좌우 번갈아가며 막대기 던지기
* 미네랄이 막대에 깨지면, 바닥으로 떨어질 미네랄 있나 확인하고 있으면 떨어뜨리기
* 최종 결과 출력하기
* 풀이
* 시뮬레이션
*/
public class boj_18500 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int i, j;

		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Pos [i=" + i + ", j=" + j + "]";
		}
	}

	static final int LEFT = 0;
	static final int RIGHT = 1;
	static final int[] di = { -1, 1, 0, 0 }; // 상 하 좌 우
	static final int[] dj = { 0, 0, -1, 1 };

	static int R, C; // 동굴은 R×C칸으로 이루어져 있다. 각 칸은 비어있거나 미네랄을 포함하고 있으며, 네 방향 중 하나로 인접한 미네랄이 포함된 두 칸은 같은
						// 클러스터이다.
	static char[][] cave; // 다음 R개 줄에는 C개의 문자가 주어지며, '.'는 빈 칸, 'x'는 미네랄을 나타낸다.
	static int N; // 다음 줄에는 막대를 던진 횟수 N이 주어진다. (1 ≤ N ≤ 100)

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cave = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < C; j++) {
				cave[i][j] = str.charAt(j);
			}
		}
		// 풀이
		N = Integer.parseInt(br.readLine().trim()); // N회 던짐. 짝수면 왼쪽, 홀수면 오른쪽에서 던짐
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) { // N회 던지기
			int inputHeight = Integer.parseInt(st.nextToken()); // 입력받은 높이
			int height = R - inputHeight;
			if (i % 2 == 0) { // height 행에서 왼쪽에서 처음 만나는 미네랄 부수기
				destroyMineral(height, LEFT);
			} else { // height 행에서 오른쪽에서 처음 만나는 미네랄 부수기
				destroyMineral(height, RIGHT);
			}
			Pos p = isThereFallingCluster();
			if (p == null) // 떨어져야 하는 미네랄 없으면 다음 막대기 던지기 하러 감
				continue;

			// 이하는 떨어져야 하는 미네랄 있는 경우
			// p = 떨어져야하는 미네랄클러스터의 한 부분
			ArrayList<Pos> fallingMinerals = getFallingMineralCluster(p); // 낙하중인 미네랄 클러스터의 좌표들

			// 미네랄 떨어뜨리기
			setMineral(fallingMinerals);
		}

		// 출력
		printMap();
		bw.write(sb.toString());
		bw.flush();
	}

	private static void setMineral(ArrayList<Pos> cluster) {

		int mineralCount = cluster.size();
		for (int i = 0; i < mineralCount; i++) {
			int ni = cluster.get(i).i + 1;
			int nj = cluster.get(i).j;

		}

	}

	private static ArrayList<Pos> getFallingMineralCluster(Pos start) {
		ArrayList<Pos> list = new ArrayList<>();
		Queue<Pos> q = new ArrayDeque<>();
		boolean[][] isChecked = new boolean[R][C];
		isChecked[start.i][start.j] = true;
		list.add(start);
		q.offer(start);
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				if (!isIn(ni, nj)) // 범위 외 인 경우 continue
					continue;
				if (cave[ni][nj] != 'x') // 미네랄 아니면 continue
					continue;
				if (isChecked[ni][nj]) // 이미 리스트에 삽입한 미네랄이면 continue
					continue;
				isChecked[ni][nj] = true;
				list.add(new Pos(ni, nj));
				q.offer(new Pos(ni, nj));
			}
		}
		return list;
	}

	private static Pos isThereFallingCluster() {
		boolean[][] isChecked = new boolean[R][C];

		// 바닥에 붙은 미네랄들을 시작점으로 공중에 떠있는 지 조사
		// 공중에 떠있지 않다면, 바닥에 붙은 미네랄이랑 이어져 있을 것
		Queue<Pos> q = new ArrayDeque<>();
		int ground = R - 1;
		for (int j = 0; j < C; j++) {
			if (cave[ground][j] == 'x') {
				isChecked[ground][j] = true;
				q.offer(new Pos(ground, j));
			}
		}
		// bfs 돌려서 조사 시작
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				if (!isIn(ni, nj)) // 범위 외 인 경우 continue
					continue;
				if (cave[ni][nj] != 'x') // 미네랄 아니면 continue
					continue;
				if (isChecked[ni][nj]) // 이미 연결되었는 지 체크한 미네랄이면 continue
					continue;
				isChecked[ni][nj] = true;
				q.offer(new Pos(ni, nj));
			}
		}

		// 체크되지 않은 미네랄 == 공중에 떠 있는 미네랄
		// 문제 조건으로 두 개 이상의 미네랄이 떨어져야 하는 경우는 없다고 했으므로 하나 찾으면 바로 리턴해준다.
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (cave[i][j] == 'x' && isChecked[i][j] == false) {
					return new Pos(i, j);
				}
			}
		}
		return null;
	}

	private static boolean isIn(int i, int j) {
		if (0 <= i && i < R && 0 <= j && j < C)
			return true;
		return false;
	}

	// 미네랄 하나 부수기
	private static void destroyMineral(int height, int direction) {
		if (direction == LEFT) {
			for (int j = 0; j < C; j++) {
				if (cave[height][j] == 'x') {
					cave[height][j] = '.';
					break;
				}
			}

		} else { // direction==RIGHT
			for (int j = C - 1; j >= 0; j--) {
				if (cave[height][j] == 'x') {
					cave[height][j] = '.';
					break;
				}
			}
		}
	}

	// 동굴 상태 출력
	private static void printMap() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(cave[i][j]);
			}
			sb.append("\n");
		}
	}
}
