import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14921_용액합성하기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, A[];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		A = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		int left = 0;
		int right = N - 1;
		int min = Integer.MAX_VALUE;
		
		while (true) {
			if (left == right)
				break;
			
			int sum = A[left] + A[right];
			if (Math.abs(sum) <= Math.abs(min))
				min = sum;
			
			if (sum < 0)
				left++;
			else
				right--;
		}

		System.out.print(min);
	}
}
