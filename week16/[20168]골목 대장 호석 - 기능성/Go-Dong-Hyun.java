package Algo_week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20168 {
	
	static class Node{
		int to;
		int price;
		int maxPrice;
		
		public Node(int to, int price, int maxPrice) {
			super();
			this.to = to;
			this.price = price;
			this.maxPrice = maxPrice;
		}
	}
	
	static int N,M,A,B,C;
	static ArrayList<Node>[] arr;
	static int ans;
	static int[] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 교차로 N, 골목 M, 시작 A, 도착 B, 가진돈 C
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken())-1;
		B = Integer.parseInt(st.nextToken())-1;
		C = Integer.parseInt(st.nextToken());
		
		arr = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new ArrayList<Node>();
		}
		
		ans = Integer.MAX_VALUE;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken())-1;
			int to = Integer.parseInt(st.nextToken())-1;
			int price = Integer.parseInt(st.nextToken());
			
			arr[from].add(new Node(to, price, 0));
			arr[to].add(new Node(from, price, 0));
		}
		
		visited = new int[N];
		Arrays.fill(visited, Integer.MAX_VALUE);
		
		Queue<Node> q = new ArrayDeque<>();
		
		q.add(new Node(A, 0, 0));
		visited[A] = 0;
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.to == B && visited[B] <= C) {
				ans = Math.min(ans, cur.maxPrice);
				continue;
			}
			
			for (Node node : arr[cur.to]) {
				if (visited[node.to] > visited[cur.to] + node.price) {
					visited[node.to] = visited[cur.to] + node.price;
					q.add(new Node(node.to, node.price, Math.max(node.price, cur.price)));
				}
			}
			
		}
		
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
		
	}
}
