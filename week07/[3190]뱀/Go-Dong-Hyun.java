package Algo_week06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ3190 {
	
	static int N,K,L,ans;
	static int[][] arr, dir;
	static int[] dx = {0,-1,0,1};
	static int[] dy = {1,0,-1,0};
	static Queue<int[]> q;
	static ArrayDeque<int[]> dq;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			arr[a][b] = -1; 	//사과 -1로
		}
		
		L = Integer.parseInt(br.readLine());
		q = new ArrayDeque<int[]>();
		
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			String s = st.nextToken();
			if (s.equals("L")) {
				q.add(new int[] {a,1});
			} else {
				q.add(new int[] {a,-1});
			}
		}

		dq = new ArrayDeque<int[]>();
		dq.add(new int[] {0,0});
		
		//방향전환 시간 및 전환방향
		ans = 0;
		int d = 0;
		arr[0][0] = 1;	//1일때 뱀있음
		
		while (true) {
			int[] cur = dq.peekLast();
			int nx = cur[0] + dx[d];
			int ny = cur[1] + dy[d];
			
			if (!in_range(nx,ny) || arr[nx][ny] == 1) {	//벽이나 지몸 부딪힐때
				ans += 1;
				break;
			}
			
			if (arr[nx][ny] == 0) {
				arr[nx][ny] = 1;
				dq.add(new int[] {nx,ny});
				int[] tail = dq.poll();
				arr[tail[0]][tail[1]] = 0;
			}
			else if (arr[nx][ny] == -1) {	//사과먹을때
				arr[nx][ny] = 1;
				dq.add(new int[] {nx,ny});
			}
			
			ans += 1;
			if (!q.isEmpty() && ans==q.peek()[0]) {
				int[] nextDir = q.poll();
				d = (d + nextDir[1] + 4) % 4;
			}
			
		}
		
		System.out.println(ans);
	}

	private static boolean in_range(int a, int b) {
		return 0 <= a && a < N && 0 <= b && b < N;
	}
}
