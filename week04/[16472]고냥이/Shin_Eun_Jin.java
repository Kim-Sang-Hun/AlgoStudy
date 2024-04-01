import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Shin_Eun_Jin {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();

		HashSet<Character> set = new HashSet<>();

		int maxLen = Integer.MIN_VALUE;

		char preChar = ' ';
		for (int start = 0; start < str.length(); start++) {
			if (preChar == str.charAt(start))
				continue;

			set.clear();
			preChar = str.charAt(start);

			for (int end = start; end < str.length(); end++) {
				set.add(str.charAt(end));

				if (set.size() > N) {
					maxLen = Math.max(maxLen, end - start);
					break;
				}

				if (end == str.length() - 1) {
					maxLen = Math.max(maxLen, end - start + 1);
				}
			}
		}

		System.out.println(maxLen);
	}
}
