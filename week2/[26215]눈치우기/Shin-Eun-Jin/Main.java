import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

		// 집의 수 입력
		int N = Integer.parseInt(br.readLine());

		// 집의 눈 높이 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int snow = Integer.parseInt(st.nextToken());
			pq.add(snow);
		}

		int time = 0;

		int home1, home2;
		while (!pq.isEmpty()) {
			if (pq.size() == 1) {
				home1 = pq.poll() - 1;
				if (home1 > 0) pq.add(home1);

			} 
			else {
				home1 = pq.poll() - 1;
				home2 = pq.poll() - 1;

				if (home1 > 0) pq.add(home1); 
				if (home2 > 0) pq.add(home2);
			}
			time++;
		}

		System.out.println(time > 1440 ? -1 : time);
	}
}
