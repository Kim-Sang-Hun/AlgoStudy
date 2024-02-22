package Algo_week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ1941 {
	static char[][] arr;
	static int[][] prin;
	static int ans;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static Queue<int[]> q;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		arr = new char[5][5];
		prin = new int[7][2];
		ans = 0;
		
		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			arr[i] = str.toCharArray();
		}
		
		combi(0,0);
		
		System.out.println(ans);
	}

	private static void combi(int start, int depth) {
		if (depth == 7) {
			bfs();
			return;
		}
		
		for (int i = start; i < 25; i++) {
			prin[depth][0] = i/5;
			prin[depth][1] = i%5;
			combi(i+1,depth+1);
		}
	}

	private static void bfs() {
		q = new ArrayDeque<int[]>();
		visited = new boolean[25];
		int cnt = 1;
		int s = 0;
		q.add(new int[] {prin[0][0], prin[0][1]});
		visited[prin[0][0] * 5 + prin[0][1]] = true;
		if (arr[prin[0][0]][prin[0][1]] == 'S') {
			s+=1;
		}
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				int idx = nx*5 + ny;
				if (in_range(nx,ny) && !visited[idx] && isprin(nx,ny)) {
					q.add(new int[] {nx,ny});
					visited[idx] = true;
					cnt += 1;
					if (arr[nx][ny] == 'S')
						s+=1;
				}
			}
		}
		
		if (cnt == 7 && s >= 4) {
			ans+=1;
		}
	}


	private static boolean isprin(int a, int b) {
		
		for (int[] p : prin) {
			if (a == p[0] && b ==p[1])  return true;
		}
		
		return false;
	}

	private static boolean in_range(int a, int b) {
		return 0<=a && a<5 && 0<=b && b<5;
	}
}
