import java.util.Scanner;
import java.util.Stack;

/*
* 어렵다 매우
* 알파벳을 만나면 바로 기록
* (를 만나면 스택에 넣기
* )를 만나면 (를 만날때까지 스택에서 빼서 기록
* *나 /를 만나면 전이 *나 /가 아닐 때까지 빼서 기록(같은 우선순위가 아닐 때까지)
* +나 -를 만나면 전이 연산자라면 계속 빼서 기록(같은 우선순위 결과가 끝난 것이므로)
*/
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringBuilder sb = new StringBuilder();
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			char prev;
			if (Character.isAlphabetic(c)) {
				sb.append(c);
			} else if (c == '(') {
				stack.add(c);
			} else if (c == ')') {
				while (!stack.isEmpty()) {
					prev = stack.pop();
					if (prev == '(') break;
					sb.append(prev);
				}
			} else if (c == '*' || c == '/') {
				while (!stack.isEmpty()) {
					prev = stack.peek();
					if (!(prev == '*' || prev == '/')) break;
					sb.append(stack.pop());
				}
				stack.add(c);
			} else if (c == '+' || c == '-') {
				while (!stack.isEmpty()) {
					prev = stack.peek();
					if (prev == '*' || prev == '/' || prev == '+' || prev == '-') {
						sb.append(stack.pop());
					} else break;
				}
				stack.add(c);
			}
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
	}
}
