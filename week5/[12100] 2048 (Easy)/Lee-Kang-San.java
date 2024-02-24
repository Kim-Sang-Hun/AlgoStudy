package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <2048 (Easy)> G2
 * 링크
 * https://www.acmicpc.net/problem/12100
 * 요약
 * 2048!
 * 풀이
 * dfs
 */
public class boj_12100_ {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder(); 
	
	static int N; // map 크기 N*N
	static int[][] map;
	static int maxVal = Integer.MIN_VALUE;
	static int[] dy = {-1, 1, 0, 0}; //상,하,좌,우
	static int[] dx = {0, 0, -1, 1};	
	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 풀이
		dfs(0, map);
		
		// 출력
		sb.append(maxVal);
		bw.write(sb.toString());
		bw.flush();
	}

	static void dfs(int depth, int[][] map) {	// 상, 하, 좌, 우 이동 5회 
		if(depth==5) {		// 5번 이동 후 최댓값 업데이트
			mapCheck(map);	
			return;
		}
        // 상
		int[][] temp = new int[N][N]; 
		boolean[][] isMerged = new boolean[N][N]; // 같은값 만나서 합쳐졌으면 표시
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int x = j;
				int y = i;
				boolean check = false;
				while(y>0) { // 방향
					x += dx[0]; // 한칸씩 옮기면서 만나는 값 확인
					y += dy[0];
					if(temp[y][x]==0)	// 0 만나면 계속 진행
						continue;
					if(temp[y][x] == map[i][j] && !isMerged[y][x]) {	// 같은 값 만남
						temp[y][x] = map[i][j] * 2;
						check = true;
						isMerged[y][x] = true;
						break;
					}else {	// 다른 값 만남
						x -= dx[0];
						y -= dy[0];
						break;
					}
				}
				if(!check)
					temp[y][x] = map[i][j];
			}
		}
		dfs(depth+1, temp);
        // 하
		temp = new int[N][N];
		isMerged = new boolean[N][N];
		for(int i=N-1;i>=0;i--) {
			for(int j=0;j<N;j++) {
				int x = j;
				int y = i;
				boolean check = false;
				while(y<N-1) {
					x += dx[1];
					y += dy[1];
					if(temp[y][x]==0)	
						continue;
					if(temp[y][x] == map[i][j] && !isMerged[y][x]) {
						temp[y][x] = map[i][j] * 2;
						check = true;
						isMerged[y][x] = true;
						break;
					}else {
						x -= dx[1];
						y -= dy[1];
						break;
					}
				}
				if(!check)
					temp[y][x] = map[i][j];
			}
		}
		dfs(depth+1, temp);
        // 좌
		temp = new int[N][N];
		isMerged = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				int x = j;
				int y = i;
				boolean check = false;
				while(x>0) {
					x += dx[2];
					y += dy[2];
					if(temp[y][x] == 0)
						continue;
					if(temp[y][x] == map[i][j] && !isMerged[y][x]) {
						temp[y][x] = map[i][j] * 2;
						isMerged[y][x] = true;
						check = true;
						break;
					}else {
						x -= dx[2];
						y -= dy[2];
						break;
					}
				}
				if(!check)
					temp[y][x] = map[i][j];
			}
		}
		dfs(depth+1, temp);
        // 우
		temp = new int[N][N];
		isMerged = new boolean[N][N];
		for(int i=0;i<N;i++) {
			for(int j=N-1;j>=0;j--) {
				int x = j;
				int y = i;
				boolean check = false;
				while(x<N-1) {
					x += dx[3];
					y += dy[3];
					if(temp[y][x] == 0)
						continue;
					if(temp[y][x] == map[i][j] && !isMerged[y][x]) {
						temp[y][x] = map[i][j] * 2;
						isMerged[y][x] = true;
						check = true;
						break;
					}else {
						x -= dx[3];
						y -= dy[3];
						break;
					}

				}
				if(!check)
					temp[y][x] = map[i][j];
			}
		}
		dfs(depth+1, temp);
	}
	
    //현재 맵에서 최대값 구하기
	static void mapCheck(int[][] map) {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				maxVal = Math.max(maxVal, map[i][j]);
			}
		}
	}
}
