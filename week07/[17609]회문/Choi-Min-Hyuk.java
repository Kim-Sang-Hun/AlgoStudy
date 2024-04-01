import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ17609_회문_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();
	static int T;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		
		for (int testcase = 1; testcase <= T; testcase++) {
			String s = br.readLine();
			sb.append(getStringStatus(s, 0, s.length() - 1, 0)).append(lineSeparator);
		}
		
		System.out.print(sb);
	}
	
	private static int getStringStatus(String s, int start, int end, int status) {
		// 문자 삭제를 2번 하면 바로 return 2
		if (status >= 2) {
			return 2;
		}
		
		// start와 end가 만나거나 지나치기 전까지 반복
		while (start < end) {
			// 같을 경우 포인터 한 칸씩 이동 진행
			if (s.charAt(start) == s.charAt(end)) {
				start++;
				end--;
			} 
			
			// 문자가 달라 하나를 삭제해야 하는 경우, 앞을 없앨지 뒤를 없앨지 고려하고 더 작은 값 선택
			else
				return Math.min(getStringStatus(s, start + 1, end, status + 1), getStringStatus(s, start, end - 1, status + 1));
		}
		
		return status;
	}
}
