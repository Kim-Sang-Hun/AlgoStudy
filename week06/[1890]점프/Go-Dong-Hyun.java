package Algo_week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1890 {
	
	static int N, ans;
	static int[][] arr;
	static long[][] visited;
	static int[] dx = {1,0};
	static int[] dy = {0,1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		visited = new long[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited[0][0] = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				
				if (arr[i][j] == 0) break;
				
				for (int k = 0; k < 2; k++) {
					int nx = i + dx[k] * arr[i][j];
					int ny = j + dy[k] * arr[i][j];
					if (in_range(nx,ny)) {
						visited[nx][ny] += visited[i][j];
					}
				}
				
			}
		}
		
		System.out.println(visited[N-1][N-1]);
	}

	private static boolean in_range(int a, int b) {
		return a < N && b < N;
	}

}
