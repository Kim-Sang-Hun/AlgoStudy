import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW1225_암호생성기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final String lineSeperator = System.lineSeparator();
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int passwordSize = 8;
	static int[] passwords = new int[passwordSize];

	public static void main(String[] args) throws Exception {
		for (int testcase = 1; testcase <= 10; testcase++) {
			testcase = Integer.parseInt(br.readLine());

			/*
			 * 1~5를 반복적으로 감소하고 뒤로 보내는 과정을 8번 반복하면 각 자리는 유지된 채 모두 15씩 감소한다. 따라서 입력받을 때 15로
			 * 나누어 값이 가장 적은 몫을 구하고 그 몫 * 15를 뺀 후에 문제에서 제시한 과정을 반복하면 된다.
			 */
			int minQuotient = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				passwords[i] = Integer.parseInt(st.nextToken());
				minQuotient = minQuotient > passwords[i] / 15 ? passwords[i] / 15 : minQuotient;
			}

			if (minQuotient != 0) {
				for (int i = 0; i < 8; i++) {
					passwords[i] -= 15 * minQuotient;
				}
			}

			// frontIndex라는 값으로 수를 하나씩 뒤를 보내줄 때 passwords 배열의 어디가 맨 앞의 요소인지 알 수 있게 한다.
			int frontIndex = 0;
			while (true) {
				if (passwords[frontIndex % 8] - (frontIndex % 5 + 1) <= 0) {
					passwords[frontIndex % 8] = 0;
					frontIndex++;
					break;
				} else
					passwords[frontIndex % 8] -= frontIndex % 5 + 1;

				frontIndex++;
			}

			sb.append("#" + testcase + " ");
			for (int i = 0; i < 8; i++) {
				sb.append(passwords[(frontIndex + i) % 8] + " ");
			}
			sb.append(lineSeperator);
		}

		System.out.println(sb);
	}
}