import java.util.*;
import java.io.*;

public class Main {
	

	static StringBuilder sb = new StringBuilder();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, answer = 0;
	static int[][] a;
	static boolean[][] vis;
	static final int[] dx = {0,1,0,-1};
	static final int[] dy = {1,0,-1,0};
	
	/*
	 * name: rec method
	 * 해당 함수는 문제의 방향성과는 조금 다르게 해석되어있을 수도 있음을 참고하시기 바랍니다.
	 * 5가지의 도형을 통해 갈 수 있는 모든 경로를 생각해하면,
	 * 길이 4sqrt(2)의 마름모 꼴이 나온다.
	 * 이 때의 경로들에 대해 탐색함으로써 마름모 내의 스칼라 4의 모든 경로를 지나갈 수 있다.
	 */
  
	static void rec(int x, int y, int cnt, int sum) {
		if(cnt == 4) {
			answer = Math.max(answer, sum);
			return;
		}
		for(int i = 0;i < 4; ++i) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
			if(vis[nx][ny]) continue;
			if(cnt == 2) {
				vis[nx][ny] = true;
				rec(x, y, cnt + 1, sum + a[nx][ny]);
				vis[nx][ny] = false;
			}
			vis[nx][ny] = true;
			rec(nx, ny, cnt + 1, sum + a[nx][ny]);
			vis[nx][ny] = false;
		}
	}

	static void solution() throws IOException {
		//n * m 배열을 돌며 각 위치로부터 시작한 테트로미노 최대값 찾기
		for(int i = 0;i < n; ++i) {
			for(int j = 0;j < m; ++j) {
				vis[i][j] = true;
				rec(i, j, 0, 0);
				vis[i][j] = false;
			}
		}
		System.out.println(answer);
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		a = new int[n][m];
		vis = new boolean[n][m];
		for(int i = 0;i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j < m; ++j) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}		
	}
	
	public static void main(String[] args) throws IOException {
		//메서드화하여 가독성 향상
		input();
		solution();
	}
}
