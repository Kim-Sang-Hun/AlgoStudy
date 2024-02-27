import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// dp를 이용해서 풀어주었다.
// 방향은 오직 아래와 오른쪽만 반영해주면 된다.
// 값이 매우 커서 long을 써야만 오류가 나지 않는다.
public class JUN1890_점프 {
	
	static int N;
	static long[][] dp; 
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		dp = new long[N][N];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}
		System.out.println(makeDP(0, 0));
	}

	public static long makeDP(int i, int j) {
		// dp에 값이 있으면 dp값을 리턴
		// 끝 지점에 도착하면 1을 리턴
		// 맵의 값이 0이면 0을 리턴
		if (dp[i][j] != 0) return dp[i][j];
		if (i == N-1 && j == N-1) return 1;
		if (map[i][j] == 0) return 0;
		long sum = 0;
		if (i + map[i][j] < N) {
			sum += makeDP(i + map[i][j], j);
		}
		if (j + map[i][j] < N) {
			sum += makeDP(i, j + map[i][j]);
		}
		return dp[i][j] = sum;
	}
}
