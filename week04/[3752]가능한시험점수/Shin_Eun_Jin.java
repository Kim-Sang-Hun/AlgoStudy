import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Shin_Eun_Jin {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] scores = new int[N];
			boolean[] possibleScores;

			int sumScore = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				scores[i] = Integer.parseInt(st.nextToken());
				sumScore += scores[i];
			}

			possibleScores = new boolean[sumScore + 1];
			possibleScores[0] = true;

			for (int i = 0; i < N; i++) {
				for (int j = sumScore; j >= 0; j--) {
					if (possibleScores[j]) {
						possibleScores[j + scores[i]] = true;
					}
				}
			}

			int count = 0;
			for (boolean possibleScore : possibleScores) {
				if (possibleScore) {
					count++;
				}
			}

			System.out.println("#" + tc + " " + count);
		}
	}
}
