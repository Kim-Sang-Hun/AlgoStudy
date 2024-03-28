package week08;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019 {
	
	static int A,B;
	static boolean[] visited;
	
	static class register {
		int cnt;
		String str;
		
		public register(int cnt, String str) {
			this.cnt = cnt;
			this.str = str;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			visited = new boolean[10000];
			visited[A] = true;
			
			Queue<register> q = new ArrayDeque<>();
			
			q.add(new register(A, ""));
			
			String ans = "";
			
			while (!q.isEmpty()) {
				register cur = q.poll();
				
				if (cur.cnt == B) {
					ans = cur.str;
					break;
				}
				
				int nextD = findD(cur.cnt);
				int nextS = findS(cur.cnt);
				int nextL = findL(cur.cnt);
				int nextR = findR(cur.cnt);
				
				if (!visited[nextD]) {
					visited[nextD] = true;
					q.add(new register(nextD, cur.str+"D"));
				}
				if (!visited[nextS]) {
					visited[nextS] = true;
					q.add(new register(nextS, cur.str+"S"));
				}
				if (!visited[nextL]) {
					visited[nextL] = true;
					q.add(new register(nextL, cur.str+"L"));
				}
				if (!visited[nextR]) {
					visited[nextR] = true;
					q.add(new register(nextR, cur.str+"R"));
				}
				
			}
			
			System.out.println(ans);
		}
		
	}

	private static int findR(int cnt) {
		int last = cnt%10;
		int sub_cnt = (last*1000) + (cnt/10);
		
		return sub_cnt;
	}

	private static int findL(int cnt) {
		int fir = cnt/1000;
		int sub_cnt = (cnt%1000)*10 + fir;
		
		return sub_cnt;
	}

	private static int findS(int cnt) {
		int sub_cnt = cnt-1;
		if (sub_cnt == -1)
			sub_cnt = 9999;
		
		return sub_cnt;
	}

	private static int findD(int cnt) {
		int sub_cnt = (cnt*2)%10000;
		
		return sub_cnt;
	}
}
