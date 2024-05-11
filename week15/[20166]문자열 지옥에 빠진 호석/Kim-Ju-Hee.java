package week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MulticastSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class JUN20166_문자열지옥에빠진호석_김주희 {
	static int N, M, K, cnt;
	static char[][] map;
	static String[] targets;
	static int dr[] = new int[] {0,0,1,-1,1,1,-1,-1};
	static int dc[] = new int[] {1,-1,0,0,1,-1,1,-1};
	
	static class Dot{
		int r, c;
		
		public Dot(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		@Override
		public String toString() {
			return this.r + " : " + this.c;
		}
	}
	
	private static void dfs(Dot now, String target, int depth) {
		if(depth == target.length()) {
			cnt++;
			return;
		}
		if(target.charAt(depth) != map[now.r][now.c]) return;
		
	
		for (int i = 0; i < 8; i++) {
			int nr = (now.r + dr[i] + N) % N;
			int nc = (now.c + dc[i] + M) % M;

			dfs(new Dot(nr,nc), target, depth+1);
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = input.charAt(j);
			}
		}
		
		targets = new String[K];
		for (int i = 0; i < K; i++) {
			targets[i] = br.readLine();
		}
		
		Map<String, Integer> answers = new HashMap<String, Integer>();
		
		for (String target : targets) {
			if(answers.containsKey(target)) {
				System.out.println(answers.get(target));
				continue;
			}
			
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(target.charAt(0) != map[i][j]) continue;
					dfs(new Dot(i,j), target, 0);
				}
			}
			System.out.println(cnt/8);
			answers.put(target, cnt/8);
		}
		
	}

}
