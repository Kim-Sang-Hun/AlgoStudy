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
		PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		boolean isRipe = true;
		for (int i = 0; i < Y; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < X; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					queue.add(new int[] {i, j, 1});
				}
				if (map[i][j] == 0) {
					isRipe = false;
				}
			}
		}
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
				map[nY][nX] = tmp[2] + 1;
				queue.add(new int[] {nY, nX, tmp[2] + 1});
			}
		}
		int max = 0;
		for (int i = 0; i < Y; i++) {
			for (int j = 0; j < X; j++) {
				int tmp = map[i][j];
				if (tmp == 0) {
					System.out.println(-1);
					return;
				}
				max = Math.max(max, tmp);
			}
		}
		System.out.println(max - 1);
	}
}
