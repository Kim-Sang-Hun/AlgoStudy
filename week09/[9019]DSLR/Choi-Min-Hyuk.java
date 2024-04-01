import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9019_DSLR_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();
	static int T, A, B;

	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			bfs();
		}

		System.out.print(sb);
	}

	private static void bfs() {
		String[] command = new String[10000]; // 각자의 index가 될 때 어떤 명령어를 나열해야 하는지 담기 위한 배열
		boolean[] visited = new boolean[10000];
		Queue<Integer> q = new LinkedList<>();
		visited[A] = true;
		command[A] = "";
		q.add(A);

		while (!q.isEmpty() && !visited[B]) {
			int now = q.poll();
			int D = (2 * now) % 10000;
			int S = (now == 0) ? 9999 : now - 1;
			int L = (now % 1000) * 10 + now / 1000;
			int R = (now % 10) * 1000 + now / 10;

			if (!visited[D]) {
				q.add(D);
				visited[D] = true;
				command[D] = command[now] + "D";
			}

			if (!visited[S]) {
				q.add(S);
				visited[S] = true;
				command[S] = command[now] + "S";
			}

			if (!visited[L]) {
				q.add(L);
				visited[L] = true;
				command[L] = command[now] + "L";
			}

			if (!visited[R]) {
				q.add(R);
				visited[R] = true;
				command[R] = command[now] + "R";
			}
		}

		sb.append(command[B]).append(lineSeparator);
	}
}
