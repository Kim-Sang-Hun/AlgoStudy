package BOJ;

import java.io.*;
import java.util.*;

public class boj_14500 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;	
	static StringBuilder sb = new StringBuilder();
	
	static int[][] arr;
	static int N, M;
	static int max = 0;
	
	static int[] di = {-1, 0, 1, 0};	// 상, 우, 하, 좌
	static int[] dj = {0, 1, 0, -1};
	static boolean[][] isVisited;
	
	public static void main(String[] args) throws IOException {
		//입력		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		isVisited = new boolean[N][M];
		for(int i=0; i<N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		//풀이
		solution();		// [i][j] 위치를 시작으로 생성 가능한 모든 4칸 블록 재귀 탐색
		checkTblock();	// T블록 생성 못해서 따로 체크
		//출력
		bw.write(max+"");
		bw.flush();
	}
	
	private static void solution() {
		for(int i=0; i<N; ++i) {
			for(int j=0; j<M; ++j) {
				isVisited[i][j] = true;
				tetromino(i, j, arr[i][j], 1);
				isVisited[i][j] = false;
			}
		}
	}
	private static void tetromino(int i, int j, int sum, int depth) {		// 시작점, depth
		if(depth==4) {
			max = Math.max(sum, max);	
			return;
		}
		for(int k=0; k<4; k++) {	
			if(i+di[k] < 0 || i+di[k] >= N || j+dj[k] < 0 || j+dj[k] >= M || isVisited[i+di[k]][j+dj[k]]) continue;
			isVisited[i+di[k]][j+dj[k]] = true;
			tetromino(i+di[k], j+dj[k], sum+arr[i+di[k]][j+dj[k]], depth+1); 
			isVisited[i+di[k]][j+dj[k]] = false;
		}
	}
	
	private static void checkTblock() {
		// horizontal
		for(int i=0; i<N; i++) {
			for(int j=0; j<M-2; j++) {	// 제일 오른쪽칸 기준
				int temp = arr[i][j] + arr[i][j+1] + arr[i][j+2];
				if(i-1>=0)	max = Math.max(temp+arr[i-1][j+1], max);		// ㅗ shape
				if(i+1<N)	max = Math.max(temp+arr[i+1][j+1], max);		// ㅜ shape
			}
		}
		
		// vertical
		for(int i=0; i<N-2; i++) {
			for(int j=0; j<M; j++) {	// 제일 위쪽칸 기준
				int temp = arr[i][j] + arr[i+1][j] + arr[i+2][j];
				if(j-1>=0)	max = Math.max(temp+arr[i+1][j-1], max);		// ㅓ shape
				if(j+1<M)	max = Math.max(temp+arr[i+1][j+1], max);		// ㅏ shape
			}
		}
	}
}
