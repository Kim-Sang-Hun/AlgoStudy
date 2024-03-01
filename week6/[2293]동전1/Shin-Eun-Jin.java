import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int n, k;
	static int[] coins;
	static int[] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 동전의 종류
		k = Integer.parseInt(st.nextToken()); // 가치
		coins = new int[n];
		memo = new int[k + 1];

		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}

		dp();
		System.out.println(memo[k]);

	}

	static void dp() {
		memo[0] = 1;
		for (int i = 0; i < n; i++) {

			for (int j = coins[i]; j <= k; j++) {
				memo[j] += memo[j - coins[i]];
			}

		}
	}
}
