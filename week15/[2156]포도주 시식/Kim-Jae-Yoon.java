import java.util.*;
import java.io.*;
/*
  Title: 포도주 시식
  Tier: Silver 1
  Algorithm: Dynamic Programming
  Constraint: 2 Second, 128MB
*/
public class Main {

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, s;
	static int[] a;
	static int[][] dp;

	static void solution() throws IOException {
		dp[0][1] = a[0];
        int answer = a[0];
		for(int i = 1;i < n;++i) {
			dp[i][0] = Math.max(dp[i - 1][2], dp[i - 1][1]);
			dp[i][1] = Math.max(dp[i - 1][0] + a[i], dp[i - 1][1]);
			dp[i][2] = Math.max(dp[i - 1][1] + a[i], dp[i - 1][2]);
			answer = Math.max(answer, dp[i][0]);
			answer = Math.max(answer, dp[i][1]);
			answer = Math.max(answer, dp[i][2]);
		}
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		a = new int[n];
		dp = new int[n][3];
		for(int i = 0;i < n; ++i) {
			a[i] = Integer.parseInt(br.readLine());
		}
		solution();
	}
}
