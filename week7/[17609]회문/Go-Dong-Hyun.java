package Algo_week6;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ17609 {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			
			char[] arr = br.readLine().toCharArray();
			int cnt = 0;
			int sub_cnt = 0;
			
			int left = 0;
			int right = arr.length-1;
			int sub_left = 0;
			int sub_right = right;
			
			boolean lflag = false, rflag = false;
			
			while (true) {
				if (left >= right) break;
				
				if (arr[left] == arr[right]) {
					left += 1;
					right -= 1;
					continue;
				}
				
				if (true) {	//오른쪽꺼 줄이는거
					rflag = true;
					sub_left = left;
					sub_right = right;
					
					cnt += 1;
					right -= 1;
					while (true) {
						if (left >= right) break;
						
						if (arr[left] != arr[right]) {
							cnt += 1;
							break;
						}
						left += 1;
						right -= 1;
					}
				}
				
				left = sub_left;
				right = sub_right;
				if (true) {	//왼쪽꺼
					lflag = true;
					sub_cnt += 1;
					left += 1;
					while (true) {
						if (left >= right) break;
						if (arr[left] != arr[right]) {
							sub_cnt +=1;
							break;
						}
						left += 1;
						right -= 1;
					}
				} 
				
				if (!lflag && !rflag) {
					cnt = 2;
					sub_cnt = 2;
				}
				break;
			}
			
			if (lflag && rflag) {
				System.out.println(Math.min(cnt, sub_cnt));
			} else if (lflag) {
				System.out.println(sub_cnt);
			} else {
				System.out.println(cnt);
			}
			
		}
		
	}
}
