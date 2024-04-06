package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <후위 표기식> G2
* 링크
* https://www.acmicpc.net/problem/1918
* 요약
* 중위표기식 -> 후위표기식
* 풀이
* stack, 차량기지 알고리즘
*/
public class boj_1918 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static String inOrderString;
	static int N;

	public static void main(String[] args) throws IOException {
		// 입력
		inOrderString = br.readLine();
		// 풀이
		postOrder(inOrderString);
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void postOrder(String expr) {
		Stack<Character> stack = new Stack<>(); // 연산자용

		int len = expr.length();
		for (int i = 0; i < len; ++i) {
			char c = expr.charAt(i);
			if (c == '(') { // 여는 괄호 무조건 push
				stack.push(c);
			} else if (c == ')') { // 닫는 괄호 만나면 ( 만날때까지 pop 해서 출력
				while (stack.peek() != '(')
					sb.append(stack.pop());
				stack.pop(); // 여는 괄호
			} else if ('A' <= c && c <= 'Z') { // 피연산자 무조건 출력
				sb.append(c);
			} else { // 현 위치(i)가 연산자인 경우 -> 연산자 우선순위 고려 필요
				// 1. stack 빈 상태면 그냥 push
				if (stack.isEmpty())
					stack.push(c);
				// 우선 순위 : ( +- */
				// 2. c가 stack top 보다 우선순위 높은 경우 : stack 에 push
				else if ((stack.peek() == '(') 
						|| (stack.peek() == '+' && c == '*' 
						|| stack.peek() == '+' && c == '/'
						|| stack.peek() == '-' && c == '*' 
						|| stack.peek() == '-' && c == '/')) {
					stack.push(c);
				} else { // 3. 그 외의 경우 : stack이 비거나 c보다 우선순위 낮은 연산자 나올 때 까지 pop해서 출력 후 c push
					if (c == '*' || c == '/') {
						while (!stack.isEmpty() && stack.peek() == '*' || !stack.isEmpty() && stack.peek() == '/') {
							sb.append(stack.pop());
						}
						stack.push(c);
					} else { // c=='+' || c=='-'
						while (!stack.isEmpty() && stack.peek() != '(')
							sb.append(stack.pop());
						stack.push(c);
					}

				}
			}
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
	}
}
