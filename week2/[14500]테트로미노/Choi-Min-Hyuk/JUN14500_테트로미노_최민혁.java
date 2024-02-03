import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN14500_테트로미노_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int nums[][];
	static boolean visited[][];
	static int[] dRow = { 0, 0, -1, 1 };
	static int[] dCol = { -1, 1, 0, 0 };
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// dfs로 모든 칸에서 탐색해보면서 4칸만 가기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(1, i, j, nums[i][j]);
				visited[i][j] = false;
			}
		}

		System.out.println(max);

	}

	public static void dfs(int count, int row, int col, int sum) {
		// 4칸 방문했으면 끝
		if (count == 4) {
			max = Math.max(max, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nRow = row + dRow[i];
			int nCol = col + dCol[i];

			if (nRow < 0 || N <= nRow || nCol < 0 || M <= nCol || visited[nRow][nCol])
				continue;

			visited[nRow][nCol] = true;

			/*
			 * 2번째 칸까지 탐색했으면 ㅗ, ㅜ, ㅓ, ㅏ의 경우를 따로 고려해주기
			 */
			if (count == 2) {
				dfs(count + 1, row, col, sum + nums[nRow][nCol]);
			}

			dfs(count + 1, nRow, nCol, sum + nums[nRow][nCol]);

			visited[nRow][nCol] = false;
		}

	}
}