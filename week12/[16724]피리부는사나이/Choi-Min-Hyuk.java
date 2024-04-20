import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16724_피리부는사나이_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, answer, map[][];
	static boolean[][] visited, reachable;
	static int dx[] = { -1, 1, 0, 0 }; // UDLR 순서
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				int c = str.charAt(j);
				
				if (c == 'U')
					map[i][j] = 0;
				
				else if (c == 'D')
					map[i][j] = 1;
				
				else if (c == 'L')
					map[i][j] = 2;
				
				else if (c == 'R')
					map[i][j] = 3;
			}
		}
		
		visited = new boolean[N][M];
		reachable = new boolean[N][M];
		answer = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j])
					dfs(i, j);
			}
		}

		System.out.print(answer);

	}

	public static void dfs(int x, int y) {
		visited[x][y] = true;

		int nx = x + dx[map[x][y]];
		int ny = y + dy[map[x][y]];

		if (!visited[nx][ny]) {
			dfs(nx, ny);
		}

		else if (!reachable[nx][ny]) {
			answer++;
		}

		reachable[x][y] = true;
	}
}
