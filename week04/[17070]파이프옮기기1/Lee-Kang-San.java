package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <파이프 옮기기 1> G5
 * 링크
 * https://www.acmicpc.net/problem/17070
 * 요약
 * (1, 2) 에서 (N, N)으로 조건에 맞게 파이프 밀기
 * 풀이
 * dfs + 현 파이프 상태에 따른 이동 
 */
public class boj_17070 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 집의 크기 N * N
	static int[][] house;
	final static int GARO = 0, DAEGAK = 1, SERO = 2;

	static int dr[] = {0, 1, 1};	// 가로, 대각, 세로
	static int dc[] = {1, 1, 0};
	static int cnt = 0;
	
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		house = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				house[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 풀이
		dfs(1, 2, GARO);
		// 출력
		sb.append(cnt+"");
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dfs(int r, int c, int state) {
		if(r==N && c==N) {
			if(house[r][c]==0) cnt++;
			return;
		}
		else if(r>N || c>N) return;
		
		int nr, nc;
		if(state==GARO) {
			for(int i=0; i<=1; i++) { // 가로, 대각
				nr = r + dr[i];
				nc = c + dc[i];
				if(0 <= nr && nr <= N && 0 <= nc && nc <= N && house[nr][nc]!=1) { //범위 내면서 벽이 아닐 때
					if(i==1) {	// 대각일 때 추가 검사
						if(house[r][c+1]==1) continue;
						if(house[r+1][c]==1) continue;
					}
					dfs(nr, nc, i);
				}
			}
		} else if(state==DAEGAK) {
			for(int i=0; i<3; i++) { // 가로, 대각, 세로
				nr = r + dr[i];
				nc = c + dc[i];
				if(0 <= nr && nr <= N && 0 <= nc && nc <= N && house[nr][nc]!=1) { //범위 내면서 벽이 아닐 때
					if(i==1) {	// 대각일 때 추가 검사
						if(house[r][c+1]==1) continue;
						if(house[r+1][c]==1) continue;
					}
					dfs(nr, nc, i);
				}
				
			}
		} else { // state==SERO
			for(int i=1; i<=2; i++) { // 대각, 세로
				nr = r + dr[i];
				nc = c + dc[i];
				if(0 <= nr && nr <= N && 0 <= nc && nc <= N && house[nr][nc]!=1) { //범위 내면서 벽이 아닐 때
					if(i==1) {	// 대각일 때 추가 검사
						if(house[r][c+1]==1) continue;
						if(house[r+1][c]==1) continue;
					}
					dfs(nr, nc, i);
				}
			}
		}
	}

}
