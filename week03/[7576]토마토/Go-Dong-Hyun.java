package Algo_week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN7576 {
	static int N,M;
	static int[][] arr;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0,-1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1) {
					q.add(new int[] {i,j});
				}
			}
		}
		
		while (!q.isEmpty()) {
			int[] now = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				
				if (in_range(nx,ny) && arr[nx][ny] == 0) {
					arr[nx][ny] = arr[now[0]][now[1]] + 1;
					q.add(new int[] {nx,ny});
				}
			}
		}
		
		int cnt = -1;
		boolean flag = true;
		
		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 0) {
					flag = false;
					break;
				} else {
					cnt = Math.max(cnt, arr[i][j]);
				}
			}
		}
		
		if (flag == false) {
			System.out.println(-1);
		} else {
			System.out.println(cnt-1);
		}
		
	}

	private static boolean in_range(int nx, int ny) {
		return 0<= nx && nx < N && 0<=ny && ny < M;
	}
}
