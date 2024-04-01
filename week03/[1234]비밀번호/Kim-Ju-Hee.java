package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class SWEA1234 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String input = st.nextToken();
			
			
			Deque<Character> q = new ArrayDeque<Character>();
			q.addLast(input.charAt(0));
			for (int i = 1; i < N; i++) {
				char now = input.charAt(i);
				if(!q.isEmpty() && q.peekLast() == now) { // 마지막꺼랑 같으면 pop, 아니면 넣음
					q.pollLast();
				}else {
					q.addLast(now);
				}
			}
			
			System.out.printf("#%d ",tc);
			while(!q.isEmpty()) System.out.print(q.pollFirst());
			System.out.println();
		}

	}

}
