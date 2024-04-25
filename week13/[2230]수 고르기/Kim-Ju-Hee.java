import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN2230_수고르기_김주희 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		long M = Integer.parseInt(st.nextToken());
		
		long[] arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine()); // parseLong......
		}
		
		Arrays.sort(arr);
		
		long answer = Long.MAX_VALUE;
		// 원래 arr[N-1]로 초기화했었는데 0 ≤ |A[i]| ≤ 1,000,000,000 라서 그러면 틀림.
		for (int i = 0; i < N; i++) {
			int p1 = i - 1;
			int p2 = i + 1;
			
			while(p1 >= 0) {
				if(arr[i] - arr[p1] >= M) {
					answer = Math.min(answer, arr[i] - arr[p1]);
					break;
				}
				p1--;
			}
			
			if(answer == M) break;
			
			while(p2 <= N-1) {
				if(arr[p2] - arr[i] >= M) {
					answer = Math.min(answer, arr[p2] - arr[i]);
					break;
				}
				p2++;
			}
			
			if(answer == M) break;
		}
		
		System.out.println(answer);

	}

}
