package Algo_week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16234 {
	
	static int N,L,R;
	static int[][] arr;
	static boolean[][] visited;
	static int ans;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		//인구 차이가 L이상 R이하
		
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		
		while (true) {
			boolean flag = false;
			visited = new boolean[N][N];
			int sub_cnt = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j]) {
						visited[i][j] = true;
						flag = bfs(i,j);
						if (flag) sub_cnt ++;
					}
				}
			}
			
			if (sub_cnt == 0) break;
			ans += 1;
		}
		
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
		
		System.out.println(ans);
	}

	private static boolean bfs(int x, int y) {
		
		Queue<int[]> q = new ArrayDeque<int[]>();
		Queue<int[]> sub_q = new ArrayDeque<int[]>();
		q.add(new int[] {x,y});
		sub_q.add(new int[] {x,y});
		
		int people = arr[x][y];
		int num = 1;
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];
				
				if(in_range(nx,ny) && !visited[nx][ny] && in_dif(cur[0],cur[1],nx,ny)) {
					num++;
					people += arr[nx][ny];
					visited[nx][ny] = true;
					q.add(new int[] {nx,ny});
					sub_q.add(new int[] {nx,ny});
				}
			}
		}
		
		if (sub_q.size() == 1) return false;
		
		people = people/num;
		
		while (!sub_q.isEmpty()) {
			int[] cur = sub_q.poll();
			arr[cur[0]][cur[1]] = people;
		}
		
		return true;
	}

	private static boolean in_dif(int x, int y, int nx, int ny) {
		int dif = Math.abs(arr[x][y] - arr[nx][ny]);
		return L<= dif && dif <= R;
	}

	private static boolean in_range(int a, int b) {
		return 0 <= a && a < N && 0 <= b && b < N;
	}
}
