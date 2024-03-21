import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int N, M;
	static int[] map;
	static int[] diceCnt;
	static int minCnt = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 사다리의 수
		M = Integer.parseInt(st.nextToken()); // 뱀의 수
		map = new int[101];
		diceCnt = new int[101];

		for (int i = 1; i <= 100; i++) {
			map[i] = i;
			diceCnt[i] = Integer.MAX_VALUE;
		}

		// 사다리와 뱀의 정보 입력
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			map[to] = from;
		}

		bfs();
		System.out.println(minCnt);

	}

	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		queue.offer(new Node(1, 0));

		while (!queue.isEmpty()) {
			Node curNode = queue.poll();
			int curIdx = curNode.idx;
			int curCnt = curNode.cnt;

			if (curIdx == 100) {
				minCnt = curCnt;
				return;
			}

			for (int i = 1; i <= 6; i++) {
				int nextIdx = curIdx + i;
				int nextCnt = curCnt + 1;

				if (nextIdx > 100)
					break;

				if (diceCnt[map[nextIdx]] <= nextCnt)
					continue;

				diceCnt[map[nextIdx]] = nextCnt;
				queue.offer(new Node(map[nextIdx], curCnt + 1));
			}
		}

	}

	static class Node {
		int idx;
		int cnt;

		public Node(int idx, int cnt) {
			this.idx = idx;
			this.cnt = cnt;
		}
	}
}
