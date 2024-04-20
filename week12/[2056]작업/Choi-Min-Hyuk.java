import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ2056_작업_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] degree, time, maxTime;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		degree = new int[N];
		time = new int[N];
		maxTime = new int[N];

		for (int i = 0; i < N; i++)
			graph.add(new ArrayList<Integer>());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			degree[i] = Integer.parseInt(st.nextToken());

			if (degree[i] == 0)
				pq.add(i);

			for (int j = 0; j < degree[i]; j++) {
				int num = Integer.parseInt(st.nextToken());
				graph.get(num - 1).add(i);
			}
		}

		findTime();
		Arrays.sort(time);
		System.out.print(time[N - 1]);
	}

	public static void findTime() {
		while (!pq.isEmpty()) {
			int p = pq.poll();

			for (Integer i : graph.get(p)) {
				maxTime[i] = Math.max(maxTime[i], time[p]);
				degree[i] -= 1;

				if (degree[i] == 0) {
					time[i] += maxTime[i];
					pq.add(i);
				}
			}
		}
	}
}
