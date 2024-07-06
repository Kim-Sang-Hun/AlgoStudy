package week19;

import java.io.*;

public class BOJ2448 {
	
	static String[] star;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		star = new String[N];
		star[0] = "  *  ";
		star[1] = " * * ";
		star[2] = "*****";
		
		for (int i = 1; 3 * Math.pow(2, i) <= N ; i++) {
			writeStar(i);
		}
		
		for (int i = 0; i < N; i++) {
			System.out.println(star[i]);
		}
		
	}

	private static void writeStar(int idx) {
		
		int bot = (int) (3 * Math.pow(2, idx));
		int mid = bot/2;
		
		for (int i = mid; i < bot; i++) {
			star[i] = star[i-mid] + " " + star[i-mid];
		}
		
		String blank = " ".repeat(mid);
		
		for (int i = 0; i < mid; i++) {
			star[i] = blank + star[i] + blank;
		}
		
	}
}
