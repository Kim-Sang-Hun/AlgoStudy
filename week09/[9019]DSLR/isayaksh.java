import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		int A, B;
		for(int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			
			sb.append(bfs(A, B)).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	
	
	public static String bfs(int A, int B) {
		
		Deque<Node> deque = new ArrayDeque<Node>();
		deque.add(new Node(A, ""));
		
		boolean[] visited = new boolean[10000];
		visited[A] = true;
		
		int d, s, l, r;
		
		while(!deque.isEmpty()) {
			Node node = deque.poll();
			
			if(node.value == B) return node.path;
			
			d = D(node.value);
			if(!visited[d]) {
				visited[d] = true;
				deque.add(new Node(d, node.path + "D"));
			}
			
			s = S(node.value);
			if(!visited[s]) {
				visited[s] = true;
				deque.add(new Node(s, node.path + "S"));
			}
			
			l = L(node.value);
			if(!visited[l]) {
				visited[l] = true;
				deque.add(new Node(l, node.path + "L"));
			}
			
			r = R(node.value);
			if(!visited[r]) {
				visited[r] = true;
				deque.add(new Node(r, node.path + "R"));
			}
			
			
		}
		
		return null;
	}
	
	public static int D(int A) {
		return (A * 2) % 10000;
	}
	
	public static int S(int A) {
		return (A + 9999) % 10000;
	}
	
	public static int L(int A) {
		return (A % 1000) * 10 + (A / 1000);
	}
	
	public static int R(int A) {
		return (A % 10) * 1000 + (A / 10);
	}
	
	static class Node {
		
		int value;
		String path;
		
		public Node(int value, String path) {
			this.value = value;
			this.path = path;
		}
		
	}
	
}
