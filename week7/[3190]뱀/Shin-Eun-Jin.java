import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int N, K, L;
	static int[][] map;

	// 상, 우, 하, 좌
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine()); // 보드의 크기
		K = Integer.parseInt(br.readLine()); // 사과의 개수

		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			map[r][c] = 1; // 사과 표시
		}

		L = Integer.parseInt(br.readLine()); // 뱀의 방향 변환 횟수

		Queue<int[]> dir = new LinkedList<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int d = st.nextToken().equals("D") ? 1 : -1;

			dir.offer(new int[] { t, d });
		}

		int time = 0;
		int d = 1; // 초기 뱀의 방향은 오른쪽
		int[] next = dir.poll();
		int nextT = next[0];
		int nextD = next[1];

		List<Pos> sList = new LinkedList<>();
		sList.add(new Pos(0, 0));
		Pos head = new Pos(0, 0);
		map[0][0] = 2;

		while (true) {

			head.r += dr[d];
			head.c += dc[d];

			time++;

			// 벽이나 자기자신의 몸과 부딪힐 경우
			if (head.r < 0 || head.r >= N || head.c < 0 || head.c >= N || map[head.r][head.c] == 2) {
				break;
			}

			// 사과가 없는 경우
			if (map[head.r][head.c] == 0 && sList.size() != 0) {
				Pos tail = sList.remove(0);
				map[tail.r][tail.c] = 0;
			}

			sList.add(new Pos(head.r, head.c));
			map[head.r][head.c] = 2;

			if (time == nextT) {
				d = (4 + d + nextD) % 4;

				if (!dir.isEmpty()) {
					next = dir.poll();
					nextT = next[0];
					nextD = next[1];
				}
			}
		}

		System.out.println(time);
	}

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
