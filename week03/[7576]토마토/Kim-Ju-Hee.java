package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN7576 {
	static int N, M, answer, count, box[][];
	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };

	private static Queue<int[]> bfs(Queue<int[]> ripen) {
		Queue<int[]> next = new LinkedList<>();
		while (!ripen.isEmpty()) {
			int[] tomato = ripen.poll();

			for (int j = 0; j < 4; j++) {
				int nr = tomato[0] + dr[j];
				int nc = tomato[1] + dc[j];

				if (nr < 0 || nr > N - 1 || nc < 0 || nc > M - 1 || box[nr][nc] != 0) {continue;}

				next.add(new int[] { nr, nc });
				box[nr][nc] = 1;
				count++;
			}
		}
		return next;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		count = 0; // 0이 아닌 칸 수
		answer = 0;

		box = new int[N][M];
		Queue<int[]> ripen = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st1.nextToken());

				if (box[i][j] == 1) {
					ripen.add((new int[] { i, j }));
					count++;
				} else if (box[i][j] == -1) {
					count++;
				}
			}
		}

		while (true) {
			ripen = bfs(ripen);
			
			if (ripen.isEmpty()) break; // 다음에 익을 토마토 없으면
			else answer++;
		}

		if (count < N * M) System.out.println(-1); // 탐색 끝났는데 다 안익음
		else System.out.println(answer);
	}
}
