// 루돌프 전부 곰탕 끓여야 함

import java.util.*;
import java.io.*;

/*
 * 제목
 * <루돌프의 반란> G2
 * 링크
 * https://www.codetree.ai/training-field/frequent-problems/problems/rudolph-rebellion/
 * 요약
 * 잘 읽고 잘 작성하기
 * 풀이
 * 시뮬레이션
 */
public class codetree_루돌프의반란 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int i;
		int j;

		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Pos [" + i + "][" + j + "]";
		}
	}

	static int N; // 게임판 크기 N*N
	static int M; // 게임 턴 수
	static int P; // 산타 수 1~P
	static int C; // 루돌프의 힘
	static int D; // 산타의 힘

	// 게임판
	static int[][] arr;

	// 루돌프 관련
	static Pos deer; // 루돌프 좌표
	static int distance[]; // 루돌프와 [i]번 산타와의 거리
	static int[] deeri = { -1, -1, -1, 0, 0, 1, 1, 1 }; // 좌상, 상, 우상, 좌, 우, 좌하, 하, 우하
	static int[] deerj = { -1, 0, 1, -1, 1, -1, 0, 1 };

	// 산타 관련
	static Pos[] santa; // 산타 좌표
	static int[] isStunned; // 루돌프랑 충돌 시 산타 기절
	static int[] score; // [i]번 산타의 점수
	static int[] di = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int[] dj = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		// 게임판
		arr = new int[N + 1][N + 1];

		// 루돌프
		st = new StringTokenizer(br.readLine());
		deer = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		distance = new int[P + 1];

		// 산타
		santa = new Pos[P + 1];
		isStunned = new int[P + 1];
		score = new int[P + 1];
		for (int i = 1; i <= P; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			santa[num] = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		// 풀이
		for (int i = 0; i < M; ++i) {
//			System.out.println("======" + (i+1) + "번째 시도======");
			simulation();
//			System.out.println("\n사슴 " + deer);
//			for (int k = 1; k <= P; k++) {
//				System.out.println("산타[" + k + "] " + santa[k]);
//			}
			// 턴 종료 후 살아있는 산타들 점수 +1
//			System.out.println();
			boolean check = false; // 턴 끝나기 전에 모든 산타 탈락 시 종료
			for (int k = 1; k <= P; ++k) {
				if (santa[k] != null) {
					check = true;
//					System.out.println(k+"번 산타 살아있음 +1");
					score[k]++;
				}
			}
//			System.out.println("\n점수 " + Arrays.toString(score));
			if(!check) break;
		}

		// 출력
		for(int i=1; i<=P; ++i)
			sb.append(score[i]+" ");
		bw.write(sb.toString());
		bw.flush();
	}

	private static void simulation() {
		// 0. 기절 관리
		for (int i = 1; i <= P; ++i) { // 기절 -1
			if (isStunned[i] > 0)
				isStunned[i]--;
		}
		
		// 1. 루돌프 움직임
		// 가장 가까운 산타 찾기
		for (int i = 1; i <= P; i++) {
			distance[i] = getDistance(deer, santa[i]);
		}
		// 산타 선택
		int selectedSanta = selectSanta();
		// 돌진
		int dir = getChargeDirection(selectedSanta);

		deer.i += deeri[dir];
		deer.j += deerj[dir];
//		System.out.println("사슴 이동"+" ["+deer.i+"]["+deer.j+"]");

		// 돌진할 칸에 산타 있는 지 확인
		int victimSantaNum = checkSantaIsThere(deer.i, deer.j);
		if (victimSantaNum != -1) { // 돌진 자리에 산타 있는 경우
			score[victimSantaNum] += C; // 점수 증가
			// dir 의 역방향으로 C칸 밀기
			int santadi = deeri[dir]; // [k]산타는 루돌프가 돌진한 방향으로 C만큼 날아가야 함
			int santadj = deerj[dir];
			setSantaPosAndStun(victimSantaNum, santadi, santadj, C);
		}

		// 2. 산타들 움직임
		for (int k = 1; k <= P; k++) { // 1~P번 산타 이동
			if (isStunned[k] > 0 || santa[k] == null) // 기절 중이거나 탈락한 산타 제외
				continue;
			int minDist = Integer.MAX_VALUE;
			int toDeerDir = -1;
			int curDist = getDistance(deer, santa[k]);
			for (int d = 0; d < 4; ++d) {
				int ni = santa[k].i + di[d];
				int nj = santa[k].j + dj[d];
				if (!isIn(ni, nj)) // 갈 수 없는 위치면 continue
					continue;
				int dist = getDist(deer.i, deer.j, ni, nj);
				if (curDist <= dist) // 움직여도 현 위치와 같거나 더 멀어지면 continue
					continue;
				int hasSanta = checkSantaIsThere(santa[k].i + di[d], santa[k].j + dj[d]);
				if (hasSanta != -1) // 움직일 위치에 산타 있으면 continue
					continue;
				if (minDist > dist) {
					minDist = dist;
					toDeerDir = d;
				}
			}
			if (toDeerDir == -1)
				continue;

			santa[k].i += di[toDeerDir];
			santa[k].j += dj[toDeerDir];
//			System.out.println("\n산타["+k+"] 이동"+" ["+santa[k].i+"]["+santa[k].j+"]");

			// 이동한 칸에 루돌프 있는 경우
			if (deer.i == santa[k].i && deer.j == santa[k].j) {
				score[k] += D;
				int santadi = -di[toDeerDir]; // [k]산타는 이동 방향의 역방향으로 D만큼 날아가야 함
				int santadj = -dj[toDeerDir];
				setSantaPosAndStun(k, santadi, santadj, D);
			}
		}
	}

	private static void setSantaPosAndStun(int k, int di, int dj, int Power) {
//		System.out.println(k + "번 산타 충돌");
		int ni = santa[k].i;
		int nj = santa[k].j;
		for (int i = 0; i < Power; i++) { // 산타가 떨궈질 자리
			ni += di;
			nj += dj;
		}
		if (!isIn(ni, nj)) {
			santa[k] = null; // 장외로 날아간 경우 산타 아웃
			return;
		} else { // 장내에 떨어진 경우
			int victim = checkSantaIsThere(ni, nj);
			if (victim != -1) { // 해당 위치에 산타 있으면 그 산타도 영향 받음
				setVictimSantaPos(victim, di, dj);
			}
			isStunned[k] = 2;
			santa[k].i = ni;
			santa[k].j = nj;
		}
	}

	private static void setVictimSantaPos(int victim, int di, int dj) {
//		System.out.println(victim + "번 산타 여파에 휘말림");
		int ni = santa[victim].i + di;
		int nj = santa[victim].j + dj;
		if (!isIn(ni, nj)) {
			santa[victim] = null; // 장외로 날아간 경우 산타 아웃
			return;
		} else {
			int nextVictim = checkSantaIsThere(ni, nj);
			if (nextVictim != -1) {
				setVictimSantaPos(nextVictim, di, dj);
			}
			santa[victim].i = ni;
			santa[victim].j = nj;
		}
	}

	private static int checkSantaIsThere(int deerNi, int deerNj) {
		int num = -1;
		for (int k = 1; k <= P; ++k) {
			if (santa[k] != null && santa[k].i == deerNi && santa[k].j == deerNj) {
				num = k;
				break;
			}
		}
		return num;
	}

	private static int getChargeDirection(int k) {
		// 8방향 중 [k]산타와 가장 가까운 칸으로 이동
		int dir = -1;
		int minDist = Integer.MAX_VALUE;

		for (int d = 0; d < 8; ++d) {
			int ni = deer.i + deeri[d];
			int nj = deer.j + deerj[d];
			if (!isIn(ni, nj))
				continue; // 범위 외 이면 continue
			int ndist = getDist(santa[k].i, santa[k].j, ni, nj);
			if (ndist < minDist) {
				minDist = ndist;
				dir = d;
			}
		}
		return dir;
	}

	private static boolean isIn(int ni, int nj) {
		if (1 <= ni && ni <= N && 1 <= nj && nj <= N)
			return true;
		return false;
	}

	// 4/8방향 비교 위해 만든 거리 계산 함수
	private static int getDist(int si, int sj, int ni, int nj) {
		double dist = Math.pow(si - ni, 2) + Math.pow(sj - nj, 2);
		return (int) dist;
	}

	private static int selectSanta() {
		int cur = -1;
		int dist = Integer.MAX_VALUE;
		for (int k = 1; k <= P; ++k) {
			if (distance[k] == -1)
				continue;
			if (dist > distance[k]) {
				cur = k;
				dist = distance[k];
			} else if (dist == distance[k]) { // 거리 같을 때
				if (santa[cur].i == santa[k].i) { // i 좌표도 같으면
					if (santa[cur].j < santa[k].j) { // j 좌표 더 크면 산타 교체
						cur = k;
					}
				} else { // i 좌표 다르면
					if (santa[cur].i < santa[k].i) { // i 좌표 더 크면 산타 교체
						cur = k;
					}
				}
			}
		}
		return cur;
	}

	private static int getDistance(Pos deer, Pos santa) {
		if (santa == null) // 장외탈락한 산타면 -1 거리
			return -1;
		double dist = Math.pow(deer.i - santa.i, 2) + Math.pow(deer.j - santa.j, 2);
		return (int) dist;
	}
}
