package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JUN9935 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		String bomb = br.readLine();
		
		StringBuilder result = new StringBuilder(); // deque로 풀었다가 시간초과나서 바꿈
		StringBuilder window = new StringBuilder();
		
		for (int i = 0; i < input.length(); i++) {
			window.append(input.charAt(i));
			
			if(window.length() == bomb.length()) {
				if(window.toString().equals(bomb)) {
					window.setLength(0); // 초기화
					for (int j = 0; j < bomb.length() - 1; j++) {
						if(result.length() == 0) continue;
			
						window.insert(0, result.charAt(result.length() - 1));
						result.setLength(result.length() - 1);
					}
				}else {
					result.append(window.charAt(0));
					window.deleteCharAt(0);
				}
			}
		}
		
		if(result.length() == 0 && window.length() == 0) {
			System.out.println("FRULA");
		}else {
			System.out.print(result);
			System.out.print(window);
		}
	}
}
