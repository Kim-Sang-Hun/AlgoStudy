import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class BOJ10597_순열장난 {
	static String input;
	static int len;
	static boolean isClose;
	static int[] ans;
	static boolean[] isVisit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();

		len = input.length() <= 9 ? input.length() : 9 + (input.length() - 9) / 2;
		ans = new int[len];
		isVisit = new boolean[len + 1];
		isClose = false;
		bt(0, 0);
	}

	public static void bt(int idx, int cnt) {
		if (isClose) {
			return;
		}
		if (idx == input.length()) {
			for (int n : ans) {
				System.out.print(n + " ");
			}
			isClose = true;
			return;
		}

		for (int i = 1; i <= 2; i++) {
			if (idx + i > input.length())
				continue;

			int num = Integer.parseInt(input.substring(idx, idx + i));

			if (num <= len && !isVisit[num]) {
				isVisit[num] = true;
				ans[cnt] = num;

				bt(idx + i, cnt + 1);

				isVisit[num] = false;
				ans[cnt] = 0;
			}
		}

	}

}
