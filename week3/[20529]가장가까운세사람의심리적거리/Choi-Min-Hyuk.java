import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static String lineSeparator = System.lineSeparator();
	static StringTokenizer st;
	static int T, N, min;
	
	public static void main(String[] args) throws Exception {
		T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			// 만약 사람이 33명이면 비둘기 집 원리에 의해 같은 MBTI인 사람이 3명 존재할 수 밖에 없다
			if (N > 32) {
				sb.append("0" + lineSeparator);
				continue;
			}
			
			// MBTI별 인원이 몇명인지 담는 배열
			int[] MBTINums = new int[16];
			// 입력된 MBTI를 MBTINum으로 치환해주고 넣는 배열
			int[] MBTIs = new int[N];
			boolean sameThreeMBTIExistence = false;
			
			for (int i = 0; i < N; i++) {
				String newMBTI = st.nextToken();
				// E: 0, I: 1 / N: 0, S: 1 / F: 0, T: 1 / P: 0, J: 1로 치환해 2진수로 계산
				int MBTINum =
						((newMBTI.charAt(0) == 'E' ? 0 : 1) << 3) +
						((newMBTI.charAt(1) == 'N' ? 0 : 1) << 2) +
						((newMBTI.charAt(2) == 'F' ? 0 : 1) << 1) +
						(newMBTI.charAt(3) == 'P' ? 0 : 1);
				
				MBTIs[i] = MBTINum;
				MBTINums[MBTINum]++;
				
				// 같은 MBTI가 3명 이상이면
				if (MBTINums[MBTINum] >= 3) {
					sameThreeMBTIExistence = true;
					break;
				}
			}
			
			// 같은 MBTI가 3명 이상이면
			if (sameThreeMBTIExistence) {
				sb.append("0" + lineSeparator);
				continue;
			}
            
			min = 12;
			int[] people = new int[3];
			getPsychologicalDistance(MBTIs, people, 0, 0);
			
			sb.append(min + lineSeparator);
		}
		
		System.out.println(sb);
	}
	
	/*
	 * 두 MBTI의 심리적 거리는 2진수로 치환한 두 수의 XOR연산 후 각 자리수를 더한 값
	 * 재귀로 3명을 고르고 값을 구함
	 */
	public static void getPsychologicalDistance(int[] MBTIs, int[] arr, int count, int start) {
		if (count == 3) {
			int result = 
					sumOfOnesInBinaryRepresentation(arr[0] ^ arr[1]) +
					sumOfOnesInBinaryRepresentation(arr[1] ^ arr[2]) +
					sumOfOnesInBinaryRepresentation(arr[2] ^ arr[0]);
			min = min > result ? result : min;
			return ;
		}

		for (int i = start; i < N; i++) {
			arr[count] = MBTIs[i];
			getPsychologicalDistance(MBTIs, arr, count + 1, i + 1);
		}
	}
	
	// 입력받은 수를 2진수로 변환했을 때 1의 개수를 세어 return 하는 함수
	public static int sumOfOnesInBinaryRepresentation(int num) {
		int sum = 0;
		while (num > 1) {
			sum += num % 2;
			num /= 2;
		}
		
		return sum + num;
	}
}
