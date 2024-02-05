import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA1234_비밀번호_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String LineSeparator = System.lineSeparator();
	static StringTokenizer st;
	static int N;
	static char[] passwordNums;
	
	public static void main(String[] args) throws Exception {
		for (int testcase = 1; testcase <= 10; testcase++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			String inputString = st.nextToken();
			
			// 최종 비밀번호가 저장될 배열, stack처럼 사용
			passwordNums = new char[N];
			// 최종 비밀번호 문자열의 size
			int passwordNumsSize = 0;
			// 최종 비밀번호 문자열의 시작이 0이 있을 때 생략하기 위한 새로운 StringBuilder
			StringBuilder passwordString = new StringBuilder();
			
			/*
			 * 입력받은 문자열 길이만큼
			 * stack의 top과 같은 숫자가 나오지 않으면 stack에 push
			 * stack의 top과 같은 숫자가 나오면 둘 다 제거
			 * 반복
			 */
			for (int i = 0; i < N; i++) {
				if (passwordNumsSize == 0 || passwordNums[passwordNumsSize - 1] != inputString.charAt(i))
					passwordNums[passwordNumsSize++] = inputString.charAt(i);
				else
					passwordNumsSize--;
			}
			
			// 
			for (int i = 0; i < passwordNumsSize; i++) {
				passwordString.append(passwordNums[i] + "");
			}
			sb.append("#" + testcase + " " + Integer.parseInt(passwordString.toString()) + LineSeparator);
		}
		
		System.out.println(sb);
	}
}
