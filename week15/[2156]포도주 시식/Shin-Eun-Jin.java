import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class day1 {
	static int n;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		System.out.println(dp());
	}

	public static int dp() {

		int[] memo = new int[n + 1];

		for (int i = 1; i <= n; i++) {

			if (i == 1) {
				memo[i] = arr[i];
			} else if (i == 2) {
				memo[i] = memo[i - 1] + arr[i];
			} else {
				memo[i] = Math.max(memo[i - 1], Math.max(memo[i - 2] + arr[i], memo[i - 3] + arr[i] + arr[i - 1]));
			}

		}

		return memo[n];
	}
}
