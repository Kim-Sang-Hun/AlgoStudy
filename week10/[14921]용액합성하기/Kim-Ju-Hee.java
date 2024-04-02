package april1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN14921_용액합성하기_김주희 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = N-1;
		int answer = Integer.MAX_VALUE;
		
		while(right > left) {
			int value = arr[right] + arr[left];
			
			if(Math.abs(value) < Math.abs(answer))
				answer = value;
			
			if(value > 0) right--;
			else if(value < 0) left++;
			else break;

		}
		
		System.out.println(answer);
	}

}
