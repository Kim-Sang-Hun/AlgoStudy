import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class 두배열의합 {
	static int T, n, m;
	static int[] arrA;
	static int[] arrB;
	static ArrayList<Integer> sumA;
	static ArrayList<Integer> sumB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		// A 배열 입력
		n = Integer.parseInt(br.readLine());
		arrA = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arrA[i] = Integer.parseInt(st.nextToken());
		}

		// B 배열 입력
		m = Integer.parseInt(br.readLine());
		arrB = new int[m];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < m; i++) {
			arrB[i] = Integer.parseInt(st.nextToken());
		}

		// A 배열 누적합 구하기
		sumA = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int sum = 0;
			for (int j = i; j < n; j++) {
				sum += arrA[j];
				sumA.add(sum);
			}
		}

		// B 배열 누적합 구하기
		sumB = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			int sum = 0;
			for (int j = i; j < m; j++) {
				sum += arrB[j];
				sumB.add(sum);
			}
		}

		// 정렬
		Collections.sort(sumA);
		Collections.sort(sumB);

		// 배열 쌍의 개수 구하기
		int pa = 0;
		int pb = sumB.size() - 1;
		long cnt = 0;
		while (pa < sumA.size() && pb >= 0) {
			int numA = sumA.get(pa);
			int numB = sumB.get(pb);

			int sum = numA + numB;

			if (sum == T) {
				long cntA = 0;
				while (pa < sumA.size() && sumA.get(pa) == numA) {
					pa++;
					cntA++;
				}

				long cntB = 0;
				while (pb >= 0 && sumB.get(pb) == numB) {
					pb--;
					cntB++;
				}

				cnt += cntA * cntB;
			} else if (sum < T) {
				pa++;
			} else if (sum > T) {
				pb--;
			}
		}

		System.out.println(cnt);

	}

}
