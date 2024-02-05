import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ26215_눈치우기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[] snows;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		snows = new int[N];
		st = new StringTokenizer(br.readLine());
		int max = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			snows[i] = Integer.parseInt(st.nextToken());
			sum += snows[i];
			max = max < snows[i] ? snows[i] : max;
		}
		
		// 두 집에 쌓인 눈을 1씩 같이 치우는 게 더 빠르니까 같이 치우는데 합이 홀수면 따로 한 번 더 걸린다.
		int result = sum / 2 + sum % 2;

		// 만약 가장 많이 쌓인 눈이 모든 눈의 반 이상일 경우, 그럼 나머지 집에 쌓인 눈이랑 관계 없이 그거 치우는 만큼 걸린다.
		if (max * 2 > sum) {
			result = max;
		}

		if (result > 1440) {
			System.out.println("-1");
		} else {
			System.out.println(result);
		}
	}
}
