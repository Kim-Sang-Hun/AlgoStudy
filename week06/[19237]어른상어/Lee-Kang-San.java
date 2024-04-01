package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <어른 상어> G2
 * 링크
 * https://www.acmicpc.net/problem/19237
 * 요약
 * 주어진 조건에 따라 으른 상어 조작하기
 * 풀이
 * 시뮬레이션
 * 이동시 4방향에 빈 공간 없으면 왔던 곳으로 돌아가는 게 아니라 자기 냄새 나는 곳 중 우선 순위 높은 곳으로 간다.
 */
public class boj_19237 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static class Shark {
		int num; // 상어번호
		int y, x; // 위치
		int dir; // 현재 방향 (1~4) 상하좌우
		int[][] move; //[dir][0~3] 현재 방향에 따른 우선순위 
		
		public Shark(int num, int y, int x, int dir) {
			this.num = num;
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
	static int N, M, k; // 격자 크기 N*N, 상어 숫자 M, 냄새강도 k
	static int[][] map; // 격자, 0=빈칸, 숫자=상어번호
	static int[][] kCnt; // k 얼마나 남았는지
	static Shark[] shark; // 상어들 (좌표 -1, -1 시 쫒겨난 상어)
	static int[] dy = {0, -1, 1, 0, 0}; // [0] 사용 x, 상하좌우
	static int[] dx = {0, 0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	M = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	map = new int[N][N];
    	kCnt = new int[N][N];
    	shark = new Shark[M+1]; // [0]번 상어 제외
    	for(int i=0; i<N; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j=0; j<N; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    			if(map[i][j]!=0) {
    				shark[map[i][j]] = new Shark(map[i][j], i, j, 0); // 방향 임시로 0 지정
    			}
    		}
    	}
    	st = new StringTokenizer(br.readLine()); // 상어 방향 지정
    	for(int i=1; i<=M; i++) {
    		shark[i].dir = Integer.parseInt(st.nextToken()); // 상:1 하:2 좌:3 우:4
    	}
    	for(int i=1; i<=M; i++) { // 상어 이동 우선순위 
    		shark[i].move = new int[5][4];
    		for(int j=1; j<=4; j++) {
    			st = new StringTokenizer(br.readLine());
    			shark[i].move[j][0] = Integer.parseInt(st.nextToken());
    			shark[i].move[j][1] = Integer.parseInt(st.nextToken());
    			shark[i].move[j][2] = Integer.parseInt(st.nextToken());
    			shark[i].move[j][3] = Integer.parseInt(st.nextToken());
    		}
    	}
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
    }
    
//	private static void printShark() {
//		for(int i=1; i<=M; i++) {
//			System.out.println("shark num : "+shark[i].num);
//			System.out.println("current direction : "+shark[i].dir);
//			System.out.println("when dir=1 "+Arrays.toString(shark[i].move[1]));
//			System.out.println("when dir=2 "+Arrays.toString(shark[i].move[2]));
//			System.out.println("when dir=3 "+Arrays.toString(shark[i].move[3]));
//			System.out.println("when dir=4 "+Arrays.toString(shark[i].move[4]));
//			System.out.println("========================");
//		}
//	}

	private static void solution() {
		for(int i=1; i<=M; i++) { // kCnt, visit 초기 상태 설정
			int x = shark[i].x;
			int y = shark[i].y;
			kCnt[y][x] = k;
		}
		
		int time;
		for(time=0; time<1000; time++) {
			if(checkShark()==1) break;
			
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println();
//			for(int i=0; i<N; i++) {
//				for(int j=0; j<N; j++) {
//					System.out.print(kCnt[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println("=============");		
			
			// 1. k 1씩 감소
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(kCnt[i][j]>0) kCnt[i][j]--;
				}
			}	

			// 2. 상어 다음 이동 계산
			for(int i=1; i<=M; i++) {
				if(shark[i].x==-1) continue;  // 쫒겨난 상어인 지 확인
				boolean hasBlank=false;
				int ny=0, nx=0, ndir=0;
				for(int d=0; d<4; d++) {
					ny = shark[i].y+dy[shark[i].move[shark[i].dir][d]]; // 상어 현재 방향에서 우선순위 따라 
					nx = shark[i].x+dx[shark[i].move[shark[i].dir][d]];
					ndir = shark[i].move[shark[i].dir][d];
					if(!isIn(nx, ny)) continue;// 범위 외 이면 continue;
					if(map[ny][nx]==0) {
						hasBlank = true;
						break;
					}
				}
				if(hasBlank) { // 빈 공간 있으면 그 위치 저장 (이동은 4에서 진행)
					shark[i].dir = ndir;
					shark[i].x = nx;
					shark[i].y = ny;
				} else { // 빈 공간 없으면 왔던 길 돌아가기
					for(int d=0; d<4; d++) {
						ny = shark[i].y+dy[shark[i].move[shark[i].dir][d]];
						nx = shark[i].x+dx[shark[i].move[shark[i].dir][d]];
						ndir = shark[i].move[shark[i].dir][d];
						if(!isIn(nx, ny)) continue;
						if(map[ny][nx]==shark[i].num) {
							break;
						}
					}
					shark[i].dir = ndir;
					shark[i].x += dx[ndir];
					shark[i].y += dy[ndir];
				}
			}
			// 3. 상어 이동
			for(int i=1; i<=M; i++) {
				if(shark[i].x!=-1) { // 격자 내 상어 일 때
					int nx = shark[i].x;
					int ny = shark[i].y;
					if(map[ny][nx]!=0 && map[ny][nx]<shark[i].num) {// 자기보다 쎈 놈 있으면 도망가기
						shark[i].x=-1;
					} else {
						map[ny][nx]=shark[i].num;
						kCnt[ny][nx]=k;
					}
				}
			}
			
			// 4. k=0 인 지점 지우기
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]!=0 && kCnt[i][j]==0) map[i][j]=0;
				}
			}
		}
		if(checkShark()!=1) sb.append(-1);
		else sb.append(time);
	}


	private static boolean isIn(int nx, int ny) {
	return 0<=nx && nx<N && 0<=ny && ny <N;
}

	private static int checkShark() { // x좌표 -1이면 쫒겨난 상어
		int cnt = 0;
		for(int i=1; i<=M; i++)
			if(shark[i].x!=-1) cnt++;
		return cnt;
	}

}
