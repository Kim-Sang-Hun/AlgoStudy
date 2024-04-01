import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ9935_문자열_폭발_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();

	public static void main(String[] args) throws Exception {
		char[] str = br.readLine().toCharArray();
		char[] bombStr = br.readLine().toCharArray();
		
		// pointer로 문자열의 앞부터 탐색, stack으로 치면 top index이므로 결국 남은 문자열의 길이와 같음
		int pointer = 0;
		int bombStrLength = bombStr.length;
		int strLength = str.length;
		char[] newStr = new char[strLength];
		
		for (int i = 0; i < strLength; i++) {
			newStr[pointer] = str[i];
			
			// 만약 포인터가 보고 있는 문자와 폭발 문자열의 끝 문자가 같은 경우 거꾸로 하나씩 확인하면서 폭발 문자열인지 확인
			if (newStr[pointer] == bombStr[bombStrLength - 1] && pointer + 1 >= bombStrLength) {
				boolean checkBombStr = true;
				
				for (int j = 1; j < bombStrLength; j++) {
					if (newStr[pointer - j] == bombStr[bombStrLength - j - 1])
						continue;
					
					else {
						checkBombStr = false;
						break;
					}
				}
				
				// 폭발 문자열이면 pointer 위치만 변경
				if (checkBombStr) {
					pointer -= bombStrLength;
				}
			}

			pointer++;
		}
		
		if (pointer == 0) {
			System.out.print("FRULA");
		} else {
			for (int i = 0; i < pointer; i++) {
				sb.append(newStr[i]);
			}
			System.out.print(sb);
		}
	}
}
