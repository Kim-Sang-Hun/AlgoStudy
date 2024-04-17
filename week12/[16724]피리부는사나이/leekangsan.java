package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <피리 부는 사나이> G3
* 링크
* https://www.acmicpc.net/problem/16724
* 요약
* bfs 탐색
* 풀이
* 지도 밖으로 나가는 방향의 입력은 주어지지 않는다. == 주어진 방향대로 진행하다 보면 방문했던 곳 재방문하게 됨.
* 중복 방문한 지점이 현재 번호(startCount)와 동일 -> 새로운 사이클이므로 safeZoneCount++
* 중복 방문한 지점이 이전 번호 -> 이전 사이클의 경로에 흡수
*/
public class boj_16724 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int i, j;

		public Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int N, M;
	static char[][] arr;
	static int[][] vis;
	static int startCount = 0;
	static int safeZoneCount = 0;

	public static void main(String[] args) throws IOException {
		// 입력
		init();

		// 풀이
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (vis[i][j] == 0) {
					bfs(i, j);
				}
			}
		}

		// 출력
		sb.append(safeZoneCount);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void init() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		vis = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < M; j++) {
				arr[i][j] = str.charAt(j);
			}
		}
	}

	private static void bfs(int si, int sj) {
		Queue<Pos> q = new ArrayDeque<>();
		startCount++;
		vis[si][sj] = startCount;
		q.offer(new Pos(si, sj));
		while (!q.isEmpty()) {
			int ci = q.peek().i;
			int cj = q.poll().j;

			int dir = arr[ci][cj];
			int ni = ci;
			int nj = cj;
			if (dir == 'U')
				ni--;
			else if (dir == 'D')
				ni++;
			else if (dir == 'L')
				nj--;
			else // dir == 'R'
				nj++;
			if (vis[ni][nj] == 0) {
				vis[ni][nj] = startCount;
				q.offer(new Pos(ni, nj));
			} else {
				if (vis[ni][nj] == startCount)
					safeZoneCount++;
			}
		}
	}
}
