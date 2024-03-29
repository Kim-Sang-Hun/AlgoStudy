package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1991 {
	
	static int N;
	static int[][] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder(); 
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int p = st.nextToken().charAt(0)-'A';
			arr[p][0] = st.nextToken().charAt(0)-'A';
			arr[p][1] = st.nextToken().charAt(0)-'A';
		}
		
		preorder(0);
		sb.append("\n");
		inorder(0);
		sb.append("\n");
		postorder(0);
		sb.append("\n");
		System.out.println(sb);
		
	}

	private static void postorder(int node) {
		if (node < 0) return;
		
		postorder(arr[node][0]);
		postorder(arr[node][1]);
		sb.append((char) (node+'A'));
		
	}

	private static void inorder(int node) {
		if (node < 0) return;
		
		inorder(arr[node][0]);
		sb.append((char) (node + 'A'));
		inorder(arr[node][1]);
		
	}

	private static void preorder(int node) {
		sb.append((char) (node+'A'));
		
		for (int child : arr[node]) {
			if(child < 0) continue;
			
			preorder(child);
		}
		
	}
}
