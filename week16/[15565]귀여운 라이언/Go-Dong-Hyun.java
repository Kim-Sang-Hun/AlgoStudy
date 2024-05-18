package Algo_week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ15565 {
	
	static int N,K,cnt,ans;

	public static void main(String[] args) throws Exception {
		
		//라이언 1, 어피치 2
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		cnt = 0;
		ans = Integer.MAX_VALUE;
		
		ArrayDeque<Integer> dq = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			dq.add(Integer.parseInt(st.nextToken()));
			if (dq.peekLast() == 1) cnt++;
			
			if (cnt == K) {
				while (true) {
					if (dq.peekFirst() == 2) {
						dq.pollFirst();
						ans = Math.min(ans, dq.size());
						continue;
					}
					dq.pollFirst();
					cnt--;
					break;
				}
			}
		}
		
		if (ans == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
}
