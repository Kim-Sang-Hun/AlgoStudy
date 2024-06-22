package Algo_week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14391 {
	
	static int N,M;
	static int[][] arr;
	static boolean[][] visited;
	static int ans;
	static int[] dx = {1,0};
	static int[] dy = {0,1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j)-'0';
			}
		}
		
		ans = 0;
		dfs(0,0,0);
		System.out.println(ans);
		
	}

	private static void dfs(int depth, int num, int cnt) {
		
		if (depth == N*M) {
			ans = Math.max(cnt, ans);
			return;
		}
		
		int r = depth / M;
		int c = depth % M;
		
		if (visited[r][c]) {
			dfs(depth+1, num, cnt);
		} else {
			visited[r][c] = true;
			num = num*10 + arr[r][c];
			dfs(depth+1, 0, num+cnt);
			
			int i, tmp = num;
			for (i = r+1; i < N; i++) {
				if (visited[i][c]) break;
				
				visited[i][c] = true;
				tmp = tmp * 10 + arr[i][c];
				dfs(depth+1, 0, cnt+tmp);
			}
			
			for (int j = r+1; j < i; j++) {
				visited[j][c] = false;
			}
			
			tmp = num;
			for (i = c+1; i < M; i++) {
				if (visited[r][i]) break;
				
				visited[r][i] = true;
				tmp = tmp * 10 + arr[r][i];
				dfs(depth+i-c+1, 0, cnt+tmp);
			}
			
			for (int j = c+1; j < i; j++) {
				visited[r][j] = false;
			}
			
			visited[r][c] = false;
			
		}
		
	}
}
