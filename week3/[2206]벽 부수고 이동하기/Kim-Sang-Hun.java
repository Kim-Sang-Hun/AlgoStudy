import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		// 벽을 부쉈을 때의 visited배열과 아직 부수지 않았을때의 visited배열을 구분하기 위해 3차원 배열로 만들어준다.
		boolean[][][] visited = new boolean[N][M][2];
		int answer = N * M + 1;
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		}

		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		// int[0] = y, int[1] = x, int[2] = count, int[3] = canBreakWall;
		queue.add(new int[] { 0, 0, 1, 1 });
		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();

			if (tmp[0] == N - 1 && tmp[1] == M - 1) {
				answer = tmp[2];
				break;
			}
			for (int i = 0; i < dirs.length; i++) {
				int nY = tmp[0] + dirs[i][0];
				int nX = tmp[1] + dirs[i][1];

				if (nY >= N || nX >= M || nY < 0 || nX < 0 || visited[nY][nX][tmp[3]]) {
					continue;
				}
				// 만약 벽을 만났는데
				if (map[nY][nX] == 1) {
					// 아직 벽을 부수지 않았다면 벽 부술 기회를 차감하고 queue에 넣어준다.
					if (tmp[3] == 1) {
						visited[nY][nX][1] = true;
						queue.add(new int[] { nY, nX, tmp[2] + 1, 0 });
					}
				} else {
					// 벽이 아니라면 벽 부술 기회에 해당하는 visited배열의 값을 변경해주고 queue에 집어넣는다.
					visited[nY][nX][tmp[3]] = true;
					queue.add(new int[] { nY, nX, tmp[2] + 1, tmp[3] });
				}
			}
		}
		System.out.print(answer == N * M + 1 ? -1 : answer);
	}
}
