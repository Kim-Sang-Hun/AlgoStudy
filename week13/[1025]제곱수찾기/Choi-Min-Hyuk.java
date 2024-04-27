import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 처음에 문제 설명을 이해 못해서 풀이 보고 문제를 이해함
// https://dingdingmin-back-end-developer.tistory.com/entry/%EB%B0%B1%EC%A4%80-1025-%EC%9E%90%EB%B0%94-java-%EC%A0%9C%EA%B3%B1%EC%88%98-%EC%B0%BE%EA%B8%B0
// 위에 풀이 참고함

public class BOJ1025_제곱수찾기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int[][] arr;
	static int result = -1;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = s.charAt(j) - '0';
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int mi = -N; mi < N; mi++) {
					for (int mj = -M; mj < M; mj++) {
						if (mi == 0 && mj == 0) {
							continue;
						}

						int t = 0;
						int newI = i;
						int newJ = j;

						while (newI >= 0 && newI < N && newJ >= 0 && newJ < M) {
							// 기존에 담긴 숫자가 있다면 x10 해주고 더해줌
							t = 10 * t + arr[newI][newJ];

							// 완전 제곱수인지 판별
							if (Math.abs(Math.sqrt(t) - (int) Math.sqrt(t)) < 1e-10)
								result = Math.max(result, t);

							newI += mi;
							newJ += mj;
						}
					}
				}
			}
		}

		System.out.print(result);
	}
}
