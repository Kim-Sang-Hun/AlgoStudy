package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16946 {
	
	static int N,M;
	static char[][] arr;
	static int[][] group;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static HashMap<Integer, Integer> hash;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new char[N][M];
		group = new int[N][M];
		hash = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		int idx = 1;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '0' && group[i][j] == 0) {
					hash.put(idx, bfs(i,j,idx));
					idx++;
				}
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(count(i,j));
			}
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
		
	}

	
	private static int count(int x, int y) {
		if (arr[x][y] == '0') return 0;

		int cnt = 1;
		HashSet<Integer> set = new HashSet<Integer>();
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (!in_range(nx,ny)) continue;
			if (group[nx][ny] == 0) continue;
			
			if (arr[nx][ny] == '0') {
				set.add(group[nx][ny]);
			}
		}
		
		for (Integer ii : set) {
			cnt += hash.get(ii);
		}
		
		return cnt%10;
	}


	private static Integer bfs(int x, int y, int idx) {
		int cnt = 1;
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] {x,y});
		group[x][y] = idx;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if (!in_range(nx,ny)) continue;
				
				if (arr[nx][ny] == '0' && group[nx][ny] == 0) {
					q.add(new int[] {nx,ny});
					group[nx][ny] = idx;
					cnt++;
				}
			}
		}
		
		return cnt;
	}


	private static boolean in_range(int a, int b) {
		return 0 <= a && a < N && 0 <= b && b < M;
	}
}
