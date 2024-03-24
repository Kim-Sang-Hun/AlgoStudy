import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1932_정수삼각형_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;
	static int[][] triangles;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		triangles = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i + 1; j++) {
				triangles[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = N - 1; i > 0; i--) {
			for (int j = 0; j < i; j++)
				triangles[i - 1][j] += Math.max(triangles[i][j], triangles[i][j + 1]);
		}

		System.out.println(triangles[0][0]);
	}
}
