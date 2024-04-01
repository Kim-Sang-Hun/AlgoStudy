package algo_group_study;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <동전 1> G5
 * 링크
 * https://www.acmicpc.net/problem/2293
 * 요약
 * n가지 종류 동전으로 k원 만들기
 * 풀이
 * dp
 */
public class boj_2293 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, k;
	static int[] coin;
	static int[] d;
	
    public static void main(String[] args) throws IOException {
		// 입력
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	d = new int[k+1];
    	coin = new int[n];
    	for(int i=0; i<n; i++) 
    		coin[i] = Integer.parseInt(br.readLine());
		// 풀이
		dp();
		// 출력
		sb.append(d[k]);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dp() {
		Arrays.sort(coin);
		d[0]=1; 
				
		for(int i=0; i<n; i++) { // n가지 동전
			for(int j=coin[i]; j<=k; j++) {
				d[j] += d[j-coin[i]];
			}
		}
	}
}
