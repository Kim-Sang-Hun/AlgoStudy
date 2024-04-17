import java.io.*;
import java.util.*;

/*
* 제목
* <해킹> G4
* 링크
* https://www.acmicpc.net/problem/10282
* 요약
* a가 b를 의존 == b에서 a로 가는 경로 존재
* 풀이
* 다익스트라
*/
public class boj_10282 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Node {
		int num;
		int weight;

		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", weight=" + weight + "]";
		}
	}

	static ArrayList<ArrayList<Node>> 인접리스트;

	static int 컴퓨터개수, 의존성개수, 해킹당한컴퓨터;
	static int 총감염되는컴퓨터수, 마지막컴퓨터가감염되기까지걸리는시간;

	public static void main(String[] args) throws IOException {
		int 테스트케이스개수 = Integer.parseInt(br.readLine().trim());
		for (int tc = 0; tc < 테스트케이스개수; tc++) {
			// 입력
			입력();

			// 플이
			총감염되는컴퓨터수 = 0;
			마지막컴퓨터가감염되기까지걸리는시간 = 0;
			풀이();

			// 출력
			sb.append(총감염되는컴퓨터수).append(" ").append(마지막컴퓨터가감염되기까지걸리는시간).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void 풀이() {
		int[] dist = new int[컴퓨터개수 + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.weight - o2.weight;
			}
		});

		dist[해킹당한컴퓨터] = 0;
		pq.offer(new Node(해킹당한컴퓨터, dist[해킹당한컴퓨터]));

		while (!pq.isEmpty()) {
			Node 현재노드 = pq.poll();
			int 현재노드번호 = 현재노드.num;
			int 현재노드까지거리 = 현재노드.weight;

			for (Node node : 인접리스트.get(현재노드번호)) {
				if (dist[node.num] > node.weight + 현재노드까지거리) {
					dist[node.num] = node.weight + 현재노드까지거리;
					pq.offer(new Node(node.num, dist[node.num]));
				}
			}
		}
		
		for(int 거리 : dist) {
			if(거리!=Integer.MAX_VALUE) {
				총감염되는컴퓨터수++;
				마지막컴퓨터가감염되기까지걸리는시간 = Math.max(마지막컴퓨터가감염되기까지걸리는시간, 거리);
			}
		}
	}

	private static void 입력() throws IOException {
		st = new StringTokenizer(br.readLine().trim());
		컴퓨터개수 = Integer.parseInt(st.nextToken());
		의존성개수 = Integer.parseInt(st.nextToken());
		해킹당한컴퓨터 = Integer.parseInt(st.nextToken());

		인접리스트 = new ArrayList<>();

		for (int i = 0; i <= 컴퓨터개수; i++) {
			인접리스트.add(new ArrayList<Node>());
		}

		for (int i = 0; i < 의존성개수; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			인접리스트.get(b).add(new Node(a, s));
		}
	}
}
