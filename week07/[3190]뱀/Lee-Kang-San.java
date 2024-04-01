package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <뱀> G4
 * 링크 
 * https://www.acmicpc.net/problem/3190
 * 요약
 * [1][1] 시작, 자기자신이나 벽에 박으면 게임 종료, 시작 길이 1, 시작 방향 오른쪽
 * 머리 1칸 진행 - 몸이나 벽에 박으면 게임 종료 - 사과 있으면 꼬리 이동x(몸길이+1) - 사과 없으면 꼬리 이동o(몸길이 변화x) 
 * 풀이
 * 덱 사용
 * 자바의 mod 연산의 결과는 첫번째 피연산자의 부호를 따른다. (두 피연산자의 부호 상관없이 연산 후, 첫번쨰 피연산자의 부호를 붙여준다.)
 * 모듈러 합동이긴 한데, 음수에 대해 연산 시 내가 생각한 값과 다른 결과가 나올 수 있음.
 * -1 % 4 시 3이 아닌 -1
 * -10 % -4 시 2가 아닌 -2
 */
public class boj_3190 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Pos{
		int y, x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
	static class ChangeDir {
		int tic; // 초
		char c; // 좌, 우로 방향 전환
		public ChangeDir(int tic, char c) {
			this.tic = tic;
			this.c = c;
		}
	}

	static int N; // 보드 크기 N*N
	static int K; // 사과 개수 K
	static int[][] arr; // 보드 (좌상단 [1][1])
	static int L; // 뱀의 방향 전환 횟수 L
	
	static int dir = 0; // 뱀의 이동 방향
	static int[] dy = {0, 1, 0, -1}; // 우하좌상
	static int[] dx = {1, 0, -1, 0};
	static Deque<Pos> snake = new ArrayDeque<>(); 
	static Queue<ChangeDir> change = new ArrayDeque<>();
	
    public static void main(String[] args) throws IOException {
		// 입력
    	N=Integer.parseInt(br.readLine());
    	K=Integer.parseInt(br.readLine());
    	arr = new int[N+1][N+1];
    	for(int i=0; i<K; i++) {
    		st = new StringTokenizer(br.readLine());
    		int y = Integer.parseInt(st.nextToken());
    		int x = Integer.parseInt(st.nextToken());
    		arr[y][x]=2; // 2=사과
    	}
    	L = Integer.parseInt(br.readLine());
    	for(int i=0; i<L; i++) {
    		st = new StringTokenizer(br.readLine());
    		int tic = Integer.parseInt(st.nextToken());
    		char c = st.nextToken().charAt(0);
    		change.offer(new ChangeDir(tic, c));
    	}
		// 풀이
    	System.out.println((-1)%4);
    	 System.out.println((-10)%(-4));
    	 System.out.println(10%(-4));
        //	playDummy();
		// 출력;
		bw.write(sb.toString());
		bw.flush();
	}

	private static void playDummy() {
		int time = 0;
		arr[1][1] = 1;
		snake.offer(new Pos(1, 1)); // 시작 위치 초기화
		ChangeDir c = change.poll();
		while(true) {
			time++;
			Pos p = snake.peek();
			int ny = p.y+dy[dir];
			int nx = p.x+dx[dir];
			if(!isValidPos(ny, nx)) {
				break;
			}
			snake.addFirst(new Pos(ny, nx));
			if(arr[ny][nx]!=2) {
				Pos t = snake.pollLast();
				arr[t.y][t.x] = 0;
			}
			arr[ny][nx] = 1;
			if(time==c.tic) {
				if(c.c=='L') { // 좌회전
					dir = dir-1;
					if(dir==-1) dir = 3;
				}
				else { // 우회전 
					dir = (dir+1)%4;
				}
				if(!change.isEmpty()) {
					c = change.poll();
				}
			}
		}
		sb.append(time);
	}

	private static boolean isValidPos(int ny, int nx) {
		if(1<=ny && ny<=N && 1<=nx && nx<=N) { // 범위 내
			if(arr[ny][nx]==1) return false; // 자기 몸에 박은 경우
			else return true; // 빈 공간 또는 사과
		} else return false; // 범위 외
	}
}
