package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class JUN16472 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		String input = br.readLine();
		Set<Character> check = new HashSet<Character>(); // 알파벳 수 체크하기 위한 set
		int L = input.length();
		
		if(input.length() == 1) {
			System.out.println(1);
			return;
		}
		
		int left = 0;
		int right = 0;
		check.add(input.charAt(left));
		int answer = 0;
		
		while(right < L) {
			check.add(input.charAt(right));
			
			if(check.size() > N) {
				answer = Math.max(answer, right - left);
				check.remove(input.charAt(left));
				left++;
				for (int i = left; i <= right; i++) {
					check.add(input.charAt(i));
				}
			}
			
			right++;
		}
		
		answer = Math.max(answer, right - left); // 마지막에 한번 더 갱신
		
		System.out.println(answer);
	}
}
