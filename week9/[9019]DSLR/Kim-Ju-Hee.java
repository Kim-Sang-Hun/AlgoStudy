package march4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN9019_DSLR_김주희 {
	static int init, dest;
	static String answer;
	static boolean visited[];
	
	static class Result{
		int num;
		String str = "";
		
		public Result(int num, String str) {
			this.num = num;
			this.str = str;
		}
	}
	
	private static int QtoNum(Deque<Integer> q) {
		int result = 0;
		for(int i = 1; i < 1001; i*=10) {
			result += q.pollLast()*i;
		}
		return result;
	}
	
	private static int D(int num) {
		num *= 2;
		num %= 10000;
		return num;
	}
	
	private static int S(int num) {
		if(num == 0) return 9999;
		return num-1;
	}
	
	private static int L(int num) {
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 1000; i > 0; i/=10) {
			q.addLast(num/i);
			num -= num/i*i;
		}
		
		q.addLast(q.pollFirst());
		return QtoNum(q);
	}
	
	private static int R(int num) {
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 1000; i > 0; i/=10) {
			q.addLast(num/i);
			num -= num/i*i;
		}
		
		q.addFirst(q.pollLast());
		return QtoNum(q);
	}
	
	private static void bfs(int start) {
		Queue<Result> q = new LinkedList<>();
		visited = new boolean[10000];
		
		q.add(new Result(start, ""));
		visited[start] = true;
		
		while(!q.isEmpty()) {
			Result now = q.poll();
			
			if(now.num == dest) {
				answer = now.str;
				break;
			}
			
			int d = D(now.num);
			if(!visited[d]) {
				visited[d] = true;
				q.add(new Result(d, now.str + "D"));
			}
			
			int s = S(now.num);
			if(!visited[s]) {
				visited[s] = true;
				q.add(new Result(s, now.str + "S"));
			}
			
			int l = L(now.num);
			if(!visited[l]) {
				visited[l] = true;
				q.add(new Result(l, now.str + "L"));
			}
			
			int r = R(now.num);
			if(!visited[r]) {
				visited[r] = true;
				q.add(new Result(r, now.str + "R"));
			}

		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			
			init = Integer.parseInt(st.nextToken());
			dest = Integer.parseInt(st.nextToken());
			
			bfs(init);
			System.out.println(answer);
			
		}

	}

}
