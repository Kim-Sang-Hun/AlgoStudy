import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1918_후위표기식_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static Stack<Character> stack = new Stack<>();
	
	public static void main(String[] args) throws Exception {
		char arr[] = br.readLine().toCharArray();

		for (Character c : arr) {
			if (c >= 'A' && c <= 'Z')
				sb.append(c);

			else if (c == '(')
				stack.push(c);

			else if (c == ')') {
				while (!stack.isEmpty()) {
					if (stack.peek() == '(') {
						stack.pop();
						break;
					}
					sb.append(stack.pop());
				}
			}

			else {
				while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(c)) {
					sb.append(stack.pop());
				}
				stack.push(c);
			}
		}

		while (!stack.isEmpty())
			sb.append(stack.pop());

		System.out.print(sb);
	}
	
	private static int getPriority(char c) {
		if (c == '(')
			return 0;
		
		else if (c == '+' || c == '-')
			return 1;
		
		else
			return 2;
	}
}
