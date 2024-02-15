package Algo_week03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ16472 {

	//ㅈ냥이 문제
	//while문 돌리면서 set에 언어가 포함되어 있으면 cnt+=1
	//안되어있으면 left인덱스 옮겨가면서 cnt다시설정
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] arr = br.readLine().toCharArray();
		Set<Character> set = new HashSet<Character>();
		
		int left = 0;
		int right = N-1;
		int cnt = N;
		int ans = 0;
		int len = arr.length;
		
		for (int i = 0; i < right; i++) {
			set.add(arr[i]);
		}
		
		while (true) {
			ans = Math.max(ans, cnt);
			if (right == len-1)
				break;
			
			right += 1;
			
			if (set.contains(arr[right])) {
				cnt += 1;
			} else if(set.size() < N-1) {
				cnt += 1;
				set.add(arr[right]);
			} else {
				left += 1;
				set = new HashSet<Character>();
				cnt = 0;
				
				for (int i = right; i >= left ; i--) {
					if (set.size() == N && !set.contains(arr[i])) {
						left = i;
						break;
					}
					set.add(arr[i]);
					cnt += 1;
				}
			}
			
		}
		System.out.println(ans);
	}
}
