package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16236 {
	
	static class shark{
		int x;
		int y;
		int size;
		int distance;
		
		public shark(int x, int y, int size, int distance) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.distance = distance;
		}
	}
	
	static int N, shark_size, shark_cnt, time, shark_x, shark_y;
	static int[][] arr;
	static Queue<shark> q;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		time = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					shark_x = i;
					shark_y = j;
				}
			}
		}
		
		shark_size = 2;
		shark_cnt = 0;
		
		while (true) {
			int move = bfs(shark_x, shark_y);
			if (move > 0) {
				time += move;
				shark_cnt++;
				
//				System.out.println(move + ", " + time + ", " + shark_size);
//				for (int i = 0; i < N; i++) {
//					System.out.println(Arrays.toString(arr[i]));
//				}
				
				if (shark_cnt == shark_size) {
					shark_cnt = 0;
					shark_size++;
				}
				
				continue;
			}
			
			break;
		}
		
		System.out.println(time);
		
	}

	private static int bfs(int x, int y) {
		
		q = new ArrayDeque<shark>();
		q.add(new shark(x, y, shark_size, 1));
		visited = new boolean[N][N];
		visited[x][y] = true;
		
		//x, y, 사이즈, 가는거리
		shark can_move_shark = new shark(0, 0, shark_size, Integer.MAX_VALUE);
		
		while (!q.isEmpty()) {
			shark cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];
				
				if (!in_range(nx,ny)) continue;
				if (visited[nx][ny]) continue;
				if (cur.size < arr[nx][ny]) continue;
				
				visited[nx][ny] = true;
				q.add(new shark(nx, ny, cur.size, cur.distance+1));
				
				// 물고기 먹을 수 있으면
				if (arr[nx][ny] != 0 && arr[nx][ny] < shark_size && cur.distance <= can_move_shark.distance) {
					if (cur.distance==can_move_shark.distance) {
						if (cur.x > can_move_shark.x) continue;
						else if (cur.x == can_move_shark.x && cur.y > can_move_shark.y) continue;
					}
					can_move_shark = new shark(nx, ny, shark_size, cur.distance);
				}
			}
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(visited[i]));
//		}
		
		if (can_move_shark.distance != Integer.MAX_VALUE) {
			arr[x][y] = 0;
			arr[can_move_shark.x][can_move_shark.y] = 9;
			shark_x = can_move_shark.x;
			shark_y = can_move_shark.y;
			
			return can_move_shark.distance;
		}
		
		return 0;
	}

	private static boolean in_range(int a, int b) {
		return 0 <= a && a < N && 0 <= b && b < N;
	}
}
