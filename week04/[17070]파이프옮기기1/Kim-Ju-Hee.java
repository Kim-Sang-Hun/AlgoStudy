package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN17070 {
	static int N, answer;
	static boolean map[][];
	
	private static void dfs(int r, int c, int flag) {
		if(r == N-1 && c == N-1) {
			answer++;
			return;
		}
		
		if(r >= N || c >= N) return;

		int nr = r + 1;
		int nc = c + 1;
	
		if(nc < N && map[r][nc] && flag != 1) { // 가로로 이동, 이전이 세로였으면 못함
			dfs(r, nc, 0);
		}
		if(nr < N && map[nr][c] && flag != 0  ) { // 세로로 이동, 이전이 가로였으면 못함
			dfs(nr, c, 1);
		}
		if(nr < N && nc < N && map[nr][nc] && map[r][nc] && map[nr][c]) { // 대각선 이동
			dfs(nr, nc, 2);
		}
	}
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if(Integer.parseInt(st.nextToken()) == 0) map[i][j] = true;
			}
		}
		
		// 탐색
		answer = 0;
		dfs(0,1,0); // flag - 0:가로, 1:세로, 2:대각선
		
		// 출력
		System.out.println(answer);
	}
}
