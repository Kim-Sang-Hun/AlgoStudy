package week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2096 {
	
	static int N;
	static int[][] maxDp;
	static int[][] minDp;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		maxDp = new int[N][3];
		minDp = new int[N][3];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 3; i++) {
			int start = Integer.parseInt(st.nextToken());
			maxDp[0][i] = start;
			minDp[0][i] = start;
		}
		
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			maxDp[i][0] = a + Math.max(maxDp[i-1][0], maxDp[i-1][1]);
			maxDp[i][1] = b + Math.max(maxDp[i-1][0], Math.max(maxDp[i-1][1], maxDp[i-1][2]));
			maxDp[i][2] = c + Math.max(maxDp[i-1][1], maxDp[i-1][2]);
			
			minDp[i][0] = a + Math.min(minDp[i-1][0], minDp[i-1][1]);
			minDp[i][1] = b + Math.min(minDp[i-1][0], Math.min(minDp[i-1][1], minDp[i-1][2]));
			minDp[i][2] = c + Math.min(minDp[i-1][1], minDp[i-1][2]);
		}
		
		System.out.print(Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2])) + " ");
		System.out.print(Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2])));
		
	}
}
