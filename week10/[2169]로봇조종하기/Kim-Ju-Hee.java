import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, map[][];
	static int[] dr = new int[] {0,0,1};
	static int[] dc = new int[] {-1,1,0};
	static int[][] dp; 
	static boolean[][][] visited; 
	
	static class Robot{
		int r, c, value;

		public Robot(int r, int c, int value) {
			this.r = r;
			this.c = c;
			this.value = value;
		}
		
		
	}
	
	private static void dfs() {
		Queue<Robot> q = new LinkedList<>();
		
		q.add(new Robot(0,0,map[0][0]));
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		visited[0][0][2] = true;
		dp[0][0] = map[0][0];
		
		while(!q.isEmpty()) {
			Robot cur = q.poll();
			
			for (int i = 0; i < 3; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				for (int j = 0; j < 3; j++) {
					if(visited[nr][nc][j]) continue;
					
					visited[nr][nc][j] = true;
					if(dp[nr][nc] < cur.value + map[nr][nc])
						{
						dp[nr][nc] = cur.value + map[nr][nc];
						}
					q.add(new Robot(nr, nc, dp[nr][nc]));
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp = new int[N][M];

		visited = new boolean[N][M][3];
		
		dfs();
		
		System.out.println(dp[N-1][M-1]);

	}

}
