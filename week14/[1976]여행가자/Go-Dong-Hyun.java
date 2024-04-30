package Algo_week14;

import java.io.*;
import java.util.*;

public class BOJ1976 {
	
	static int N,M;
	static int[][] arr;
	static int[] citys,parents;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		citys = new int[M];
		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			citys[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					union(i,j);
				}
			}
		}
		
		int idx = find(citys[0]);
		for (int i = 1; i < M; i++) {
			if (find(citys[i]) != idx) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
		
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			parents[b] = a;
		}
	}

	private static int find(int x) {
		if (x == parents[x]) 
			return x;
		return parents[x] = find(parents[x]);
	}
}
