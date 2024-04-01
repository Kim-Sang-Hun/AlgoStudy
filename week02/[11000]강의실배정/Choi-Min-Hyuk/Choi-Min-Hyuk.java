import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ11000_강의실배정_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final String lineSeperator = System.lineSeparator();
	static StringTokenizer st;
	static int N;
	static int[][] times;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		times = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			times[i][0] = Integer.parseInt(st.nextToken());
			times[i][1] = Integer.parseInt(st.nextToken());
		}

		// 수업 시작 시간 순서대로 정렬하는데 시작시간이 같으면 끝나는 시간이 빠른 순서대로 정렬
		Arrays.sort(times, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				if (o1[0] == o2[0])
					return o1[1] - o2[1];
				return o1[0] - o2[0];
			}
		});

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		pq.add(times[0][1]);

		for (int i = 1; i < N; i++) {
			// 다음 수업이 가장 먼저 끝나는 수업의 강의실을 사용할 수 있으면 대체
			if (pq.peek() <= times[i][0])
				pq.poll();

			pq.add(times[i][1]);
		}

		System.out.println(pq.size());
	}
}
