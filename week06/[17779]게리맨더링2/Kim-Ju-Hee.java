package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN17779 {
	static int N, map[][], sums[], x, y, d1, d2, total,answer, result[];
	
	private static void sum() {

		sums[4] = total;
		int temp = 0;
		for (int i = 1; i < x+d1; i++) {
			
			if(i >= x) temp++;
			for (int j = 1; j <= y-temp; j++) {
				
				sums[0] += map[i][j];
				sums[4] -= map[i][j];
			}
		}
		
		temp = 0;
		for (int i = 1; i <= x+d2; i++) {
			
			if(i > x) temp++;
			for (int j = y+1+temp; j <= N; j++) {
				sums[1] += map[i][j];
				sums[4] -= map[i][j];
			}
		}
		
		temp = 0;
		for (int i = N; i >= x+d1; i--) {
		
			if(i < x+d1+d2) temp++;
			for (int j = y-d1+d2-1-temp; j >= 1; j--) {
				sums[2] += map[i][j];
				sums[4] -= map[i][j];
			}
		}
		
		temp = 0;
		for (int i = x+d2+1; i <= N; i++) {
			
			if(i <= x+d1+d2+1) temp++;
			for (int j = y+d2 - temp + 1; j <= N; j++) {
				sums[3] += map[i][j];
				sums[4] -= map[i][j];
			}
		}
		
		Arrays.sort(sums);
		answer = Math.min(answer,sums[4]-sums[0]);
		
		// 초기화
		Arrays.fill(sums, 0);
	}
	
	private static void pick(int depth) {
		if(depth == 4) {
			if(result[2] + result[0] + result[1] <= N && 1 <= result[3] - result[0] && result[3] + result[1] <= N) {
				d1 = result[0];
				d2 = result[1];
				x = result[2];
				y = result[3];
				sum();
			}
			return;
		}
		
		for (int i = 1; i <= N; i++) {
			result[depth] = i;
			pick(depth+1);
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				total += map[i][j];
			}
		}
		
		sums = new int[5];
		result = new int[4];
		answer = Integer.MAX_VALUE;

		pick(0);
		
		System.out.println(answer);
	}

}
