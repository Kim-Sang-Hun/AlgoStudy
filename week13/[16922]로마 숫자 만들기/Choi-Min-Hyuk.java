public class BOJ16922_로마숫자만들기_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, count;
	static int[] arr = { 1, 5, 10, 50 };
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N * 50 + 1];
		 
		dfs(0, 0, 0);
		System.out.print(count);
	}

	private static void dfs(int depth, int index, int sum) {
		if (depth == N) {
			if (!visited[sum]) {
				count++;
				visited[sum] = true;
			}
			
			return;
		}
		
		for (int i = index; i < 4; i++) {
			dfs(depth + 1, i, sum + arr[i]);
		}
	}
}
