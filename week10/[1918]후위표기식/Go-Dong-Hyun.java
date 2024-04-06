package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1918 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		char[] arr = br.readLine().toCharArray();
		
		Stack<Character> stack = new Stack<Character>();
		
		for (int i = 0; i < arr.length; i++) {
			
			if ((int)arr[i] >= 65) {
				sb.append(arr[i]);
			} else {
				if (arr[i] == '+' || arr[i] == '-') {
					while (!stack.isEmpty() && stack.peek() != '(') {
						sb.append(stack.pop());
					}
					stack.add(arr[i]);
				} else if (arr[i] == '*' || arr[i] == '/'){
					while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/') && stack.peek()!='(' ) {
						sb.append(stack.pop());
					}
					stack.add(arr[i]);
				} else if (arr[i] == '(') {
					stack.add(arr[i]);
				} else if (arr[i] == ')') {
					while (stack.peek() != '(') {
						sb.append(stack.pop());
					}
					stack.pop();
				}
			}
		}
		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		System.out.println(sb);
		
	}
}
