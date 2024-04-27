package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <등수 찾기> G3
* 링크
* https://www.acmicpc.net/problem/17616
* 요약
* 
* 풀이
* bfs
*/
public class boj_17616 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M, X; // 1~N명의 학생, M번의 질문, 등수 범위 알고 싶은 학생 X
	static int A, B; // 입력 A B : A가 B보다 점수 높음
	static int U, V; // 출력 U V : X가 가능한 가장 높은 등수 U, 가장 낮은 등수 V

	static ArrayList<ArrayList<Integer>> listTop = new ArrayList<>(); // 최대값 구하기 위한 인접리스트
	static ArrayList<ArrayList<Integer>> listBottom = new ArrayList<>(); // 최소값 구하기 위한 인접리스트
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) { // 0번 학생 제외
			listTop.add(new ArrayList<Integer>());
			listBottom.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			listTop.get(B).add(A); // X에서 시작해서 최대 경로 구하기, 경로길이+1 == 최대 등수
			listBottom.get(A).add(B); // X에서 시작해서 최대 경로 구하기, N-경로길이 == 최소 등수
		}

		// 풀이
		U = bfs(true); // TOP
		V = bfs(false); // BOTTOM

		// 출력
		bw.write(U + " " + V);
		bw.flush();
	}

	private static int bfs(boolean b) {
		// init
		ArrayList<ArrayList<Integer>> list;
		if (b)
			list = listTop;
		else
			list = listBottom;

		// bfs
		visit = new boolean[N + 1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(X);
		visit[X] = true;

		int cnt = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : list.get(cur)) {
				if (visit[next]) continue;
				q.offer(next);
				visit[next] = true;
				cnt++;
			}
		}
		
		if (b) { // TOP
			return cnt + 1;
		} else { // BOTTOM
			return N - cnt;
		}
	}
}
