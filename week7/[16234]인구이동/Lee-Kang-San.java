package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <인구 이동> G4
 * 링크 
 * https://www.acmicpc.net/problem/16234
 * 요약
 * bfs
 * 풀이
 * bfs, 연합 크기가 1일 때는 탐색할 필요x
 */
public class boj_16234 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Pos {
		int y, x;

		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		} 
	}

	static int N, L, R; // 땅 한 변 길이 N, 인접한 두 구역의 인구 차이 L이상 R이하 시 국경이 열린다.
	static int[][] arr; // N*N 크기 땅
	static int[][] visited; // 매 인구이동 마다 1~cnt 값으로 연합 구분
	static int[] list = new int[2501]; // k번 연합의 크기 list[k]
	static int[] listAvg = new int[2501]; // k번 연합의 평균값 listAvg[k]
	static Pos[] listStartPos = new Pos[2501]; // k번 연합의 시작 위치 listStartPos[k]	
	static int unionSum, unionCnt; // 연합 인구 수, 연합 칸 수
	
	static int[] dy = {-1, 1, 0, 0}; // 상하좌우
	static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visited = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 풀이
		int day = 0;
		while(day<=2000) {
			for(int[] arr : visited) Arrays.fill(arr, 0);
			int cnt = simulation(); // 연합 개수, 각 연합의 크기, 각 연합 시작 위치 저장 		
			if(cnt==N*N) // 연합 개수가 N*N개이면 더 이상 인구 이동 x
				break;
			else day++;
			for(int i=1; i<=cnt; i++) { // 연합 개수 만큼 평균 구하고 값 분배하기
				if(list[i]!=1) { // 연합 크기 1이면 연산x
					setCurrentUnionAverage(i);
				}
			}
		}
		// 출력
		sb.append(day);
		bw.write(sb.toString());
		bw.flush();
	}
    
    private static void setCurrentUnionAverage(int k) {
    	Queue<Pos> q = new ArrayDeque<>();
    	boolean[][] tempVisit=new boolean[N][N];
    	int avg = listAvg[k]; // k번 연합의 평균
    	q.offer(listStartPos[k]); // k번 연합의 시작 위치 
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for(int d=0; d<4; d++) {
				int ny = p.y+dy[d];
				int nx = p.x+dx[d];
				if(!isIn(ny, nx)) continue;
				if(visited[ny][nx]==k && !tempVisit[ny][nx]) {
					arr[ny][nx]=avg;
					tempVisit[ny][nx] = true;
					q.offer(new Pos(ny, nx));
				}
			}
		}
	}

	private static int simulation() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited[i][j]>0) continue;
				cnt++;
				bfs(cnt, new Pos(i, j));
			}
		}
		return cnt;
	}
	
	//cnt번 연합, cnt번 연합 찾기 위한 bfs 시작 위치 pos
	private static void bfs(int cnt, Pos pos) { 
		int sum = 0;
		Queue<Pos> q = new ArrayDeque<>();
		sum += arr[pos.y][pos.x];
		visited[pos.y][pos.x] = cnt;
		q.offer(pos);
		int currentUnionSize = 1;
		while(!q.isEmpty()) {
			Pos p = q.poll();
			for(int d=0; d<4; d++) {
				int ny = p.y+dy[d];
				int nx = p.x+dx[d];
				if(!isIn(ny, nx)) continue;
				if(visited[ny][nx]>0) continue;
				int diff = Math.abs(arr[p.y][p.x]-arr[ny][nx]);
				if(L<=diff && diff<=R) {
					sum += arr[ny][nx];
					visited[ny][nx] = cnt;
					q.offer(new Pos(ny, nx));
					currentUnionSize++;
				}
			}
		}
		listStartPos[cnt] = pos;
		list[cnt] = currentUnionSize;
		listAvg[cnt]=sum/currentUnionSize;
	}

	private static boolean isIn(int ny, int nx) {
		return 0<=ny && ny<N && 0<=nx && nx<N;
	}
}
