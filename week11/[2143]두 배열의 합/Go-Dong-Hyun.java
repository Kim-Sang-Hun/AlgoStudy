package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ2143 {
	
	static long T;
	static int N,M;
	static int[] arrA, arrB;
	static ArrayList<Integer> dpA, dpB;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Long.parseLong(br.readLine()); 
		
		N = Integer.parseInt(br.readLine());
		arrA = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		arrB = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}
		
		dpA = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			int cnt = 0;
			for (int j = i; j < N; j++) {
				cnt += arrA[j];
				dpA.add(cnt);
			}
		}
		
		dpB = new ArrayList<Integer>();
		for (int i = 0; i < M; i++) {
			int cnt = 0;
			for (int j = i; j < M; j++) {
				cnt += arrB[j];
				dpB.add(cnt);
			}
		}
		
		Collections.sort(dpA);
		Collections.sort(dpB);
		
		long cnt = 0;
		int left = 0;
		int right = dpB.size()-1;
		
		while (left < dpA.size() && right >= 0) {
			int sum = dpA.get(left) + dpB.get(right);
			
			//정렬했으니까 연속해서 값찾기
			if (sum == T) {
				int a = dpA.get(left);
				int b = dpB.get(right);
				long leftCnt = 0;
				long rightCnt = 0;
				
				while (left < dpA.size() && dpA.get(left) == a) {
					leftCnt++;
					left++;
				}
				
				while (right >= 0 && dpB.get(right) == b) {
					rightCnt++;
					right--;
				}
				cnt += leftCnt*rightCnt;
				
			} else if (sum < T) {
				left++;
			} else if (sum > T){
				right--;
			}
		}
		System.out.println(cnt);
	}
}
