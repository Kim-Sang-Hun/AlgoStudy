import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2412_암벽등반_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, T;
	static ArrayList<Integer>[] rockList;

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		rockList = new ArrayList[T + 1];
		for (int i = 0; i <= T; i++) {
			rockList[i] = new ArrayList<>();
		}

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			rockList[y].add(x);
		}

		for (int i = 0; i <= T; i++) {
			Collections.sort(rockList[i]);
		}

		System.out.print(bfs());
	}

	private static int bfs() {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0));
		int moveCount = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int s = 0; s < size; s++) {
				Node node = q.poll();
				int y = node.y;

				if (y == T)
					return moveCount;

				for (int ny = y - 2; ny <= y + 2; ny++) {
					if (ny < 0 || ny > T)
						continue;

					for (int j = 0; j < rockList[ny].size(); j++) {
						int x = node.x;
						int nx = rockList[ny].get(j);

						if (x + 2 < nx)
							break;

						else if (x - 2 > nx)
							continue;

						rockList[ny].remove(j);
						q.add(new Node(nx, ny));
						j--;
					}
				}
			}

			moveCount++;
		}

		return -1;
	}
}
