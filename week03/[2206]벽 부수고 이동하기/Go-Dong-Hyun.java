package ssafyAlgo.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
	static int N,M,ans;
	static int[][] arr;
	static int[][][] visited;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		visited = new int[2][N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j)-'0';
			}
		}
		
		bfs();
		
		
		if (ans == 999999) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
		
	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] {0,0,0});
		visited[0][0][0] = 1;
		ans = 999999;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			int crash = cur[0];
			int x = cur[1];
			int y = cur[2];
			
			if (x == N-1 && y == M-1) {
				ans = Math.min(ans, visited[crash][x][y]);
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				int ncrash = crash;
				
				if (!in_range(nx,ny)) continue;
				
				if (visited[ncrash][nx][ny] != 0) continue;
				
				if (arr[nx][ny] == 1) {
					if (crash == 0) {
						ncrash = 1;
					} else {
						continue;
					}
				}
				
				visited[ncrash][nx][ny] = visited[crash][x][y] + 1;
				q.add(new int[] {ncrash,nx,ny});
			}
			
		}
	}

	private static boolean in_range(int nx, int ny) {
		return 0<=nx && nx < N && 0<=ny && ny<M;
	}
}
