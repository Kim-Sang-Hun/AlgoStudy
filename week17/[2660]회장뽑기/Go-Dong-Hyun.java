package Algo_week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2660 {
	
	static int N;
	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	static int[] ans;
	static Queue<int[]> q;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new ArrayList[N];
		ans = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			if (a == -2) break;
			
			arr[a].add(b);
			arr[b].add(a);
		}
		
		for (int i = 0; i < N; i++) {
			bfs(i);
		}
		
//		System.out.println(Arrays.toString(ans));
		int minAns = 51;
		for (int i = 0; i < N; i++) {
			minAns = Math.min(minAns, ans[i]);
		}
		
		int maxCnt = 0;
		for (int i = 0; i < N; i++) {
			if (ans[i] == minAns) {
				maxCnt++;
			}
		}
		System.out.println(minAns + " " + maxCnt);
		
		for (int i = 0; i < N; i++) {
			if (minAns == ans[i]) {
				System.out.print(i+1 +  " ");
			}
		}
		
	}

	private static void bfs(int idx) {
		q = new ArrayDeque<int[]>();
		q.add(new int[] {idx,1});
		visited = new boolean[N];
		visited[idx] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll(); //인덱스, 카운트
			for (Integer i : arr[cur[0]]) {
				if (visited[i]) continue;
				visited[i] = true;
				cnt = Math.max(cnt, cur[1]);
				q.add(new int[] {i,cur[1]+1});
			}
		}
		
		ans[idx] = cnt;
		
	}
}