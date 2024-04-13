package april2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
// 코드트리 두 문제 다 출력까지 나오는 코드가 캠퍼스 컴퓨터에 있어서...이거라도...
/*
 * 포탑의 공격력이 0이하가 되면 더 이상 공격 못함.
 * 최초의 공격력이 0일 수 있음
 * 부서지지 않은 포탑이 1개가 되면 반복 중지
 * 
 * 1.
 * 부서지지 않은 포탑 중 가장 약한 포탑이 공격자로 선정
 * 선정된 포탑은 공격력이 N+M만큼 증가
 * 공격력이 가장 낮은 포탑이 여러 개 -> 가장 최근에 공격한 포탑
 * 그래도 같은 포탑이 여러 개 -> 각 포탑 위치의 행과 열의 합이 가장 큰 포탑
 * 그래도 같으면 -> 열 값이 가장 큰 포탑
 * 
 * 2.
 * 공격자는 가장 강한 포탑을 공격
 * 공격력이 가장 높은 포탑이 가장 강한 포탑
 * 같으면, 공격한지 가장 오래된 포탑
 * 같으면, 행과 열의 합이 가장 작은 포탑
 * 같으면, 열 값이 가장 작은 포탑
 * 
 * 레이저 공격을 먼저 시도, 안 되면 포탄 공격
 * - 레이저공격
 * : 상하좌우 4개 방향으로 움직임. 부서진 포탑이 있으면 지날 수 없음.
 * 막힌 방향으로 진행하고자 한다면 반대편으로 나옴
 * 공격 대상 포탑까지 최단경로로 공격, 경로가 존재하지 않으면 포탄공격
 * 경로가 여러 개 존재하면, 우-하-좌-상으로 먼저 움직인 경로가 선택됨.
 * 공격받은 포탑은 공격자의 공격력만큼 피해, 경로에 있는 포탑들은 2로 나눈 만큼 피해
 * 
 * - 포탄공격
 * 공격대상은 공격자의 공격력만큼 피해, 주위 8방향 포탑도 절반만큼 피해
 * 공격자는 공격에 영향 받지 않음
 * 가장자리에 포탄이 떨어졌다면 반대편 격자에도 영향 미침
 * 
 * 3.
 * 공격력이 0 이하가 되면 부서짐.
 * 
 * 4.
 * 부서지지 않은 포탑 중 공격과 무관했던 포탑은 공격력이 1씩 올라감
 * 
 * 전체과정 후 가장 강한 포탑의 공격력을 출력
 * 
 * */

public class 포탑부수기_김주희 {
	static int N, M, K;
	static Turrent[][] map;
	static boolean canGo[][];
	static PriorityQueue<Turrent> leak = new PriorityQueue<>();
	static PriorityQueue<Turrent> strong = new PriorityQueue<>(Collections.reverseOrder());
	static int[] dr = new int[] {0,1,0,-1,1,1,-1,-1};
	static int[] dc = new int[] {1,0,-1,0,1,-1,1,-1}; // 앞에서부터 네개 우하좌상
	
	static class Turrent implements Comparable<Turrent>{
		int r, c, power, currentAttackT;
		boolean dead;
		
		public Turrent(int r, int c, int power) {
			this.r = r;
			this.c = c;
			this.power = power;
		}

		@Override
		public int compareTo(Turrent o) {
			if(this.power == o.power) {
				if(o.currentAttackT == this.currentAttackT) {
					if((o.r+o.c) == (this.r+this.c)) {
						return o.c - this.c;
					}else return (o.r+o.c) - (this.r+this.c);
				}else return o.currentAttackT - this.currentAttackT;
			}
			return this.power - o.power;
		}

		@Override
		public String toString() {
			return "Turrent [r=" + r + ", c=" + c + ", power=" + power + ", currentAttackT=" + currentAttackT + "]";
		}
		
	}
	
	static class Route{
		int r, c;
		List<int[]> routes = new ArrayList<>();
		
		public Route(int r, int c, List<int[]> routes) {
			this.r = r;
			this.c = c;
			for (int i = 0; i < routes.size(); i++) {
				this.routes.add(routes.get(i));
			}
		}
	}
	
	private static int next(int i, int dir, boolean flag) { // flag true이면 r
		if(flag) {
			if(i + dir > N) return 1;
			else if( i + dir < 1) return N;
			else return i + dir;
		}else {
			if(i + dir > M) return 1;
			else if( i + dir < 1) return N;
			else return i + dir;
		}
	}
	
	private static List<int[]> bfs(Turrent attacker, Turrent victim){
		Queue<Route> q = new LinkedList<>();
		boolean[][] visited = new boolean[N+1][M+1];
		
		q.add(new Route(attacker.r, attacker.c, new ArrayList<>()));
		visited[attacker.r][attacker.c] = true;
		
		List<int[]> answer = new ArrayList<>();
		while(!q.isEmpty()) {
			Route cur = q.poll();
//				for (int[] is : cur.routes) {
//				System.out.println(Arrays.toString(is));
//			}
//				System.out.println();
			
			cur.routes.add(new int[] {cur.r, cur.c});
			
			//현재꺼가 도착했으면 break
			if(cur.r == victim.r && cur.c == victim.c) {
				answer = cur.routes;
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int nr = next(cur.r,dr[i],true);
				int nc = next(cur.c,dc[i],false);
				
				if(!canGo[nr][nc] || visited[nr][nc]) continue;
				
				visited[nr][nc] = true;
				q.add(new Route(nr,nc,cur.routes));
			}
		}
		
		return answer;
		
	}
	
	private static void Attack() {
		Turrent attacker = leak.poll();
		attacker.power += N+M;
		
		Turrent strong = leak.poll();
		
		List<int[]> r = bfs(attacker, strong);
		
		if(r.size() == 0) { // 포탄공격
			
		}else { // 레이저공격
			Laser(r);
		}
	}
	
	private static void Laser(List<int[]> routes) {
		
		int attackPower = map[routes.get(0)[0]][routes.get(0)[1]].power;
		
		for (int i = 1; i < routes.size()-1; i++) {
			map[routes.get(i)[0]][routes.get(i)[1]].power -= attackPower/2;
		}
		map[routes.get(routes.size()-1)[0]][routes.get(routes.size()-1)[1]].power -= attackPower;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new Turrent[N+1][M+1];
		canGo = new boolean[N+1][M+1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				int power = Integer.parseInt(st.nextToken());
				
				if(power > 0) {
					canGo[i][j] = true;
					map[i][j] = new Turrent(i,j,power);
					leak.add(map[i][j]);
					strong.add(map[i][j]);
				}
			}
			
		}
		
//		for (int i = 1; i <= N ; i++) {
//			for (int j = 1; j <= M; j++) {
//				System.out.print(canGo[i][j]);
//			}
//			System.out.println();
//		}
		
		Attack();
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if(map[i][j] == null) continue;
				
				System.out.println(map[i][j]);
			}
		}
		
		
		


		


	}

}
