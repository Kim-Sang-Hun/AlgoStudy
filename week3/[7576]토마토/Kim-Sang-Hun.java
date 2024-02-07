package problemsolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN7576 {
	
	static int Y, X;
	static int[][] map;
	static int[][] dirs = new int[][] {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		map = new int[Y][X];
		// 모든 익은 토마토에 대해 시간별로 진행하기 위해 시간으로 정렬하는 PQ를 만든다.
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		boolean isRipe = true;
		for (int i = 0; i < Y; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < X; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				// 맵을 돌면서 1이라면 익은 토마토이므로 큐에 집어넣는다.
				if (map[i][j] == 1) {
					queue.add(new int[] {i, j, 0});
				}
				if (map[i][j] == 0) {
					isRipe = false;
				}
			}
		}
		// 이미 다 익은 토마토라면 0을 출력한다.
		if (isRipe) {
			System.out.println(0);
			return;
		}
		while (!queue.isEmpty()) {
			int[] tmp = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int nY = tmp[0] + dirs[i][0];
				int nX = tmp[1] + dirs[i][1];
				
				if (nY >= Y || nX >= X || nY < 0 || nX < 0 || map[nY][nX] != 0) continue;
				// 시간을 표시하기 위해 경과시간을 맵에 저장한다
				map[nY][nX] = tmp[2] + 1;
				queue.add(new int[] {nY, nX, tmp[2] + 1});
			}
		}
		int max = 0;
		for (int i = 0; i < Y; i++) {
			for (int j = 0; j < X; j++) {
				int tmp = map[i][j];
				// map[i][j]값이 0이라면 익지 않은 토마토가 있다는 말이므로 -1을 출력한다.
				if (tmp == 0) {
					System.out.println(-1);
					return;
				}
				max = Math.max(max, tmp);
			}
		}
		// 마지막으로 가장 오래 걸린 시간을 출력한다
		System.out.println(max);
	}
}
