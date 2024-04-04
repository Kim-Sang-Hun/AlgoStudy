import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14921_용액합성하기 {
	static int N;
	static int[] nums;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		nums = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int front = 0;
		int rear = N - 1;
		int minSum = Integer.MAX_VALUE;
		while (front < rear) {

			int sum = nums[front] + nums[rear];
			if(Math.abs(minSum) > Math.abs(sum)) {
				minSum = sum;
			}

			if (sum < 0) {
				front++;
			} 
			else if (sum > 0) {
				rear--;
			} 
			else {
				break;
			}
		}
		
		System.out.println(minSum);

	}
}
