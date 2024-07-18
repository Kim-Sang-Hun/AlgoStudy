package Algo_week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16953 {
	
	static long A,B;
	static Queue<long[]> q;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		q = new ArrayDeque<long[]>();
		
		q.add(new long[] {A,1});
		
		while (!q.isEmpty()) {
			
			long[] cur = q.poll();
			long num = cur[0];
			long cnt = cur[1];
			
			if (num == B) {
				System.out.println(cnt);
				return;
			}
			if (num > B) continue;
			
			q.add(new long[] {num*2, cnt+1});
			q.add(new long[] {num*10 + 1, cnt+1});
			
		}
		
		System.out.println(-1);
		
	}
}
