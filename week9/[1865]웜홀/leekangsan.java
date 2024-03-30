package isSolving;

import java.util.*;
import java.io.*;

/*
 * 제목
 * <웜홀> G3
 * 링크
 * https://www.acmicpc.net/problem/1865
 * 요약
 * 임의의 시작점에서 출발하고 다시 시작점으로 돌아올 때, 그 회로의 가중치 합이 음수가 되는 경우 찾기
 * 풀이
 * 벨만 포드 알고리즘 
 * - 음의 간선 존재 하는 그래프에서 한 정점에서 다른 정점들로의 최단 거리 계산
 * - V개의 정점으로 이루어진 그래프에서, 한 정점에서 다른 정점까지 경로를 이루는 간선은 최대 V-1개
 * - V번째 탐색 시 최단거리가 갱신되는 정점이 있다면 음수 사이클이 존재  
 * - 모든 정점에서 시작해 볼 필요 없이 아무 정점에서 시작한 후 음수 사이클만 존재하는 지 확인하면 된다. 음수 사이클 없으면 시작 노드로 돌아올 때 음수값 가질 수 없음.
 */
public class boj_1865 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Edge {
		int end;
		int weight;

		Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}

	static int N, M, W; // 지점 개수 N, 도로 개수 M, 웜홀 개수 W
	static ArrayList<ArrayList<Edge>> list; // 인접리스트

	static int INF = 88888888;

	public static void main(String[] args) throws IOException {
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; ++tc) {
			// 입력
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			for (int i = 0; i <= N; i++) {
				list.add(new ArrayList<>());
			}

			for (int i = 0; i < M; ++i) {
				// 도로 정보
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				list.get(S).add(new Edge(E, T));
				list.get(E).add(new Edge(S, T));
			}
			for (int i = 0; i < W; ++i) {
				// 웜홀 정보
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				list.get(S).add(new Edge(E, -T));
			}
			// 풀이
			boolean ans = bellmanFord(1);
			// 출력
			if (ans)
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		bw.write(sb.toString());
		bw.flush();

	}

	private static boolean bellmanFord(int start) {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[start] = 0; // 시작위치

		// 1번 정점에서 다른 정점들까지의 최단 거리 구하기 (dist)
		for (int i = 1; i < N; ++i) { // V-1 회 탐색
			for (int j = 1; j <= N; ++j) {
				for (Edge e : list.get(j)) {
					if (dist[e.end] > dist[j] + e.weight) {
						dist[e.end] = dist[j] + e.weight;
					}
				}
			}
		}

		// 더 돌려서 업데이트 되면 음수 사이클 존재
		for (int i = 1; i <= N; i++) {
			for (Edge e : list.get(i)) {
				if (dist[e.end] > dist[i] + e.weight) {
					return true;
				}
			}
		}
		return false;
	}
}
