package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN15565_귀여운라이언_김주희 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int cnt = 0;
		int answer = Integer.MAX_VALUE;
		
		for (int left = 1, right = 1; left < arr.length; left++) {
			while(right <= N && cnt < K) {
				if(arr[right] == 1) cnt += 1;
				
				right++;
			}
			
			if(cnt == K) answer = Math.min(answer, right - left);
			
			if(arr[left] == 1) cnt -= 1;
		}
		
		
		if(answer == Integer.MAX_VALUE) answer = -1;
		
		System.out.println(answer);

	}

}
