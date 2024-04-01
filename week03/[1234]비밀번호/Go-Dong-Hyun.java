package Algo_week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class SW1234 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			String lst = st.nextToken();
			char[] arr = lst.toCharArray();
			
			Stack<Character> stack = new Stack<Character>();
			
			stack.add(arr[0]);
			
			for (int i = 1; i < arr.length; i++) {
				if (stack.isEmpty()) {
					stack.add(arr[i]);
				} else if (stack.peek().equals(arr[i])) {
					stack.pop();
				} else {
					stack.add(arr[i]);
				}
			}
			
//			System.out.println(stack.toString());
			System.out.print("#" + t+ " ");
			for (Character c : stack) {
				System.out.print(c);
			}
			System.out.println("");
		}
	}
}
