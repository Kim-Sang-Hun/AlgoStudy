package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <가장 가까운 공통 조상> G4
* 링크
* https://www.acmicpc.net/problem/3584
* 요약
* 
* 풀이
* 트리 탐색, bfs
*/
public class boj_3584 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 트리를 구성하는 노드의 수
	static int[] parents; // 각 노드의 부모 저장한 배열
	static ArrayList<ArrayList<Integer>> list; // 자식들을 저장한 트리
	static int[] depth; // 노드의 깊이 저장
	static int A, B; // 가장 가까운 공통 조상을 구할 두 노드
	static int curA, curB; // A, B의 조상 가리키는 커서

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine().trim());
			parents = new int[N + 1]; // [0] 제외
			Arrays.fill(parents, 0);
			list = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i <= N; i++) {
				list.add(new ArrayList<>());
			}

			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				list.get(parent).add(child);
				parents[child] = parent;
			}

			st = new StringTokenizer(br.readLine().trim());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			// 풀이

			// depth 설정
			depth = new int[N + 1]; // [0] 제외
			Arrays.fill(depth, 0);
			int start = 0;
			for (int i = 1; i <= N; i++) {
				if (parents[i] == 0) {
					start = i;
					break;
				}
			}
			depth[start] = 0;
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(start);
			while (!q.isEmpty()) {
				int cur = q.poll();
				for (int child : list.get(cur)) {
					depth[child] = depth[cur] + 1;
					q.offer(child);
				}
			}

			// 가장 가까운 공통 조상을 구할 두 노드에서 시작
			curA = A;
			curB = B;
			
			// 더 깊이 있는 노드에서 깊이차이 만큼 커서 이동
			if (depth[A] > depth[B]) {
				int diff = depth[A] - depth[B];
				for (int k = 0; k < diff; k++) {
					curA = parents[curA];
				}
			} else if (depth[A] < depth[B]) {
				int diff = depth[B] - depth[A];
				for (int k = 0; k < diff; k++) {
					curB = parents[curB];
				}
			} else { // depth[A] == depth[B]
				;
			}
			
			// 공통 조상 나올때 까지 커서를 한 칸씩 부모로 이동
			while(curA != curB) {
				curA = parents[curA];
				curB = parents[curB];
			}

			// 출력
			sb.append(curA).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
}
