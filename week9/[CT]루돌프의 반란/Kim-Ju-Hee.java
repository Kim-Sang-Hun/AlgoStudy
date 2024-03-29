package march5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 좌상단 1,1
 * M개의 턴마다 루돌프가 먼저 움직이고 1-P번 산타들이 순서대로 한 번씩
 * 두 칸 사이의 거리는 피타고라스
 * M번의 턴이 끝나거나, P명의 산타가 모두 탈락하면 게임 종료
 * 매 턴 이후 탈락하지 않은 산타에게 1점씩 부여
 * 
 * - 루돌프
 * 탈락하지 않은 산타들 중 가장 가까운 산타를 향해 1칸 돌진
 * 가장 가까운 산타가 2명 이상이면 r좌표가 큰 산타를 향해 돌진, r같으면 c가 큰 쪽으로
 * 상하좌우, 대각선까지 8방향 중 하나로 돌진할 수 있음
 * 
 * - 산타
 * 기절해있거나 탈락한 산타는 안 움직임
 * 루돌프에게 거리가 가장 가까워지는 방향으로 1칸 이동
 * 다른 산타가 있는 칸이나 게임판 밖으로는 움직일 수 없음 -> 안 움직임
 * 움직일 수 있는 칸이 있어도 루돌프로부터 가까워질 수 있는 방법이 없다면 안 움직임
 * 산타는 인접한 4방향 중 한 곳으로 움직일 수 있는데, 여러 개라면 상-우-하-좌 우선순위
 * -- 루돌프와 충절하면 기절, k번째에 기절하면 k+2부터 다시 정상상태
 * -- 기절한 산타는 움직일 수 없음
 * -- 루돌프는 기절한 산타에게 돌진할 수 있음
 *
 * 
 * - 충돌
 * 루돌프->산타 : 산타가 C만큼의 점수를 얻고 산타는 루돌프가 이동해온 방향으로 C칸만큼 밀려남
 * 산타->루돌프 : 산타가 D만큼의 점수를 얻고 산타는 자신이 이동해온 반대방향으로 D칸만큼 밀려남
 * 밀려난 위치가 게임판 밖이라면 산타 탈락
 * 밀려난 칸에 다른 산타가 있는 경우 상호작용
 * 
 * - 상호작용
 * 밀려난 칸에 다른 산타가 있는 경우 그 산타는 1칸 해당 방향으로 연쇄적으로 밀려남
 * 게임판 밖으로 밀려나는 산타는 탈락
 * 
 * */
public class CT루돌프의반란_김주희 {
	static int N, M, P, C, D, rudolfR, rudolfC, map[][], score[];
	static Santa[] santas;
	static int dr[] = new int[] {0,1,0,-1,1,1,-1,-1}; // 상 우 하 좌 반대
	static int dc[] = new int[] {-1,0,1,0,1,-1,-1,1};
	
	static class Santa implements Comparable<Santa>{
		int r, c, stun;
		double dist;
		boolean out;
		
		public Santa(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public void calDist() {
			dist = Math.pow(r - rudolfR, 2) + Math.pow(c - rudolfC, 2);
		}

		@Override
		public int compareTo(Santa o) {
			if(this.dist == o.dist && this.r == o.r) return o.c - this.c;
			else if(this.dist == o.dist) return o.r - this.r;
			return (int)this.dist - (int)o.dist;
		}

		@Override
		public String toString() {
			return "Santa [r=" + r + ", c=" + c + ", stun=" + stun + ", dist=" + dist + ", out=" + out + "]";
		}

	}
	
	private static double calDistRudolf(int r, int c) {
		return Math.pow(r - rudolfR, 2) + Math.pow(c - rudolfC, 2);
	}
	
	private static void moveS(int santaIdx) {
		// 산타 이동 방향 정하기
		int nr = 0;
		int nc = 0;
		double nowDist = calDistRudolf(santas[santaIdx].r, santas[santaIdx].c); // 현재 루돌프와 산타의 거리
		
		for (int i = 0; i < 4; i++) {
			int tempR = santas[santaIdx].r + dr[i];
			int tempC = santas[santaIdx].c + dc[i];
			
			if(tempR < 1 || tempR >= N+1 || tempC < 1 || tempC >= N+1) continue;
			
			if(map[tempR][tempC] > 0) continue; // 산타 있으면 업뎃 안함
			
			double tempDist = calDistRudolf(tempR,tempC);
			if(tempDist <= nowDist) {
				nr = tempR;
				nc = tempC;
				nowDist = tempDist;
			}
		}
		
		// 산타가 갈 수 있는 곳이 없으면
		if(nr == 0 && nc == 0) return;
		
		// 방향 정하기
		int dr = nr - santas[santaIdx].r;
		int dc = nc - santas[santaIdx].c;
		
		// 루돌프 있다면 충돌
		if(map[nr][nc] == -1) {
			crushStoR(santaIdx, -dr, -dc);
		}
		else {
			// 산타 이동
			map[santas[santaIdx].r][santas[santaIdx].c] = 0;
			santas[santaIdx].r = nr;
			santas[santaIdx].c = nc;
			map[nr][nc] = santaIdx;
		}

	}
	
	private static void moveR(Santa target) {
		// 방향 정하기
		int dr = target.r - rudolfR;
		int dc = target.c - rudolfC;
		
		if(dr != 0) dr /= Math.abs(dr);
		if(dc != 0) dc /= Math.abs(dc); // 값 1로 만들어주기
		
		// 이동하기
		int nr = rudolfR + dr;
		int nc = rudolfC + dc;
		
		// 산타 있다면 충돌
		if(map[nr][nc] > 0) crushRtoS(map[nr][nc], dr, dc);
		
		// 루돌프 이동
		map[rudolfR][rudolfC] = 0;
		rudolfR = nr;
		rudolfC = nc;
		map[rudolfR][rudolfC] = -1;
		
	}
	
	private static void crushStoR(int santaIdx, int dr, int dc) { // 지금산타
		// 산타가 D만큼의 점수를 얻고 산타는 자신이 이동해온 반대방향으로 D칸만큼 밀려남
		santas[santaIdx].stun = 2;
		score[santaIdx] += D;
		
		map[santas[santaIdx].r][santas[santaIdx].c] = 0;

		int nr = rudolfR + dr*D;
		int nc = rudolfC + dc*D;
		
		// 맵 밖으로 나가면
		if(nr < 1 || nr >= N+1 || nc < 1 || nc >= N+1) {
			santas[santaIdx].out = true;
			return;
		}
			
		// 산타 있으면 상호작용
		if(map[nr][nc] > 0) interaction(map[nr][nc], dr, dc);
		
		// 산타 위치 업데이트
		santas[santaIdx].r = nr;
		santas[santaIdx].c = nc;
		map[nr][nc] = santaIdx;
	}
	
	private static void crushRtoS(int santaIdx, int dr, int dc) {
		// 산타가 C만큼의 점수를 얻고 산타는 루돌프가 이동해온 방향으로 C칸만큼 밀려남
		santas[santaIdx].stun = 2;
		score[santaIdx] += C;
		
		int nr = santas[santaIdx].r + dr*C;
		int nc = santas[santaIdx].c + dc*C;
		
		// 맵 밖으로 나가면
		if(nr < 1 || nr >= N+1 || nc < 1 || nc >= N+1) {
			santas[santaIdx].out = true;
			return;
		}
			
		// 산타 있으면 상호작용
		if(map[nr][nc] > 0) interaction(map[nr][nc], dr, dc);
		
		// 산타 위치 업데이트
		santas[santaIdx].r = nr;
		santas[santaIdx].c = nc;
		map[nr][nc] = santaIdx;
	}

	private static void interaction(int santaIdx, int dr, int dc) {
		int nr = santas[santaIdx].r + dr;
		int nc = santas[santaIdx].c + dc;
		
		// 맵 밖으로 나가면
		if(nr < 1 || nr >= N+1 || nc < 1 || nc >= N+1) {
			santas[santaIdx].out = true;
			return;
		}
		
		if(map[nr][nc] > 0) interaction(map[nr][nc], dr, dc);
		
		santas[santaIdx].r = nr;
		santas[santaIdx].c = nc;
		map[nr][nc] = santaIdx;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 게임판의 크기
		M = Integer.parseInt(st.nextToken()); // 게임 턴 수
		P = Integer.parseInt(st.nextToken()); // 산타의 수
		C = Integer.parseInt(st.nextToken()); // 루돌프의 힘
		D = Integer.parseInt(st.nextToken()); // 산타의 힘
		
		map = new int[N+1][N+1];
		santas = new Santa[P+1];
		score = new int[P+1];
		
		st = new StringTokenizer(br.readLine());
		rudolfR = Integer.parseInt(st.nextToken());
		rudolfC = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			santas[Integer.parseInt(st.nextToken())] = new Santa(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		// map 초기화
		map[rudolfR][rudolfC] = -1; // 루돌프
		for (int i = 1; i <= P; i++) {
			map[santas[i].r][santas[i].c] = i;
		}
		
		PriorityQueue<Santa> q = new PriorityQueue<>();
		while(M > 0) {
			M--;
			
			// 거리 계산해서 루돌프의 목표 산타 정하기
			q.clear();
			for (int i = 1; i <= P; i++) {
				if(santas[i].out) continue;
				santas[i].calDist();
				q.add(santas[i]);
			}
			
			// 루돌프 이동
			moveR(q.poll());
			
			// 산타 이동
			for (int i = 1; i <= P; i++) {
				if(santas[i].out) 
					continue;
				else if(santas[i].stun > 0) {
					continue;
				}else {
					moveS(i);
				}
			}
			
			boolean flag = false;
			for (int i = 1; i <= P; i++) {
				if(!santas[i].out) {
					score[i]++;
					flag = true; // 한명이라도 살아있으면 갱신
					if(santas[i].stun > 0) santas[i].stun--; // 기절한거 한번 깨움
				}
			}
			
			if(!flag) break; // 다 죽었으면 게임 끝남
		}
		
		
		for (int i = 1; i <= P; i++) {
			System.out.print(score[i]);
			System.out.print(" ");
		}

	}

}
