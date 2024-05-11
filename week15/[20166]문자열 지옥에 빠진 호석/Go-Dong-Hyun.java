package week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20166 {
	
	static class Word {
		int x;
		int y;
		int len;
		String w;

		public Word(int x, int y, int len, String w) {
			super();
			this.x = x;
			this.y = y;
			this.len = len;
			this.w = w;
		}
	}
	
	static int N,M,K,maxLen;
	static int[] dx = {-1,-1,-1,0,1,1,1,0};
	static int[] dy = {-1,0,1,1,1,0,-1,-1};
	static char[][] arr;
	static String[] fav;
	static HashMap<String, Integer> hash;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new char[N][M];
		fav = new String[K];
		hash = new HashMap<String, Integer>();
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		maxLen = 0;
		
		for (int i = 0; i < K; i++) {
			fav[i] = br.readLine();
			hash.put(fav[i], 0);
			maxLen = Math.max(maxLen, fav[i].length());
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				bfs(i,j);
			}
		}
		
		for (int i = 0; i < K; i++) {
			System.out.println(hash.get(fav[i]));
		}
		
	}

	private static void bfs(int x, int y) {
		
		Queue<Word> q = new ArrayDeque<>();
		
		q.add(new Word(x, y, 1, arr[x][y]+""));
		
		while (!q.isEmpty()) {
			Word cur = q.poll();
			
			if (hash.containsKey(cur.w)) {
				hash.put(cur.w, hash.get(cur.w)+1);
				continue;
			}
			
			if (cur.len > maxLen)
				continue;
			
			for (int d = 0; d < 8; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				nx = fix(nx,N);
				ny = fix(ny,M);

				q.add(new Word(nx, ny, cur.len+1, cur.w+arr[nx][ny]));
				
			}
		}
	}

	private static int fix(int cur, int maxVal) {
		if (cur == -1)
			return maxVal-1;
		else if (cur == maxVal)
			return 0;
		return cur;
	}

}
