package Algo_week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class BOJ9935 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String sfrula = br.readLine();
		
		char[] arr = str.toCharArray();
		char[] frula = sfrula.toCharArray();
		
		Stack<Character> stack = new Stack<Character>();
		
		int N = arr.length;
		int M = frula.length;
		
		for (int i = 0; i < N; i++) {
			stack.push(arr[i]);
			
			
			if (stack.size() >= M && stack.peek().equals(frula[M-1])) {
				boolean flag = true;
				for (int j = 0; j < M-1 ; j++) {
					if (frula[j] != stack.get(stack.size()-M+j)) {
						flag = false;
						break;
					} 
				}
				
				if (flag) {
					for (int j = 0; j < M; j++) {
						stack.pop();
					}
					
				}
			} 
			
			
		}
		
		StringBuilder result = new StringBuilder();
		if (stack.size() == 0) {
			result.append("FRULA");
		} else {
			for (Character c : stack) {
				result.append(c);
			}
		}
		System.out.println(result);
	}
}
