package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Kim-Ju-Hee {
	static int N, K, coin[], answer[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		coin = new int[N];
		answer = new int[K + 1];

		for (int i = 0; i < N; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}

		answer[0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = coin[i]; j <= K; j++) {
				answer[j] += answer[j - coin[i]];
			}

		}
		System.out.println(answer[K]);

	}
}
