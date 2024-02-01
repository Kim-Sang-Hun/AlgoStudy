

import java.util.Scanner;

public class SW5215 {
	static int[] grade;
	static int[] cal;
	static int ans;
	static int L;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			L = sc.nextInt();
			
			grade = new int[N];
			cal = new int[N];
			
			for (int i = 0; i < N; i++) {
				int g = sc.nextInt();
				int c = sc.nextInt();
				
				grade[i] = g;
				cal[i] = c;
			}
			
			ans = 0;
			combination(0, 0, 0);
//			System.out.println(ans);
			System.out.println("#"+t+ " " + ans);
			
		}
	}

	private static void combination(int idx, int max_cal, int max_grade) {
		if (max_cal > L) {
			return;
		}
		
		if (max_grade > ans) {
			ans = max_grade;
		}
		
		if (idx == N) {
			return;
		}
		
		combination(idx+1,cal[idx] + max_cal, max_grade + grade[idx]);
		combination(idx+1,max_cal, max_grade);
		
		
	}	
}
