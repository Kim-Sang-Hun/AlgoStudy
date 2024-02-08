package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int M, N;
	static int[][] map;
	static boolean[][] isVisited;
	static Queue<int[]> queue;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken()); // 열
		N = Integer.parseInt(st.nextToken()); // 행
		map = new int[N][M];
		queue = new LinkedList<>();
		
		boolean flag = true;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					queue.add(new int[] {i,j,0});
				}
				else if(map[i][j] == 0) {
					flag = false;
				}
				
			}
		}
		
		// 모든 토마토가 익어 있는 상태일 경우
		if(flag) {
			System.out.println(0);
			return ;
		}
		
		int count = bfs();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}
		System.out.println(count);

	}
	static int bfs() {
		int maxCount = Integer.MIN_VALUE; 
				
		while(!queue.isEmpty()) {
			int[] curVertex = queue.poll();
			
			for(int i = 0; i < 4; i++) {
				int nextRow = curVertex[0] + dr[i];
				int nextCol = curVertex[1] + dc[i];
				int nextCount = curVertex[2] + 1;
				
				if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M || map[nextRow][nextCol] != 0) {
					continue;
				}
				
				queue.add(new int[] {nextRow, nextCol, nextCount});
				map[nextRow][nextCol] = 1;
				maxCount = Math.max(maxCount, nextCount);
			}
		}
		
		return maxCount;
	}
}
