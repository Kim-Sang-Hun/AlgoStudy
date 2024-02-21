import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 브루트 포스로 풀었다.
// 무조건 3개를 놓아야 하므로 조합으로 해결했다. 최대 64C3이므로 할 만해 보인다.
// 조합에 쓰일 좌표를 뽑을 때 0부터 N*M까지 돌리고, y좌표는 i / M, x좌표는 i % M을 하게 되면 정확한 위치를 구할 수 있다.
public class Main {

// 세로, 가로, 총 안전영역, 퍼지는 수, 최대 안전영역
	static int N, M, safeSpot, spreadVirus, maxSafeSpot;
	static int[][] map;
	static int[] dirY = {0, 0, -1, 1};
	static int[] dirX = {-1, 1, 0, 0};
	static boolean[][] visited;
	static int[] walls;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		walls = new int[3];
		maxSafeSpot = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					++safeSpot;
				}
			}
		}
		safeSpot -= 3;
		combi(0, 0);
		System.out.println(maxSafeSpot);
	}

	public static void combi(int count, int start) {
//  저장해둔 좌표를 1로 바꾸고 bfs를 돌린 다음 다시 0으로 바꿔준다.
		if (count == 3) {
			for (int i = 0; i < walls.length; i++) {
				map[walls[i] / M][walls[i] % M] = 1;
			}
			bfs();
			for (int i = 0; i < walls.length; i++) {
				map[walls[i] / M][walls[i] % M] = 0;
			}
			int tmpSafeSpot = safeSpot - spreadVirus;
			if (maxSafeSpot < tmpSafeSpot) {
				maxSafeSpot = tmpSafeSpot;
			}
			return;
		}

		for (int i = start; i < N * M; i++) {
			if (map[i / M][i % M] == 0) {
				walls[count] = i;
				combi(count + 1, i + 1);
			}
		}

	}

	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		visited = new boolean[N][M];
		spreadVirus = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 2) {
					visited[i][j] = true;
					q.add(new int[] { i, j });
				}
			}
		}

		while (!q.isEmpty()) {
			if (safeSpot - spreadVirus < maxSafeSpot) return;
			int[] tmp = q.poll();

			for (int i = 0; i < 4; i++) {
				int nY = tmp[0] + dirY[i];
				int nX = tmp[1] + dirX[i];

				if (nY >= N || nX >= M || nY < 0 || nX < 0 || visited[nY][nX])
					continue;
				if (map[nY][nX] != 0)
					continue;
				++spreadVirus;
				visited[nY][nX] = true;
				q.add(new int[] { nY, nX });
			}

		}
	}
}
