package week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//여러분은 저처럼 코드 드럽게 짜지 마세요 
//드럽게짜면 M==1일때 예외처리해줘야합니다
public class BOJ2169 {
	
	static int N,M;
	static int[][] arr;
	static int[][][] dp;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		dp = new int[N][M][3];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dp[0][0][0] = arr[0][0];
		
		if (M == 1) {
			
			for (int i = 1; i < N; i++) {
				dp[i][0][0] += arr[i][0] + dp[i-1][0][0];
			}
			System.out.println(dp[N-1][0][0]);
			return;
		}
		
		for (int i = 1; i < M; i++) {
			dp[0][i][0] = arr[0][i] + dp[0][i-1][0];
		}
		
		
		int j;
		
		for (int i = 1; i < N; i++) {
			j = M-1;
			dp[i][j][1] = arr[i][j] + dp[i-1][j][0];
			while (true) { //오른쪽에서 왼쪽으로 -> 1
				j--;
				dp[i][j][1] = arr[i][j] + Math.max(dp[i-1][j][0], dp[i][j+1][1]);
				
				if (j == 0) break;
			}
				
			dp[i][j][2] = arr[i][j] + dp[i-1][j][0];
			while (true) { //왼쪽에서 오른쪽으로 -> 2
				j++;
				dp[i][j][2] = arr[i][j] + Math.max(dp[i-1][j][0], dp[i][j-1][2]);
				
				if (j == M-1) break;
			}
			
			for (int k = 0; k < M; k++) {
				dp[i][k][0] = Math.max(dp[i][k][1], dp[i][k][2]);
			}
		}
		
		
		System.out.println(dp[N-1][M-1][0]);
	}
}
