package Algo_week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {
	static int N, ans;
	static int[][] arr;
	static int[] dx = {0,1,1};
	static int[] dy = {1,1,0};
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					visited[i][j] = true;
				}
			}
		}
		
		ans = 0;
		visited[0][0] = true;
		visited[0][1] = true;
		dfs(0,1,0);
		
		System.out.println(ans);
	}

	private static void dfs(int x, int y, int idx) {
		if (x == N-1 && y == N-1) {
			ans++;
			return;
		}
		
		if (idx == 0) {	//가로일때
			for (int i = 0; i < 2; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if (i == 0) {
					if (in_range(nx,ny) && !visited[nx][ny]) {
						visited[nx][ny] = true;
						dfs(nx,ny,i);
						visited[nx][ny] = false;
					}
				} else if (i == 1) {
					if (in_range(nx,ny) && in_range(nx-1,ny) && in_range(nx,ny-1) && 
							!visited[nx][ny] && !visited[nx-1][ny] && !visited[nx][ny-1]) {
						visited[nx][ny] = true;
						visited[nx-1][ny] = true;
						visited[nx][ny-1] = true;
						dfs(nx,ny,i);
						visited[nx][ny] = false;
						visited[nx-1][ny] = false;
						visited[nx][ny-1] = false;
					}
				}
			}
		} else if (idx == 2) {	// 세로일때
			for (int i = 1; i < 3; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if (i == 1) {
					if (in_range(nx,ny) && in_range(nx-1,ny) && in_range(nx,ny-1) && 
							!visited[nx][ny] && !visited[nx-1][ny] && !visited[nx][ny-1]) {
						visited[nx][ny] = true;
						visited[nx-1][ny] = true;
						visited[nx][ny-1] = true;
						dfs(nx,ny,i);
						visited[nx][ny] = false;
						visited[nx-1][ny] = false;
						visited[nx][ny-1] = false;
					}
				} else if (i == 2) {
					if (in_range(nx,ny) && !visited[nx][ny]) {
						visited[nx][ny] = true;
						dfs(nx,ny,i);
						visited[nx][ny] = false;
					}
				}
			}
		} else {	//대각선일때
			for (int i = 0; i < 3; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				
				if (i == 0) {
					if (in_range(nx,ny) && !visited[nx][ny]) {
						visited[nx][ny] = true;
						dfs(nx,ny,i);
						visited[nx][ny] = false;
					}
				} else if (i == 1) {
					if (in_range(nx,ny) && in_range(nx-1,ny) && in_range(nx,ny-1) && 
							!visited[nx][ny] && !visited[nx-1][ny] && !visited[nx][ny-1]) {
						visited[nx][ny] = true;
						visited[nx-1][ny] = true;
						visited[nx][ny-1] = true;
						dfs(nx,ny,i);
						visited[nx][ny] = false;
						visited[nx-1][ny] = false;
						visited[nx][ny-1] = false;
					}
				} else if (i == 2) {
					if (in_range(nx,ny) && !visited[nx][ny]) {
						visited[nx][ny] = true;
						dfs(nx,ny,i);
						visited[nx][ny] = false;
					}
				}
			}
		}
	}

	private static boolean in_range(int nx, int ny) {
		return 0<=nx && nx < N && 0<=ny && ny<N;
	}

}
