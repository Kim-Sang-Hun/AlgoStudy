import java.util.*;
import java.io.*;

/*
 * 제목
 * <DSLR> G4
 * 링크
 * https://www.acmicpc.net/problem/9019
 * 요약
 * bfs
 * 풀이
 * bfs
 */
public class boj_9019 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class State {
		int num;
		String str;

		public State(int num, String str) {
			this.num = num;
			this.str = str;
		}
	}

	static int A, B;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			// 입력
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			// 풀이
			String ans = bfs();
			// 출력
			sb.append(ans + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static String bfs() {
		Queue<State> q = new ArrayDeque<>();
		boolean[] vis = new boolean[10000];
		State start = new State(A, "");
		q.offer(start);
		while (!q.isEmpty()) {
			State cur = q.poll();
			if (cur.num == B)
				return cur.str;
			else {
				int dnum = d(cur.num);
				int snum = s(cur.num);
				int lnum = l(cur.num);
				int rnum = r(cur.num);

				if (!vis[dnum]) {
					vis[dnum] = true;
					q.offer(new State(dnum, cur.str + "D"));

				}
				if (!vis[snum]) {
					vis[snum] = true;
					q.offer(new State(snum, cur.str + "S"));

				}
				if (!vis[lnum]) {
					vis[lnum] = true;
					q.offer(new State(lnum, cur.str + "L"));

				}
				if (!vis[rnum]) {
					vis[rnum] = true;
					q.offer(new State(rnum, cur.str + "R"));

				}

			}

		}
		return null;
	}

	private static int r(int n) {
		int temp = n % 10;
		int d4 = n % 100 / 10;
		int d3 = n % 1000 / 100;
		int d2 = n / 1000;
		int d1 = temp;
		return ((d1 * 10 + d2) * 10 + d3) * 10 + d4;
	}

	private static int l(int n) {
		int temp = n / 1000;
		int d1 = n % 1000 / 100;
		int d2 = n % 100 / 10;
		int d3 = n % 10;
		int d4 = temp;
		return ((d1 * 10 + d2) * 10 + d3) * 10 + d4;
	}

	private static int s(int n) {
		n -= 1;
		if (n < 0)
			return 9999;
		return n;
	}

	private static int d(int n) {
		n *= 2;
		if (n > 9999)
			return n % 10000;
		return n;
	}
}
