package Algo_week05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17779 {
	
	static int N;
	static int[][] arr;
	static int[] area;
	static int ans = Integer.MAX_VALUE;
	static int total;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		total = 0;
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				total += arr[i][j];
			}
		}
		
		for (int x1 = 0; x1 < N; x1++) {
			for (int y1 = 1; y1 < N; y1++) {
				for (int d1 = 1; d1 <= y1; d1++) {
					for (int d2 = 1; d2 < N-y1; d2++) {
						
						int x4 = x1+d1+d2;
						int y4 = y1+d2-d1;
						
						if (x4 < 0 || x4 >= N || y4 < 0 || y4 >= N) continue;
						
						int x2 = x1+d1;
						int x3 = x1+d2;
						int adjust = 0;
						
						int max_cnt = 0;
						int min_cnt = Integer.MAX_VALUE;
						int sub_cnt = 0;
						int total_cnt = total;
						
						//1번쪽
						for (int i = 0; i < x2; i++) {
							if (i >= x1) adjust++;
							
							for (int j = 0; j <= y1 - adjust; j++) {
								sub_cnt += arr[i][j];
							}
						}
						
						max_cnt = Math.max(max_cnt, sub_cnt);
						min_cnt = Math.min(min_cnt, sub_cnt);
						total_cnt -= sub_cnt;
						
						adjust = 0;
						sub_cnt = 0;
						
						//2번
						for (int i = 0; i <= x3; i++) {
							if (i > x1) adjust++;
							
							for (int j = y1+1+adjust; j < N; j++) {
								sub_cnt += arr[i][j];
							}
						}
						
						max_cnt = Math.max(max_cnt, sub_cnt);
						min_cnt = Math.min(min_cnt, sub_cnt);
						total_cnt -= sub_cnt;
						
						adjust = 0;
						sub_cnt = 0;
						
						//3번
						for (int i = N-1; i >= x2; i--) {
							if (i < x4) adjust++;
							
							for (int j = 0; j < y4-adjust; j++) {
								sub_cnt += arr[i][j];
							}
						}
						
						max_cnt = Math.max(max_cnt, sub_cnt);
						min_cnt = Math.min(min_cnt, sub_cnt);
						total_cnt -= sub_cnt;
						
						adjust = 0;
						sub_cnt = 0;
						
						//4번
						for (int i = N-1; i > x3 ; i--) {
							if (i <= x4) adjust++;
							
							for (int j = y4+adjust; j < N; j++) {
								sub_cnt += arr[i][j];
							}
						}
						
						max_cnt = Math.max(max_cnt, sub_cnt);
						min_cnt = Math.min(min_cnt, sub_cnt);
						total_cnt -= sub_cnt;
						
						max_cnt = Math.max(max_cnt, total_cnt);
						min_cnt = Math.min(min_cnt, total_cnt);
						ans = Math.min(ans, max_cnt-min_cnt);
					}
				}
			}
		}
		
		System.out.println(ans);
		
		
	}
}
