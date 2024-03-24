import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16928_뱀과사다리게임_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int M;
	static int[] ladder = new int[101];
	static int[] snake = new int[101];
	static boolean[] bl = new boolean[101];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int before = Integer.parseInt(st.nextToken());
			int after = Integer.parseInt(st.nextToken());

			if (i < N)
				ladder[before] = after;
			else
				snake[before] = after;
		}

		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);

		int cnt = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			cnt++;

			for (int i = 0; i < size; i++) {
				int now = queue.poll();
				for (int j = 1; j <= 6; j++) {
					int current = now + j;
					if (current > 100)
						continue;
					if (ladder[current] > 0) {
						current = ladder[current];
					} else if (snake[current] > 0) {
						current = snake[current];
					}
					if (bl[current])
						continue;

					if (current == 100) {
						System.out.println(cnt);
						return;
					}
					bl[current] = true;
					queue.add(current);
				}
			}
		}
	}
}
