import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Shin-Eun-jin {
	static int n;
	static int[][] nums;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		nums = new int[n][n];
		memo = new int[n][n];

		int idx = 1;
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < input.length; j++) {
				nums[i][j] = Integer.parseInt(input[j]);
			}
		}
		dp();

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			max = Math.max(max, memo[n - 1][i]);
		}

		System.out.println(max);
	}

	static void dp() {
		memo[0][0] = nums[0][0];

		for (int i = 1; i < n; i++) {

			for (int j = 0; j < n; j++) {
				if (j == 0) {
					memo[i][j] = memo[i - 1][j] + nums[i][j];
				} else if (j == i) {
					memo[i][j] = memo[i - 1][j - 1] + nums[i][j];
					break;
				} else {
					memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - 1]) + nums[i][j];
				}
			}
		}
	}
}
