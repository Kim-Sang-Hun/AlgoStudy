import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2230_수고르기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int A[];

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		A = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(A);
		System.out.print(getMinM());
	}

	private static int getMinM() {
		int point1 = 0;
		int min = Integer.MAX_VALUE;

		for (int point2 = 1; point2 <= N; point2++) {
			while (point1 + 1 <= N && A[point1] - A[point2] < M) {
				++point1;
			}
			
			if (A[point1] - A[point2] >= M) {
				min = Math.min(min, A[point1] - A[point2]);
			}
		}
		
		return min;
	}
}
