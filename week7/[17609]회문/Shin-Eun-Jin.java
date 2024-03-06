import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shin-Eun-Jin {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append(twoPointer(br.readLine())).append("\n");
		}
		System.out.println(sb);

	}

	static int twoPointer(String input) {

		// 회문일 경우
		if (check(input))
			return 0;
		else {

			int start = 0;
			int end = input.length() - 1;

			for (int i = 0; i < input.length() / 2; i++) {
				if (input.charAt(start) != input.charAt(end)) {
					break;
				}

				start++;
				end--;
			}

			String remove1 = input.substring(0, start) + input.substring(start + 1);
			String remove2 = input.substring(0, end) + input.substring(end + 1);

			if (check(remove1) || check(remove2)) {
				return 1;
			}
		}
		return 2;
	}

	static boolean check(String str) {

		String reverseStr = new StringBuilder(str).reverse().toString();

		if (str.equals(reverseStr)) {
			return true;
		}
		return false;
	}

}
