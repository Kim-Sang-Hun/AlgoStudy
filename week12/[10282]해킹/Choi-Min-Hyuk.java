import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 왜 틀렸는지 해결 못함... 시험 끝나고 고쳐야지

public class BOJ10282_해킹_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();
	
	static int T, n, d, c;
	static int[] dist;
	static ArrayList<Node>[] list;
	static int count;
	static final int MAX_VALUE = 99999999;

	static class Node implements Comparable<Node> {
		int end, weight;

		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return weight - o.weight;
		}
	}

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int testcase = 0; testcase < T; testcase++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			dist = new int[n + 1];
			Arrays.fill(dist, MAX_VALUE);

			list = new ArrayList[n + 1];
			for (int i = 0; i <= n; i++)
				list[i] = new ArrayList<>();

			count = 1;

			PriorityQueue<Node> pq = new PriorityQueue<>();
			pq.offer(new Node(c, 0));
			dist[c] = 0;

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				list[b].add(new Node(a, s));
			}

			dijkstra(c);

			

			int wasted_computer = 0;
			int max = -1;
			for (int i = 1; i <= n; i++) {
				if (dist[i] == MAX_VALUE)
					continue;
				
				else {
					wasted_computer++;
					max = Math.max(max, dist[i]);
				}
			}

			sb.append(wasted_computer).append(" ").append(max).append(lineSeparator);
		}

	}

	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		boolean visits[] = new boolean[n + 1];
		dist[start] = 0;
		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {
			Node curnode = pq.poll();
			int next = curnode.end;
			if (visits[next])
				continue;
			visits[next] = true;
			for (Node node : list[next]) {
				if (dist[node.end] > dist[next] + node.weight) {
					dist[node.end] = dist[next] + node.weight;
					pq.add(new Node(node.end, dist[node.end]));
				}
			}
		}

	}
}
