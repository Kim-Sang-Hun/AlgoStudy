package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class BOJ16724 {
	
	static int N,M;
	static int[][] arr;
	static boolean[][] visited, cycle;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int ans;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] str = br.readLine().toCharArray(); 
			for (int j = 0; j < M; j++) {
				char s = str[j];
				if (s == 'U') {
					arr[i][j] = 0;
				} else if (s == 'D') {
					arr[i][j] = 1;
				} else if (s == 'L') {
					arr[i][j] = 2;
				} else {
					arr[i][j] = 3;
				}
			}
		}
		
		visited = new boolean[N][M];
		cycle = new boolean[N][M];
		ans = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					dfs(i,j);
				}
			}
		}
		System.out.println(ans);
	}

	private static void dfs(int x, int y) {
		
		visited[x][y] = true;
		int d = arr[x][y];
		int nx = x + dx[d];
		int ny = y + dy[d];
		
		if (!visited[nx][ny]) {
			dfs(nx,ny);
		} else if (!cycle[nx][ny]) {
			ans++;
		}
		
		cycle[x][y] = true;
	}
}
