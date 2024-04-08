package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14675 {
	
	static int N,Q;
	static ArrayList<Integer>[] arr;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		arr = new ArrayList[N];
		
		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			arr[a].add(b);
			arr[b].add(a);
		}
		
		Q = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken())-1;
			
			if (t == 1) {
				sol1(k);
			} else {
				sol2(k);
			}
			
		}
	}

	private static void sol1(int idx) {
		if (arr[idx].size() == 1) {
			System.out.println("no");
			return;
		}
		System.out.println("yes");
	}
	
	private static void sol2(int idx) {
		System.out.println("yes");
	}

}
