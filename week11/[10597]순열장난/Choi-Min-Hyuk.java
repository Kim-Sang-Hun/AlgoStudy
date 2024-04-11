import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class BOJ10597_순열장난_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean selected[];
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] digits, results;
	
	public static void main(String[] args) throws Exception {
		String s = br.readLine();
		int sLength = s.length();
		
		if (sLength < 10) {
			for (char c : s.toCharArray()) {
				sb.append(c).append(" ");
			}
			
			System.out.print(sb);
			return ;
		}
		
		digits = Stream.of(s.split("")).mapToInt(Integer::parseInt).toArray();
		N = (sLength - 9) / 2 + 9;
		selected = new boolean[N + 1];
		selected[0] = true;
		results = new int[N];
		
		printKriii(0, 0);
	}
	
	public static boolean printKriii(int count, int index) {
		if (count == N) {
			if (index != digits.length)
				return false;
			
			for (int i = 0; i < N; i++) {
				sb.append(results[i]).append(" ");
			}
			System.out.print(sb);
			
			return true;
		}
		
		
		boolean status = false;
		
		if (!selected[digits[index]]) {
			selected[digits[index]] = true;
			results[count] = digits[index];
			status = printKriii(count + 1, index + 1);
			selected[digits[index]] = false;
		}
		
		if (status)
			return true;
		
		if (index == digits.length - 1)
			return false;
		
		int num = digits[index] * 10 + digits[index + 1];
		if (num <= N && !selected[num]) {
			selected[num] = true;
			results[count] = num;
			status = printKriii(count + 1, index + 2);
			selected[num] = false;
		}
		
		return status;
	}
}
