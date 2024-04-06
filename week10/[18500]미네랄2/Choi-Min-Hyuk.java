import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18500_미네랄2_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int R, C, N;
	static boolean[][] map, visited, check;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R + 2][C + 2];
		visited = new boolean[R + 2][C + 2];
		check = new boolean[R + 2][C + 2];

		for (int i = 1; i <= R; i++) {
			char[] input = br.readLine().toCharArray();
			for (int j = 1; j <= C; j++) {
				if (input[j - 1] == 'x') {
					map[i][j] = true;
				}
			}
		}

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int height = Integer.parseInt(st.nextToken());
			delete(R - height + 1, i % 2 == 0 ? 1 : C);
		}
		
		print(map);
	}

	private static void delete(int num, int j) {
		int plus = -1;
		if (j == 1)
			plus = 1;

		while (j >= 1 && j <= C) {
			if (map[num][j]) {
				map[num][j] = false;
				isDivide(num, j);
				return;
			}
			j += plus;
		}
	}
	
	private static void init(boolean[][] arr) {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				arr[i][j] = false;
			}
		}
	}

	private static boolean isDivide(int x, int y) {
		init(visited);
		
		for (int j = 1; j <= C; j++) {
			if (map[R][j] && !visited[R][j]) {
				dfs(R, j);
			}
		}

		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (map[i][j] && !visited[i][j]) {
					init(check);
					down(findMinLength(i, j));
					return true;
				}
			}
		}
		
		return false;
	}

	private static int findMinLength(int x, int y) {
		if (!map[x][y] || check[x][y]) {
			return Integer.MAX_VALUE;
		}
		check[x][y] = true;
		int min = R - x;

		for (int i = x; i <= R; i++) {
			if (visited[i][y]) {
				min -= R - i + 1;
				break;
			}
		}

		for (int i = 0; i < 4; i++) {
			min = Math.min(min, findMinLength(x + dx[i], y + dy[i]));
		}
		return min;
	}

	private static void dfs(int x, int y) {
		if (!map[x][y] || visited[x][y]) {
			return;
		}
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			dfs(x + dx[i], y + dy[i]);
		}
	}

	private static void down(int length) {
		for (int j = 1; j <= C; j++) {
			for (int i = R; i >= 1; i--) {
				if (map[i][j] && !visited[i][j]) {
					map[i][j] = false;
					map[i + length][j] = true;
				}
			}
		}
	}

	private static void print(boolean[][] map) {
		StringBuilder sb = new StringBuilder();
		String lineSeparator = System.lineSeparator();
		
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				sb.append(map[i][j] ? 'x' : '.');
			}
			sb.append(lineSeparator);
		}
		System.out.print(sb);
	}
}
