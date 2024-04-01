package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN20529 {
	static String[] students;
	static int N;
	static int answer;
		
	private static int calDist(String m1, String m2) {
		int dist = 0;
		
		for (int i = 0; i < 4; i++) {
			if(m1.charAt(i) != m2.charAt(i))
				dist++;
		}
		return dist;
	}
	
	private static void combi(int depth, int start, String[] combi) {
		if(depth == 3) {
			answer = Math.min(answer, calDist(combi[0],combi[1]) + calDist(combi[1],combi[2])+ calDist(combi[0],combi[2]));
			return;
		}
	
		for (int i = start; i < N; i++) {
			combi[depth] = students[i];
			combi(depth + 1, i + 1, combi);
		}
		return;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int testCase = 0; testCase < T; testCase++) {
			N = Integer.parseInt(br.readLine());
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			students = new String[N];
			answer = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				students[i] = st.nextToken();
			}
			
            if (N > 32){ // 비둘기
                answer = 0;
            } else{
                combi(0,0,new String[3]);
            }
			
			System.out.println(answer);
		}
	}
}
