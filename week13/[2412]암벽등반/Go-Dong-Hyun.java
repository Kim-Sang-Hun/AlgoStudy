package week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2412 {
	
	static class Home implements Comparable<Home>{
		int x;
		int y;
		
		public Home(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Home o) {
			if (this.y == o.y) 
				return this.x - o.x;
			return this.y - o.y;
		}

		@Override
		public String toString() {
			return "Home [x=" + x + ", y=" + y + "]";
		}
		
	}
	
	static int N,T;
	static Home[] arr;
	static int ans;
	static boolean[] visited;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new Home[N+1];
		arr[0] = new Home(0, 0);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[i+1] = new Home(a, b);
		}
		
		Arrays.sort(arr);
		visited = new boolean[N+1];

		flag = false;
		sol();
		if (!flag) 
			System.out.println(-1);
	}

	private static void sol() {
		
		// index랑 cnt값 
		Queue<int[]> q = new ArrayDeque<>();
		visited[0] = true;
		q.add(new int[] {0,0});
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			if (arr[cur[0]].y == T) {
				System.out.println(cur[1]);
				flag = true;
				return;
			}
			
			for (int i = 1; i <= N; i++) {
				if (visited[i]) continue;
				
				if (check(cur[0],i)) {
					visited[i] = true;
					q.add(new int[] {i,cur[1] + 1});
//					System.out.println(i);
				}
				
				// 아니 이거 안썼다고 런타임나냐 ㅅ벌
				if (arr[i].y - arr[cur[0]].y > 2) break;
			}
		}
	}

	private static boolean check(int cur, int idx) {
		
		if (Math.abs(arr[cur].x-arr[idx].x) <= 2 && Math.abs(arr[cur].y-arr[idx].y) <= 2)
			return true;
		
		return false;
	}
}
