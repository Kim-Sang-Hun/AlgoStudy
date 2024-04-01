import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DP로 해결한다. 동전이 추가됐을 때 어떤 금액을 만들 수 있는 경우의 수는 기존 경우의 수에서 (어떤 금액 - 동전의 가치)를 만들 수 있는 경우의 수를 더해준 값이다.
 * 예) 1, 2, 5일 때 1, 2로 6을 만들 수 있는 경우의 수는 111111, 11112, 1122, 222 4가지이다.
 * 이 때 5가 추가되면 f(1)의 경우의 수를 더해줘야 한다. (15의 경우)
 */
public class JUN2293_동전1_김상훈 {

	static int N, K;
	static int[] values, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		values = new int[N];
		dp = new int[K + 1];
		dp[0] = 1;
		for (int i = 0; i < N; i++) {
			values[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < N; i++) {
			int coin = values[i];
			for (int j = coin; j <= K; j++) {
				dp[j] += dp[j - coin];
			}
		}

		System.out.println(dp[K]);
	}
}
