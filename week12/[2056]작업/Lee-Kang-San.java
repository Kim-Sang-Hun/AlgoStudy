package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <작업> G4
* 링크
* https://www.acmicpc.net/problem/2056
* 요약
* 
* 풀이
* 위상정렬
*/
public class boj_2056 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Node {
		int num;
		int cost;

		public Node(int num, int cost) {
			this.num = num;
			this.cost = cost;
		}
	}

	static int N; // 수행해야 하는 작업 개수 N
	static int[] cost;
	static int[] inCount;
	static int[] visit;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine().trim());
		cost = new int[N + 1];
		inCount = new int[N + 1];
		visit = new int[N + 1];
		list.add(new ArrayList<Integer>()); // i번째 작업의 사전 작업들
		adjList.add(new ArrayList<Integer>()); // 위상정렬용 그래프

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			// cost
			cost[i] = Integer.parseInt(st.nextToken());

			// number of prework
			inCount[i] = Integer.parseInt(st.nextToken());

			// prework list
			list.add(new ArrayList<Integer>());
			adjList.add(new ArrayList<Integer>());
			if (inCount[i] > 0) {
				for (int j = 0; j < inCount[i]; j++) {
					int pre = Integer.parseInt(st.nextToken());
					list.get(i).add(pre);
					adjList.get(pre).add(i);
				}
			}
		}
		
		// 풀이
		toposort();

		// 출력
		bw.write(visit[N]+"");
		bw.flush();
	}

	private static void toposort() {
		// init
		Queue<Node> q = new ArrayDeque<>();

		for (int i = 1; i <= N; i++) {
			if (inCount[i] == 0) {
				visit[i] = cost[i];
				q.offer(new Node(i, visit[i]));
			}
		}
//		System.out.println("==========");

		while (!q.isEmpty()) {
			Node cur = q.poll();
//			System.out.println("curent node : "+cur.num);
			for (int next : adjList.get(cur.num)) { // cur.num이 사전작업인 Node 들의 진입차수--
				inCount[next]--;
//				System.out.println("cur : "+cur.num+" -- next : "+next);
				if (inCount[next] == 0) { // 사전작업이 모두 완료됐다면 제일 오래 걸린 사전작업 찾기
//					System.out.println("next is 0 : "+next );
					int maxCost = -1;
					int maxCostNode = -1;
					for (int preWork : list.get(next)) { 
						if (visit[preWork] > maxCost) {
							maxCost = visit[preWork];
							maxCostNode = preWork;
						}
					}
//					System.out.println("가장 오래 걸린 사전 작업 : "+maxCostNode);
					visit[next] = visit[maxCostNode] + cost[next];
					q.offer(new Node(next, visit[maxCostNode] + cost[next]));
				}
			}
		}
		
		Arrays.sort(visit);
	}
}
