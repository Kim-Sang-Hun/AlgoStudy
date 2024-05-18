package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN3584_가장가까운공통조상_김주희 {
	static int T, N, nodeA, nodeB, depthA, depthB;
	static boolean reachA, reachB;
	static List<Integer>[] children;
	
	static class Answer implements Comparable<Answer>{
		int depth, parent;
		
		public Answer(int depth, int parent) {
			this.depth = depth;
			this.parent = parent;
		}

		@Override
		public int compareTo(Answer o) {
			return this.depth - o.depth;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 0; tc < T; tc++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st;
			children = new ArrayList[N+1];
			for (int i = 1; i <= N; i++) {
				children[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < N-1; i++) {
				st = new StringTokenizer(br.readLine());
				children[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
			}
			
			st = new StringTokenizer(br.readLine());
			nodeA = Integer.parseInt(st.nextToken());
			nodeB = Integer.parseInt(st.nextToken());
			
			PriorityQueue<Answer> answers = new PriorityQueue<>();
			for (int i = 1; i <= N ; i++) {
				depthA = 0;
				depthB = 0;
				reachA = false;
				reachB = false;
				
				if(i == nodeA) reachA = true;
				if(i == nodeB) reachB = true;
				
				dfs(0, i);
				
				if(reachA && reachB) {
					answers.add(new Answer(depthA+depthB, i));
				}
		
			}
			
			System.out.println(answers.poll().parent);
			
			
		}
		
	}

	private static void dfs(int depth, int cur) {
		//System.out.println(cur);
		
		if(reachA && reachB) {
			return;
		}
		
		if(depth == N) return;
		
		for (int child : children[cur]) {

			if(!reachA && child == nodeA) {
				reachA = true;
				depthA = depth;
				dfs(depth + 1, child);
			}
			else if(!reachB && child == nodeB) {
				reachB = true;
				depthB = depth;
				dfs(depth + 1, child);
			}
			else {
				dfs(depth + 1, child);
			}
		}
		
	}

}
