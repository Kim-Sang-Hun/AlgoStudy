package ssafyAlgo.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SW7465 {
	static int N, M, cnt;
	static ArrayList<Integer>[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			cnt = 0;
			
			arr = new ArrayList[N];
			visited = new boolean[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;
				
				arr[a].add(b);
				arr[b].add(a);
			}
			
			for (int i = 0; i < N; i++) {
				if (visited[i]) continue;
				cnt += 1;
				dfs(i);
			}
			
			System.out.println("#" + t + " " + cnt);
			
		}
	}

	private static void dfs(int idx) {
		visited[idx] = true;
		if (arr[idx].size() != 0) {
			for (int i = 0; i < arr[idx].size(); i++) {
				int f_idx = arr[idx].get(i);
				if (!visited[f_idx]) {
					dfs(f_idx);
				}
			}
		}
	}
}
