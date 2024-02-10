package ssafyAlgo.week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20529 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			if (N > 33) {
				System.out.println(0);
				continue;
			}
			
			String[] arr = new String[N];
			
			for (int i = 0; i < N; i++) {
				arr[i] = st.nextToken();
			}
			
			int cnt = 9999999;
			
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					for (int k = j+1; k < N; k++) {
						int sub_cnt = 0;
						for (int idx = 0; idx < 4; idx++) {
							if (arr[i].charAt(idx) != arr[j].charAt(idx)) sub_cnt += 1;
							if (arr[j].charAt(idx) != arr[k].charAt(idx)) sub_cnt += 1;
							if (arr[k].charAt(idx) != arr[i].charAt(idx)) sub_cnt += 1;
						}
						cnt = Math.min(cnt, sub_cnt);
					}
				}
			}
			System.out.println(cnt);
		}
	}

}
