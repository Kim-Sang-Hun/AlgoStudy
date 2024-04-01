package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
/*
 * 상어번호 1이상 M이하
 * 1번 상어는 나머지를 모두 쫓아낼 수 있음
 * 처음: 자신의 위치에 자신의 냄새를 뿌림
 * 1초마다 모든 상어가 상하좌우 중 하나로 이동하고 자신의 냄새를 뿌림
 * 냄사는 상어가 k번 이동하면 사라짐
 * 이동방법 : 인접한 칸 중 아무 냄새가 없는 칸으로, 그런 칸이 없으면 자신의 냄새가 있는 칸으로
 * 가능한 칸이 여러 개일 경우 : 우선 순위가 상어마다 다름 ㅅㅂ
 * 상어가 맨 처음 보고있는 방향이 주어지고 이후에는 방금 이동한 방향이 보고있는 방향이 됨
 * 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어만 남는다.
 * */
public class JUN19237 {
	static int N,M,k, sharkCnt, time;
	static HashMap<Integer, int[]> dir = new HashMap<>();
	static Shark[] sharks;
	static Square[][] map;
	
	static class Shark{
		int r, c, lookAt;
		HashMap<Integer, int[]> dr = new HashMap<>();
		boolean isDead;

		public Shark(int r, int c) {
			this.r = r;
			this.c = c;
			dr.put(1, new int[4]);
			dr.put(2, new int[4]);
			dr.put(3, new int[4]);
			dr.put(4, new int[4]);
		}
	}
	
	static class Square{
		int shark, smell[];

		public Square(int shark) {
			this.shark = shark;
			smell = new int[2];
		}
	}
	
	private static void makeMap() {
		dir.put(1, new int[] {-1,0}); // 위
		dir.put(2, new int[] {1,0}); // 아래
		dir.put(3, new int[] {0,-1}); // 왼쪽
		dir.put(4, new int[] {0,1}); // 오른쪽
	}
	
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new Square[N][N];
		sharks = new Shark[M+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = new Square(Integer.parseInt(st.nextToken()));
				
				if (map[i][j].shark != 0) {
					sharks[map[i][j].shark] = new Shark(i, j);
					sharkCnt++;
				}
			}
		}
		
	
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks[i].lookAt = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					sharks[i].dr.get(j)[k] = Integer.parseInt(st.nextToken());
				}
			}
		}
	}
	
	private static void move(int i, int nr, int nc) {
		map[sharks[i].r][sharks[i].c].shark = 0; // 나 출발
		
		if (map[nr][nc].shark != 0) { // 이미 상어가 있다면
			if(map[nr][nc].shark < i) { // 나보다 작다면 내가 죽음
				sharks[i].isDead = true;
				sharkCnt--;
			}else { // 나보다 크면 있던 애가 죽고 내가 이동
				sharks[map[nr][nc].shark].isDead = true;
				sharkCnt--;
				
				sharks[i].r = nr;
				sharks[i].c = nc; // 이동
				map[nr][nc].shark = i; 
			}
		}else { // 상어 없으면 내가 이동
			sharks[i].r = nr;
			sharks[i].c = nc; // 이동
			map[nr][nc].shark = i; 
		}
	}
	
	private static void simulation() {
		while(time < 1000) {
			if(sharkCnt <= 1) break;
			time++;

			//냄새 뿌리기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].shark != 0) {
						map[i][j].smell[0] = map[i][j].shark;
						map[i][j].smell[1] = k;
					}
				}
			}

			//상어 이동
			for (int i = 1; i <= M; i++) {
				if (sharks[i].isDead) continue; // 죽은 상어
				
				// 인접한 칸 중 냄새 없는 칸 탐색
				List<Integer> hasSmell = new ArrayList<>(); 
				List<Integer> noSmell = new ArrayList<>(); 
				for (int j = 1; j <= 4; j++) {
					int nr = sharks[i].r + dir.get(j)[0];
					int nc = sharks[i].c + dir.get(j)[1];
					
					if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
					
					if (map[nr][nc].smell[0] == 0) noSmell.add(j); // 냄새 없다면
					else hasSmell.add(j);
				}
				
				// 냄새 있는 칸 4개면 자신의 냄새가 있는 칸으로 이동하고 바라보는 방향 갱신
				if (noSmell.size() == 0) {
					for (int priority : sharks[i].dr.get(sharks[i].lookAt)) {
						if (hasSmell.contains(priority)) {
							int nr = sharks[i].r + dir.get(priority)[0];
							int nc = sharks[i].c + dir.get(priority)[1];
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
							
							if(map[nr][nc].smell[0] == i) {
								move(i, nr, nc);
								sharks[i].lookAt = priority; // 방향 갱신
								break;
							}
						}
						
						
					}
				}
				// 냄새 있는 칸 4개 이하면 우선순위 가장 높은 칸으로 이동하고 바라보는 방향 갱신
				else {
					for (int priority : sharks[i].dr.get(sharks[i].lookAt)) { // 현재 보고있는 방향의 우선순위 배열
						if (noSmell.contains(priority)) { // 우선순위 높은 것부터 탐색해서 찾으면
							int nr = sharks[i].r + dir.get(priority)[0];
							int nc = sharks[i].c + dir.get(priority)[1];
							
							if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
		
							move(i, nr, nc);
							sharks[i].lookAt = priority; // 방향 갱신
							break;
						}
					}
				}
				
			}
			
			//냄새 업데이트
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].smell[0] != 0) map[i][j].smell[1]--; // 남아있는 냄새가 있으면 1 줄임
					if (map[i][j].smell[1] == 0) map[i][j].smell[0] = 0; // 남아있는 냄새가 0이 되면 없앰.
				}
			}	
		}
	}

	public static void main(String[] args) throws IOException {
		// 초기화
		makeMap();
		input();
		// 실행
		simulation();
		//츨력
		if (sharkCnt > 1) System.out.println(-1);
		else System.out.println(time);
	}
}
