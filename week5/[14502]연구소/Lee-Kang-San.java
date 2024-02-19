package BOJ;

import java.awt.Point;
import java.io.*;
import java.util.*;
/*
 * 제목
 * <연구소> G4
 * 링크
 * https://www.acmicpc.net/problem/14502
 * 요약
 * 최대한 바이러스를 덜 확산시키는 벽 3개 세우기
 * 풀이
 * 배열에서 값 0인 좌표 3개 선택 + bfs
 */
public class boj_14502 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M; // 지도 크기 N*M
	static int[][] map;
	static int maxSafe = -1;
	
	static int[] dy = {-1, 1, 0, 0}; // 상하좌우
	static int[] dx = {0, 0, -1, 1};
	
    public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		int[][] temp;
		int x1, y1, x2, y2, x3, y3;	// 3개의 벽 놓을 위치 좌표 (값 0인 위치 3개 선택)
		for(int i=0; i<N*M; i++) {	// nC3
			y1 = i/M; 
			x1 = i%M;
			if(map[y1][x1]!=0) continue;
			for(int j=i+1; j<N*M; j++) {
				y2 = j/M; 
				x2 = j%M;
				if(map[y2][x2]!=0)  continue;
				for(int k=j+1; k<N*M; k++) {
					y3 = k/M; 
					x3 = k%M;
					if(map[y3][x3]!=0) continue;
					temp = copyArr();
					temp[y1][x1] = 1;	// 3개의 벽 세우기
					temp[y2][x2] = 1;
					temp[y3][x3] = 1;
					spreadVirus(temp);  // 바이러스 확산 시키기
					maxSafe = Math.max(maxSafe, count(temp)); // 안전지대 칸 수 갱신		
				}
			}
		}
		sb.append(maxSafe+"");
	}
	
	// map 배열 복사 함수
	private static int[][] copyArr() {
		int[][] arr = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				arr[i][j] = map[i][j];
			}
		}
		return arr;
	}
	
	private static void spreadVirus(int[][] temp) {
		Queue<Point> q = new ArrayDeque<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(temp[i][j]==2)
					q.offer(new Point(j, i)); //x, y
			}
		}
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int d=0; d<4; d++) {
				int nx = p.x+dx[d];
				int ny = p.y+dy[d];
				if(0 <= nx && nx < M && 0 <= ny && ny < N) { // 바이러스 기준 4방향 탐색, 범위 내 일때  
					if(temp[ny][nx]==0) { // 0 값이라면(빈 칸) 
						temp[ny][nx] = 2; // 바이러스 확산(2) 후 다음 시작 위치로 설정(큐 삽입)
						q.offer(new Point(nx, ny));
					}
				}
			}
		}	
	}

	private static int count(int[][] temp) {
		int result = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(temp[i][j]==0)
					result++;
			}
		}
		return result;
	}
	
	
}
