package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ10282 {
	
	static class Node implements Comparable<Node>{
		int to;
		int time;
		
		public Node(int to, int time) {
			super();
			this.to = to;
			this.time = time;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.time - o.time;
		}
	}
	
	static int N,D,C;
	static ArrayList<Node>[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken())-1;
			
			arr = new ArrayList[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = new ArrayList<Node>();
			}
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				
				int a = Integer.parseInt(st.nextToken())-1;
				int b = Integer.parseInt(st.nextToken())-1;
				int s = Integer.parseInt(st.nextToken());
				
				arr[b].add(new Node(a, s));
			}
			
			visited = new boolean[N];
			
			PriorityQueue<Node> q = new PriorityQueue<>();
			
			for (Node node : arr[C]) {
				q.add(node);
			}
			visited[C] = true;
			
			int cntPc = 1;
			int cntTime = 0;
			
			while (!q.isEmpty()) {
				Node curNode = q.poll();
				
				if (visited[curNode.to]) continue;
				
				visited[curNode.to] = true;
				cntPc++;
				cntTime = curNode.time;
				
				for (Node node : arr[curNode.to]) {
					if (visited[node.to]) continue;
					q.add(new Node(node.to, cntTime+node.time));
				}
			}
			
			System.out.println(cntPc + " " + cntTime);
			
		}
	}
}