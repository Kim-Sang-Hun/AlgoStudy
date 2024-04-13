package codetree;

import java.io.*;
import java.util.*;

/*
* 제목
* <포탑 부수기> 
* 링크
* https://www.codetree.ai/training-field/frequent-problems/problems/destroy-the-turret/
* 요약
* "최단 거리로 갈 수 있을 때만 레이저로 쏜다" 가 아니라 "경로가 있을 때 레이저로 쏘는데 그 중 최단거리로 쏜다"
* 풀이
* 시뮬레이션
*/

public class codetree_포탑부수기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Turret {
		int i, j, attackTime;
		boolean islive;

		public Turret(int i, int j) {
			this.i = i;
			this.j = j;
			this.attackTime = 0;
			islive = true;
		}

		@Override
		public String toString() {
			return "Turret [i=" + i + ", j=" + j + ", attackTime=" + attackTime + "]";
		}
	}

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

	static int N, M, K; // 격자 크기, 턴 횟수
	static int[][] arr;
	static ArrayList<Turret> turret; // i번 터렛의 좌표, 최근발사 시간

	static int time; // K회 반복 위한 인덱스 변수
	static int ATTACK_ADDER;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		ATTACK_ADDER = M + N;
		turret = new ArrayList<>();

		arr = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] > 0) {
					turret.add(new Turret(i, j));
				}
			}
		}

		// 풀이
		// K번 반복
		for (int time = 1; time <= K; time++) {
			int howManyTurret = 0;
			for (Turret t : turret) {
				if (t.islive)
					howManyTurret++;
			}
			if (howManyTurret <= 1)
				break;
//			System.out.println("TIME = "+time);
			// 공격자, 타겟 선정
			int attacker = findAttacker();
//			System.out.println("attacker index " + turret.get(attacker));		
			int target = findTarget();
//			System.out.println("target index " + turret.get(target));

			turret.get(attacker).attackTime = time; // 최근 발사 시간 갱신
			arr[turret.get(attacker).i][turret.get(attacker).j] += ATTACK_ADDER;
			
//			for(int i=1; i<=N; i++)  {
//				for(int j=1; j<=M; j++) {
//					System.out.print(arr[i][j]+"	");
//				}
//				System.out.println();
//			}
//			System.out.println("===============================");

			// 레이저 쏠 수 있는 지 확인
			minDistance = Integer.MAX_VALUE;
			path = new ArrayList<>();
			isVisited = new boolean[N + 1][M + 1];
			canLaser(turret.get(attacker).i, turret.get(attacker).j, turret.get(target).i, turret.get(target).j);

			// 쏠 수 있으면
			if (laserCheck) {
//				System.out.println("경로 있음");
//				System.out.println("PATH 길이 "+path.size());
//				for(Pos p : path) {
//					System.out.println(p);
//				}
				attackByLaser(arr[turret.get(attacker).i][turret.get(attacker).j]);
			}
			// 쏠 수 없으면
			else { // laserCheck == false
//				System.out.println("경로 없으니까 폭탄 날려");
				attackByBomb(attacker, target, arr[turret.get(attacker).i][turret.get(attacker).j]);

			}

			// 남은 터렛들 +1
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					if (isVisited[i][j] == false && arr[i][j] > 0) {
						arr[i][j] += 1;
					}
				}
			}

//			for(int i=1; i<=N; i++)  {
//				for(int j=1; j<=M; j++) {
//					System.out.print(arr[i][j]+"	");
//				}
//				System.out.println();
//			}
//			System.out.println("===============================");
		}
		// 출력 : arr 에서 가장 강한 포탑의 공격력 출력
		int maxAttackPoint = Integer.MIN_VALUE;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (arr[i][j] > maxAttackPoint) {
					maxAttackPoint = arr[i][j];
				}
			}
		}
//		for (int i = 1; i <= N; i++) {
//			for (int j = 1; j <= M; j++) {
//				System.out.print(arr[i][j]+"	");
//			}
//			System.out.println(); 
//		}
		sb.append(maxAttackPoint);
		bw.write(sb.toString());
		bw.flush();

	}

	static int[] d8i = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] d8j = { -1, 0, 1, -1, 1, -1, 0, 1 };

	private static void attackByBomb(int attacker, int target, int attackPoint) {
		for (boolean[] vis : isVisited) {
			Arrays.fill(vis, false);
		}
		// 시작점
		Turret att = turret.get(attacker);
		isVisited[att.i][att.j] = true;

		// 타겟지점
		Turret tar = turret.get(target);
		arr[tar.i][tar.j] -= attackPoint;
		if (arr[tar.i][tar.j] <= 0) {
			arr[tar.i][tar.j] = 0;
			tar.islive = false;
		}
		isVisited[tar.i][tar.j] = true;

		// 8방
//		System.out.println("target : "+tar.i+" "+tar.j);
		for (int d = 0; d < 8; d++) {
			int ni = tar.i + d8i[d];
			int nj = tar.j + d8j[d];
			if (ni > N)
				ni = 1;
			else if (ni < 1)
				ni = N;
			if (nj > M)
				nj = 1;
			else if (nj < 1)
				nj = M;
			// 공격자는 해당 공격에 영향 받지 않음
			if (ni == att.i && nj == att.j)
				continue;
			if (arr[ni][nj] == 0) {
//				System.out.println("ni nj is "+ni+" "+nj);
				continue;
			}
			arr[ni][nj] -= (attackPoint / 2);
			if (arr[ni][nj] <= 0) {
				arr[ni][nj] = 0;
				int idx = findTurretWithPos(ni, nj);
				turret.get(idx).islive = false;
			}
			isVisited[ni][nj] = true;
		}
	}

	private static void attackByLaser(int attackPoint) {
		for (boolean[] vis : isVisited) {
			Arrays.fill(vis, false);
		}
		// 시작점
		Pos p = path.get(path.size() - 1);
		isVisited[p.i][p.j] = true;
		// 끝점
		p = path.get(0);		
		arr[p.i][p.j] -= attackPoint;
		if (arr[p.i][p.j] <= 0) {
			arr[p.i][p.j] = 0;
			int idx = findTurretWithPos(p.i, p.j);
			turret.get(idx).islive = false;
		}
		isVisited[p.i][p.j] = true;
		// 경로
		for (int i = path.size() - 2; i >= 1; i--) {
			p = path.get(i);
//			System.out.println("BOOM "+p);
			arr[p.i][p.j] -= (attackPoint / 2);
			if (arr[p.i][p.j] <= 0) {
				arr[p.i][p.j] = 0;
				int idx = findTurretWithPos(p.i, p.j);
				turret.get(idx).islive = false;
			}
			isVisited[p.i][p.j] = true;
		}
	}

	static boolean laserCheck;
	static ArrayList<Pos> path;
	static ArrayList<Pos> finalPath;
	static boolean[][] isVisited;
	static int minDistance;
	
	private static void canLaser(int si, int sj, int ei, int ej) {
		int[][] dpPath = new int[N + 1][M + 1];
		for (int[] dp : dpPath)
			Arrays.fill(dp, Integer.MAX_VALUE);

		Queue<Pos> q = new ArrayDeque<>();
		q.offer(new Pos(si, sj));
		dpPath[si][sj] = 0; // 시작점 체크

		int dist = 0;
		while (!q.isEmpty()) {
			dist++;
			int cnt = q.size();
			for (int k = 0; k < cnt; k++) {
				Pos cur = q.poll();
				int ci = cur.i;
				int cj = cur.j;
				for (int d = 0; d < 4; d++) {
					int ni = ci + d4i[d];
					int nj = cj + d4j[d];
					if (ni > N)
						ni = 1;
					else if (ni < 1)
						ni = N;
					else if (nj > M)
						nj = 1;
					else if (nj < 1)
						nj = M;
					if (arr[ni][nj] != 0 && dpPath[ni][nj] > dist) {
						dpPath[ni][nj] = dist;
						q.offer(new Pos(ni, nj));
					}
				}	
			}
		}
		
		// 경로 없는 경우
		if(dpPath[ei][ej]==Integer.MAX_VALUE) {
			laserCheck = false;
//			System.out.println(si+" "+sj+" to "+ei+" "+ej+" 경로 없음");
			return;
		}
		
		// 경로 있는 경우 -> 경로 생성
		laserCheck = true;
		path.add(new Pos(ei, ej));
		int ci = ei, cj = ej;
		int minimumDist = dpPath[ei][ej];
		while(minimumDist >= 0) {
			minimumDist--;
			for(int d=3; d>=0; d--) {
				int ni = ci + d4i[d];
				int nj = cj + d4j[d];
				if (ni > N)
					ni = 1;
				else if (ni < 1)
					ni = N;
				else if (nj > M)
					nj = 1;
				else if (nj < 1)
					nj = M;
				if(dpPath[ni][nj]==minimumDist) {
					path.add(new Pos(ni, nj));
					ci = ni;
					cj = nj;
					break;
				}
			}
		}
//		System.out.println(si+" "+sj+" to "+ei+" "+ej+" 경로 길이 "+path.size());
	}
	
	static int[][] visit;
	static int[] d4i = { 0, 1, 0, -1 }; // 우 하 좌 상
	static int[] d4j = { 1, 0, -1, 0 };

	private static int findTarget() {
		int len = turret.size();
		int turretIdx = -1;
		int maxAttackPoint = Integer.MIN_VALUE;
		int minAttackTime = Integer.MAX_VALUE;
		int minIJ = Integer.MAX_VALUE;
		int minCol = Integer.MAX_VALUE;
		for (int k = 0; k < len; k++) {
			int curi = turret.get(k).i;
			int curj = turret.get(k).j;
			int curTime = turret.get(k).attackTime;

			if (maxAttackPoint < arr[curi][curj]) {
				turretIdx = k;
				maxAttackPoint = arr[curi][curj];
				minAttackTime = curTime;
				minIJ = curi + curj;
				minCol = curj;
			} else if (maxAttackPoint == arr[curi][curj]) {
				if (minAttackTime > curTime) {
					turretIdx = k;
					maxAttackPoint = arr[curi][curj];
					minAttackTime = curTime;
					minIJ = curi + curj;
					minCol = curj;
				} else if (minAttackTime == curTime) {
					if (minIJ > curi + curj) {
						turretIdx = k;
						maxAttackPoint = arr[curi][curj];
						minAttackTime = curTime;
						minIJ = curi + curj;
						minCol = curj;
					} else if (minIJ == curi + curj) {
						if (minCol > curj) {
							turretIdx = k;
							maxAttackPoint = arr[curi][curj];
							minAttackTime = curTime;
							minIJ = curi + curj;
							minCol = curj;
						}
					}
				}
			}
		}
		return turretIdx;
	}

	private static int findAttacker() {
		int len = turret.size();
		int turretIdx = -1;
		int minAttackPoint = Integer.MAX_VALUE;
		int maxAttackTime = Integer.MIN_VALUE;
		int maxIJ = Integer.MIN_VALUE;
		int maxCol = Integer.MIN_VALUE;
		for (int k = 0; k < len; k++) {
			int curi = turret.get(k).i;
			int curj = turret.get(k).j;
			int curTime = turret.get(k).attackTime;
			if (arr[curi][curj] == 0)
				continue;

			if (minAttackPoint > arr[curi][curj]) {
				turretIdx = k;
				minAttackPoint = arr[curi][curj];
				maxAttackTime = curTime;
				maxIJ = curi + curj;
				maxCol = curj;
			} else if (minAttackPoint == arr[curi][curj]) {
				if (maxAttackTime < curTime) {
					turretIdx = k;
					minAttackPoint = arr[curi][curj];
					maxAttackTime = curTime;
					maxIJ = curi + curj;
					maxCol = curj;
				} else if (maxAttackTime == curTime) {
					if (maxIJ < curi + curj) {
						turretIdx = k;
						minAttackPoint = arr[curi][curj];
						maxAttackTime = curTime;
						maxIJ = curi + curj;
						maxCol = curj;
					} else if (maxIJ == curi + curj) {
						if (maxCol < curj) {
							turretIdx = k;
							minAttackPoint = arr[curi][curj];
							maxAttackTime = curTime;
							maxIJ = curi + curj;
							maxCol = curj;
						}
					}
				}
			}
		}
		return turretIdx;
	}

	private static int findTurretWithPos(int i, int j) {
		int len = turret.size();
		int turretIdx = -1;
		for (int k = 0; k < len; k++) {
			if (turret.get(k).i == i && turret.get(k).j == j) {
				turretIdx = k;
				break;
			}
		}
		return turretIdx;
	}
}
