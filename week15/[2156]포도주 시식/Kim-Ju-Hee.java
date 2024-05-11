package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JUN2156_포도주시식_김주희 {
	
	private static int maxValue(int a, int b, int c) {
		int result = Math.max(a, b);
		result = Math.max(result, c);
		return result;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N+1];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		if(N == 1) {
			System.out.println(arr[1]);
			return;
		}else if(N == 2) {
			System.out.println(arr[1]+arr[2]);
			return;
		}
		
		int[] dp = new int[N+1];
		
		dp[1] = arr[1];
		dp[2] = arr[1] + arr[2];
		dp[3] = maxValue(arr[1]+arr[2], arr[1]+arr[3], arr[2]+arr[3]);
		
		for (int i = 4; i <= N; i++) {
			dp[i] = maxValue(arr[i]+dp[i-2], arr[i]+arr[i-1]+dp[i-3], dp[i-1]);
			// 바로 앞에 한 칸 띄우고 먹는 경우, 바로앞이랑 현재 먹고 그 앞에 띄우는 경우, 현재꺼 안 먹는 경우 중에 가장 큰 거
		}
		
		System.out.println(dp[N]);

	}

}
