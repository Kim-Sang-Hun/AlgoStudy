import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 투 포인터
public class BOJ15565_귀여운라이언_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] dolls = new int[N];
		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			dolls[i] = Integer.parseInt(st.nextToken());

			if (dolls[i] == 1) {
				list.add(i);
			}
		}

		if (list.size() < K) {
			System.out.println(-1);
			return;
		}

		int distance = Integer.MAX_VALUE;

		for (int start = 0; start < list.size(); start++) {
			int end = start;
			int cnt = 0;
			
			while (end < list.size() && cnt < K) {
				cnt++;
				end++;
			}

			if (cnt == K) {
				int left = list.get(start);
				int right = list.get(end - 1);
				distance = Math.min(distance, right - left + 1);
			}
		}

		System.out.println(distance);

	}
}
