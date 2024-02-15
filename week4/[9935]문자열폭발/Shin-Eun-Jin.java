import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9935 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String originalStr = br.readLine();
		String bombStr = br.readLine();

		StringBuilder result = new StringBuilder();

		int bombLen = bombStr.length();
		for (int i = 0; i < originalStr.length(); i++) {
			result.append(originalStr.charAt(i));

			int resultLen = result.length();
			if (resultLen >= bombLen) {
				boolean bomb = true;

				for (int j = 0; j < bombLen; j++) {
					if (result.charAt(resultLen - bombLen + j) != bombStr.charAt(j)) {
						bomb = false;
						break;
					}
				}

				if (bomb) {
					result.setLength(resultLen - bombLen);
				}
			}
		}
		System.out.println(result.length() == 0 ? "FRULA" : result);
	}
}
