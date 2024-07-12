package week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ10830 {
	
	static int N;
	static Long B;
	static int[][] arr;
	static int[][] dp;
	static int[] tmp;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		arr = new int[N][N];
		dp = new int[N][N];
		tmp = new int[N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		
		int[][] ans = sol(arr,B);
		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(ans[i][j] + " ");
			}
			System.out.println("");
		}
		
	}

	private static int[][] sol(int[][] list, Long b) {
		
		if (b == 1L) {
			return list;
		}
		
		int[][] ret = sol(list, b/2);
		
		ret = mul(ret, ret);
		
		if (b % 2 == 1L) {
			ret = mul(ret, arr);
		}
		
		return ret;
	}

	private static int[][] mul(int[][] arr1, int[][] arr2) {
		
		int[][] ret = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					
					ret[i][j] += arr1[i][k] * arr2[k][j];
					ret[i][j] %= 1000;
				}
			}
		}
		
		return ret;
	}
}
