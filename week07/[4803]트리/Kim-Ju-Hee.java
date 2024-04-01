package week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class JUN4803_트리_김주희 {
	static int n,m, parent[];
	static Vertex[] vertexes;
	static Set<Integer> roots = new HashSet<>();
	
	static class Vertex{
		int a,b;

		public Vertex(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	
	private static void makeSet() {
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}
	
	private static int find(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = find(parent[a]);
	}
	
	private static void union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if(rootA > rootB) {
			int temp = rootA;
			rootA = rootB;
			rootB = temp;
		}
		
		if(rootA == rootB) { // 사이클이 생기면 0으로 바꿈
			parent[rootA] = 0;
		}else {
			parent[rootB] = rootA;
		}
	}
	
	private static int check() {
		for (int i = 1; i <= n; i++) {
			roots.add(find(i));
		}
		
		int cnt = 0;
		for (int root : roots) {
			if(root != 0) cnt++; 
		}
		
		return cnt;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc = 0;
		while(true) {
			// 입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			if(n==0 && m==0) break;
			tc++;

			vertexes = new Vertex[m];
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				vertexes[i] = new Vertex(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// union-find
			parent = new int[n+1];
			makeSet();
			
			for (Vertex v : vertexes) {
				union(v.a, v.b);
			}
			
			int answer = check();
			roots.clear();
			
			// 출력
			System.out.printf("Case %d: ",tc);
			if(answer==0) System.out.println("No trees.");
			else if(answer==1) System.out.println("There is one tree.");
			else System.out.printf("A forest of %d trees.\n",answer);

		}
		return;

	}

}
