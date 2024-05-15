package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <골목 대장 호석 - 기능성> G5
* 링크
* https://www.acmicpc.net/problem/20168
* 요약
* N개 정점, M개 간선, 양방향 그래프
* 주어진 그래프에서 정점A -> 정점B 로 이동할 때, C이하의 비용을 갖는 경로들 중 각 경로의 최대 골목 비용의 최소값
* 풀이
* 백트레킹
*/
public class boj_20168 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int N, M, A, B, C; // 정점 개수, 골목 개수, 시작, 종료, 비용
	static int[][] arr; // A->B 비용
	static boolean[] vis; // 방문 관리
	static ArrayList<Integer> path = new ArrayList<>(); // 경로
	static int minWeight = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		arr = new int[N + 1][N + 1];
		vis = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			arr[to][from] = weight;
			arr[from][to] = weight;
		}

		// 풀이
		path.add(A);
		vis[A] = true;
		bt(A, 0);

		// 출력
		if (minWeight == Integer.MAX_VALUE)
			System.out.println("-1");
		else
			System.out.println(minWeight);
	}

	private static void bt(int current, int weightSum) {
		if (current == B) {
//			for (int i : path) {
//				System.out.print(i + " ");
//			}
//			System.out.println();
			int currentPathMaxWeight = Integer.MIN_VALUE;
			for (int i = 0; i < path.size() - 1; i++) {
				currentPathMaxWeight = Math.max(currentPathMaxWeight, arr[path.get(i)][path.get(i + 1)]);
			}
			minWeight = Math.min(minWeight, currentPathMaxWeight);
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (arr[current][i] == 0)
				continue; // 경로 없음
			if (vis[i])
				continue; // 이미 방문
			if (weightSum + arr[current][i] > C)
				continue; // 예산초과

			vis[i] = true;
			path.add(i);
			bt(i, weightSum + arr[current][i]);
			path.remove(path.size() - 1);
			vis[i] = false;
		}
	}
}
