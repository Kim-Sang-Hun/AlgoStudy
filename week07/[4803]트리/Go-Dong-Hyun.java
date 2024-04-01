package Algo_week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ4803 {

	static int N, M, sub_ans;
	static int[] parent;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = 1;

		while (true) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			if (N == 0 && M == 0)
				break;
			
			parent = new int[N+1];
			
			for (int i = 0; i <= N; i++) {
				parent[i] = i;
			}
			
			sub_ans = 0;
			int[][] arr = new int[M][2];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				arr[i][0] = a;
				arr[i][1] = b;
			}
			
			Arrays.sort(arr, (o1,o2) -> {
				return o1[0] - o2[0];
			});
			
			for (int i = 0; i < M; i++) {
//				if (arr[i][0] == arr[i][1]) continue;
				
				union(arr[i][0],arr[i][1]);
			}
			
			HashSet<Integer> set = new HashSet<>();
			int ans = 0;
			int p = 0;
			set.add(p);
			
			for (int i = 1; i <= N; i++) {
				int root = find(i);
				if (root != 0 && !set.contains(root)) {
					ans += 1;
					set.add(root);
				}
			}
			
//			System.out.println(Arrays.toString(parent));
			
			System.out.print("Case " + T + ": " );
			if (ans <= 0) {
				System.out.print("No trees.");
			} else if (ans == 1) {
				System.out.print("There is one tree.");
			} else {
				System.out.print("A forest of " + ans + " trees.");
			}
			
			T++;
			System.out.println();
		}

	}

	private static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		
		if (aRoot == bRoot) {
//			parent[a] = -1;
//			parent[b] = -1;
			for (int i = 0; i <= N; i++) {
				if (find(parent[i]) == aRoot) {
					parent[i] = 0;
				}
			}
		}
		else if (aRoot > bRoot) parent[aRoot] = bRoot;
		else parent[bRoot] = aRoot;
		
	}

	private static int find(int x) {
		if (parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}

}