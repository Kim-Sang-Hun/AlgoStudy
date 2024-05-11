import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ20166_문자열지옥에빠진호석_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static String lineSeparator = System.lineSeparator();
	
	static int N, M, K;
	static char map[][];
	static int dx[] = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int dy[] = { 0, 0, -1, 1, -1, 1, -1, 1 };
	
	static Map<String, Integer> hm = new HashMap<String, Integer>();
	static String[] list;

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N + 1][M + 1];
		list = new String[K + 1];
		
		for (int i = 1; i <= N; i++) {
			String input = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = input.charAt(j - 1);
			}
		}
		
		for (int i = 1; i <= K; i++) {
			String input = br.readLine();
			list[i] = input;
			hm.put(input, 0);
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				String s = "";
				s += map[i][j];
				DFS(i, j, 1, s); // 좌표, 이동 횟수 (최대 5회) , 현재까지 만들어진 문자열
			}
		}
		
		for (int i = 1; i <= K; i++)
			sb.append(hm.get(list[i])).append(lineSeparator);
		
		System.out.print(sb);
	}

	static void DFS(int r, int c, int cnt, String s) {
		// 현재까지 만들어진 문자열이 신이 좋아하는 문자열인지 확인
		if (hm.containsKey(s))
			hm.put(s, hm.get(s) + 1); // 좋아하는 문자열이라면 경우의 수에 추가
		
		if (cnt < 5) {
			for (int i = 0; i <= 7; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];
				
				if (nr == N + 1)
					nr = 1; // 환형 처리
				
				if (nr == 0)
					nr = N;
				
				if (nc == 0)
					nc = M;
				
				if (nc == M + 1)
					nc = 1;
				
				DFS(nr, nc, cnt + 1, s + map[nr][nc]); // 좌표, 이동횟수, 현재까지 만들어진 문자열
			}
		}
	}
}
