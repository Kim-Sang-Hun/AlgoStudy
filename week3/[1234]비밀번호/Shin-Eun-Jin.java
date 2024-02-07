import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String word = st.nextToken();
			Stack<Integer> stack = new Stack<>();

			for (char c : word.toCharArray()) {
				int curNum = c - 48;

				if (!stack.isEmpty() && stack.peek() == curNum) {
					stack.pop();
				} else {
					stack.add(curNum);
				}
			}

			System.out.print("#" + tc + " ");
			for (int num : stack) {
				System.out.print(num);
			}
			System.out.println();
		}
	}
}
