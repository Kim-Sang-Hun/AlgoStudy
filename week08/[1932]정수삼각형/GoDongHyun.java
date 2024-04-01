package week08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1932 {
	
	static int N;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][];
		
		for (int i = 0; i < N; i++) {
			arr[i] = new int[i+1];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
			
		}
		
		if (N == 1) {
			System.out.println(arr[0][0]);
			return;
		}
		
		arr[1][0] += arr[0][0];
		arr[1][1] += arr[0][0];
		if (N == 2) {
			System.out.println(Math.max(arr[1][0], arr[1][1]));
			return;
		}
		
		for (int i = 2; i < N; i++) {
			for (int j = 1; j < i; j++) {
				arr[i][j] += Math.max(arr[i-1][j-1], arr[i-1][j]);
			}
			arr[i][0] += arr[i-1][0];
			arr[i][i] += arr[i-1][i-1];
		}
		
		Arrays.sort(arr[N-1]);
		System.out.println(arr[N-1][N-1]);
		
	}
}
