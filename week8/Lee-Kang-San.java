package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <뱀과 사다리 게임> G5
 * 링크
 * https://www.acmicpc.net/problem/16928
 * 요약
 * 1에서 100까지 주사위 굴려 도달하기. 단 이동한 칸에 뱀이나 사다리 존재할 경우 바로 해당 칸으로 이동.
 * 풀이
 * bfs
 */
public class boj_16928 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, M; // 사다리 수 N, 뱀 수 M
	static int[] additionalMove = new int[101]; // [0] 제외, i번쨰 칸이 사다리/뱀이면 추가 이동 값
	static int[] arriveCnt = new int[101]; // i번쨰 칸에 도달 위해 주사위 굴린 최소 회수

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int move = Integer.parseInt(st.nextToken()) - idx;
			additionalMove[idx] = move;
		}
		// System.err.println(Arrays.toString(additionalMove));
		// 풀이
		bfs();
		// 출력
		sb.append(arriveCnt[100]);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void bfs() {
		Arrays.fill(arriveCnt, Integer.MAX_VALUE);
		Queue<Integer> q = new ArrayDeque<>();
		arriveCnt[1] = 0;
		q.offer(1);
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur > 100)
				continue;
			// 도착한 칸이 뱀이나 사다리인 경우 바로 뱀이나 사다리 따라 이동
			if (additionalMove[cur] != 0) {
				if (arriveCnt[cur + additionalMove[cur]] > arriveCnt[cur]) {
					arriveCnt[cur + additionalMove[cur]] = arriveCnt[cur];
					q.offer(cur + additionalMove[cur]);
				}
				continue;
			}
			// 도착한 칸이 평범한 칸인 경우
			for (int i = 1; i <= 6; i++) {
				if (cur + i > 100)
					break;
				if (arriveCnt[cur + i] > arriveCnt[cur] + 1) {
					arriveCnt[cur + i] = arriveCnt[cur] + 1;
					q.offer(cur + i);
				}
			}
		}
		// System.out.println(Arrays.toString(arriveCnt));
	}
}
