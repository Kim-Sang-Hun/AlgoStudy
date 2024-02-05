import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA5215_햄버거다이어트_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final String lineSeperator = System.lineSeparator();
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, L, answer;
	static int[] tasteScores, calories;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			tasteScores = new int[N];
			calories = new int[N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				tasteScores[i] = Integer.parseInt(st.nextToken());
				calories[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;
			eatHamburger(0, 0, 0);
			
			sb.append("#" + testcase + " " + answer + lineSeperator);
		}
		
		System.out.println(sb);
	}
	
	// 부분집합으로 선형적으로 순회하면서 해당재료를 포함할 것인지 하지 않을 것인지에 따라 모든 경우 체크
	public static void eatHamburger(int index, int totalTasteScore, int totalCalorie) {
		if (totalCalorie > L)
			return;
		
		if (index == N) {
			answer = answer < totalTasteScore ? totalTasteScore : answer;
			return;
		}
		
		// 다음 index의 재료를 포함하는 경우
		eatHamburger(index + 1, totalTasteScore + tasteScores[index], totalCalorie + calories[index]);
		// 다음 index의 재료를 포함하지 않는 경우
		eatHamburger(index + 1, totalTasteScore, totalCalorie);
	}
}
