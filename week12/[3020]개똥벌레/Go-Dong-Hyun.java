package week12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3020 {
	
	static int N,H;
	static int[] top, bottom;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		top = new int[H+2];
		bottom = new int[H+2];
		
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(br.readLine());
				
			if (i % 2 == 0) { //bottom
				bottom[h]++;
			} else { // top
				top[H-h+1]++;
			}
		}
		
		for (int i = 1; i <= H; i++) {
			bottom[i] += bottom[i-1];
		}
		
		for (int i = H; i >= 1 ; i--) {
			top[i] += top[i+1];
		}
		
		int min = N;
		int cnt = 0;
		
		for (int i = 1; i <= H; i++) {
			int dif = (bottom[H] - bottom[i-1]) + (top[1] - top[i+1]);
			
			if (dif<min) {
				min = dif;
				cnt = 1;
			} else if (dif == min) cnt++;
		}
		System.out.println(min + " " + cnt);
	}
}
