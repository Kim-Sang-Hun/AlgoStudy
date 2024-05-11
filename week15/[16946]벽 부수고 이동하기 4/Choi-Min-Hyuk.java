import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ16946_벽부수고이동하기4_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static String lineSeparator = System.lineSeparator();

	static int n, m;
	static int[][] board;
	static int[][] answer;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static Map<Integer, Integer> map = new HashMap<>();

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		answer = new int[n][m];

		for (int i = 0; i < n; i++) {
			char[] charArray = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				board[i][j] = Character.getNumericValue(charArray[j]);
			}
		}

		// 서로 인접해 있는 빈칸들을 그룹화
		int num = 2;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 0) {
					map.put(num, bfs(i, j, num));
					num++;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 1) {
					answer[i][j] = 1;
					
					// 4방향에 그룹이 몇 개 존재하는지 구함, 이 때 중복 제거를 위해 Set 사용
					Set<Integer> set = new HashSet<>();
					
					for (int d = 0; d < 4; d++) {
						int nx = i + dx[d];
						int ny = j + dy[d];
						if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] == 1) {
							continue;
						}

						set.add(board[nx][ny]);
					}
					
					for (int a : set)
						answer[i][j] += map.get(a);
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(Integer.toString(answer[i][j] % 10));
			}
			sb.append(lineSeparator);
		}
		
		System.out.println(sb);
	}

	// 인접해 있는 빈칸들 그룹화 하기 위한 메소드
	public static int bfs(int x, int y, int num) {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(x, y));
		board[x][y] = num;
		int cnt = 1;

		while (!queue.isEmpty()) {
			Node point = queue.poll();

			for (int d = 0; d < 4; d++) {
				int nx = point.x + dx[d];
				int ny = point.y + dy[d];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m || board[nx][ny] != 0)
					continue;

				board[nx][ny] = num;
				queue.offer(new Node(nx, ny));
				cnt++;
			}
		}

		return cnt;
	}
}
