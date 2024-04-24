package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16922 {
	
	static int N, ans;
	static boolean[] visited;
	static int[] num = {1,5,10,50};

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		visited = new boolean[1001];
		
		ans = 0;
		sol(0,0,0);
		
		System.out.println(ans);
		
	}

	private static void sol(int depth, int idx,int cnt) {
		
		if (depth == N) {
			if (!visited[cnt]) {
				visited[cnt] = true;
				ans++;
			}
			return;
		}
		
		for (int i = idx; i < 4; i++) {
			sol(depth+1,i, cnt+num[i]);
		}
		
	}
}
