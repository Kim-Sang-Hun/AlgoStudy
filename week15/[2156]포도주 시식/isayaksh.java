import java.io.*;
import java.util.*;

public class isayaksh {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		int N = Integer.parseInt(br.readLine());
		
		int[] wine = new int[N];
		
		
		for(int n = 0; n < N; n++) wine[n] = Integer.parseInt(br.readLine());
		
		System.out.println(func(N, wine));
		
	}
	
	private static int func(int N, int[] wine) {
		
		if(N == 1) return wine[0];
		if(N == 2) return wine[0] + wine[1];
		
		int[] dp = new int[N];
		
		dp[0] = wine[0];
		dp[1] = wine[0] + wine[1];
		dp[2] = max(wine[0] + wine[1], wine[0] + wine[2], wine[1] + wine[2]);
		
		for(int n = 3; n < N; n++) dp[n] = max(wine[n] + dp[n-2], wine[n] + wine[n-1] + dp[n-3], dp[n-1]);
		
		return dp[N-1];
	}
	
	private static int max(int a, int b, int c) {
		int maxValue = 0;
		if(maxValue < a) maxValue = a;
		if(maxValue < b) maxValue = b;
		if(maxValue < c) maxValue = c;
		return maxValue;
	}
	
}
