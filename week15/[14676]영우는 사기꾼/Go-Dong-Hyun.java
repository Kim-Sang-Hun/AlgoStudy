package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ14676 {
	
	static int N,M,K;
	static ArrayList<Integer>[] arr;
	static int[] visited,degree;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N];
		visited = new int[N];
		degree = new int[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			arr[from].add(to);
			degree[to]++;
		}
		
		flag = true;
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken())-1;
			
			if (!flag) continue;
			
			if (order == 1) {
				build(b);
			} else if (order == 2) {
				destroy(b);
			}
//			System.out.println(flag + "," + b);
		}
		
		if (flag)
			System.out.println("King-God-Emperor");
		else
			System.out.println("Lier!");
		
	}

	private static void destroy(int idx) {
		
		if (visited[idx] < 1) {
			flag = false;
			return;
		}
		visited[idx]--;
		
		if (visited[idx] == 0) {
			for (Integer i : arr[idx]) {
				degree[i]++;
			}
		}
		
	}

	private static void build(int idx) {
		if (degree[idx] > 0) {
			flag = false;
			return;
		}
		visited[idx]++;
		
		if (visited[idx] == 1) {
			for (Integer i : arr[idx]) {
				degree[i]--;
			}
		}
		
	}
}
