import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2293_동전1_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, k, coins[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		coins = new int[n + 1];
		for (int i = 1; i <= n; i++)
			coins[i] = Integer.parseInt(br.readLine());
		
		// dp[i]: 동전을 사용해서 i원을 만드는 경우의 수
		int dp[] = new int[k + 1];
		dp[0] = 1;
		
		// 첫 번째 for문은 coins 배열 순회
		for (int i = 1; i <= n; i++) {
			// 두 번째 for문은 dp 배열 순회
			for (int j = 1; j <= k; j++) {
				// 각각의 동전마다 j원 이하면 j원에서 해당 동전을 뺀 경우의 수를 추가
				if (j >= coins[i]) {
					dp[j] += dp[j - coins[i]];
				}
			}
		}
		
		System.out.print(dp[k]);
	}
}
