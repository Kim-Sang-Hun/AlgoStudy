package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <미로 탈출> 
* 링크
* https://www.acmicpc.net/problem/14923
* 요약
* 
* 풀이
* 3차원 bfs
* 메모리
* 137180KB
* 시간
* 1064ms
*/
public class boj_14923 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		boolean state;
		int x, y;

		public Pos(boolean state, int x, int y) {
			this.state = state;
			this.x = x;
			this.y = y;
		}
	}

	static final int WALL = 12345678;

	static int N, M, Hx, Hy, Ex, Ey;
	static int[][] arr;
	static int[][][] distance;
	static int minDistance;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 시작위치
		st = new StringTokenizer(br.readLine());
		Hx = Integer.parseInt(st.nextToken()) - 1;
		Hy = Integer.parseInt(st.nextToken()) - 1;

		// 종료위치
		st = new StringTokenizer(br.readLine());
		Ex = Integer.parseInt(st.nextToken()) - 1;
		Ey = Integer.parseInt(st.nextToken()) - 1;

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1) {
					arr[i][j] = WALL;
				}
			}
		}

		// 풀이
		bfs(new Pos(true, Hx, Hy)); // true : 벽 부수기 1회 남아있음

		// 출력
		sb.append(minDistance);
		bw.write(sb.toString());
		bw.flush();
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	private static void bfs(Pos pos) {
		distance = new int[2][N][M];
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < N; j++) {
				Arrays.fill(distance[i][j], Integer.MAX_VALUE);
			}
		}
		distance[0][pos.x][pos.y] = 0;

		Queue<Pos> q = new ArrayDeque<>();
		q.offer(pos);

		while (!q.isEmpty()) {
			Pos current = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = current.x + dx[d];
				int ny = current.y + dy[d];

				// 범위 체크
				if (!isIn(nx, ny)) {
					continue;
				}

				if (current.state) { // 벽부수기 마법 있을 때
					if (arr[nx][ny] == 0) { // 벽이 아닐 때
						if (distance[0][current.x][current.y] + 1 < distance[0][nx][ny]) {
							distance[0][nx][ny] = distance[0][current.x][current.y] + 1;
							q.offer(new Pos(current.state, nx, ny));
						}
					} else { // 벽일 때 : arr[nx][ny] == 1
						distance[1][nx][ny] = distance[0][current.x][current.y] + 1;
						q.offer(new Pos(false, nx, ny));
					}
				} else { // 벽부수기 마법 없을 떄
					if (arr[nx][ny] == 0) { // 벽이 아닐 때
						if (distance[1][current.x][current.y] + 1 < distance[1][nx][ny]) {
							distance[1][nx][ny] = distance[1][current.x][current.y] + 1;
							q.offer(new Pos(current.state, nx, ny));
						}
					} else { // 벽일 때 : arr[nx][ny] == 1
						; // 이미 마법 써서 못 감
					}
				}
			}
		}
		minDistance = Integer.min(distance[0][Ex][Ey], distance[1][Ex][Ey]);
		if (minDistance == Integer.MAX_VALUE)
			minDistance = -1;
	}

	private static boolean isIn(int nx, int ny) {
		if (0 <= nx && nx < N && 0 <= ny && ny < M) {
			return true;
		}
		return false;
	}
}
