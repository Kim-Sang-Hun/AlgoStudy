package Algo_week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16987 {
	static int N, ans;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());	//내구도
			arr[i][1] = Integer.parseInt(st.nextToken());	//무게
		}
		
		ans = 0;
		sol(0,0);
		System.out.println(ans);
	}

	private static void sol(int depth, int cnt) {
		if (depth == N) {
			ans = Math.max(cnt, ans);
			return;
		}
		
		if (arr[depth][0] <= 0 || cnt == N-1) {
			sol(depth+1, cnt);
			return;
		}
		
		int sub_cnt = cnt;
		
		for (int i = 0; i < N; i++) {
			if (depth == i) continue;
			
			if (arr[i][0] <= 0) continue;
			
			arr[i][0] -= arr[depth][1];
			arr[depth][0] -= arr[i][1];
			
			if (arr[depth][0] <= 0) cnt++;	//왼손
			
			if (arr[i][0] <= 0) cnt++;	//다른놈
			
			sol(depth+1, cnt);
			
			arr[i][0] += arr[depth][1];
			arr[depth][0] += arr[i][1];
			cnt = sub_cnt;
			
		}
		
	}
}
