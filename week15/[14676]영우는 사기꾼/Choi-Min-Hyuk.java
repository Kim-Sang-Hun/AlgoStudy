import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14676_영우는사기꾼_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] indegree = new int[N + 1];
		int[] build = new int[N + 1];
		List<Integer>[] adj = new ArrayList[N + 1];

		for (int i = 0; i <= N; i++)
			adj[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			adj[x].add(y);
			indegree[y]++;
		}
		
		String answer = "King-God-Emperor";

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			// 건설
			if (x == 1) {
				//건설 불가능
				if (indegree[y] > 0) {
					answer = "Lier!";
					break;
				}
				
				else {
					build[y]++;
					
					if (build[y] == 1 && adj[y].size() > 0) {
						for (Integer next : adj[y]) {
							indegree[next]--;
						}
					}
				}
			}
			
			// 파괴
			else {
				// 건설한 적이 없는 건물
				if (build[y] == 0) {
					answer = "Lier!";
					break;
				}
				
				else {
					build[y]--;
					if (build[y] == 0 && adj[y].size() > 0) {
						for (Integer next : adj[y]) {
							indegree[next]++;
						}
					}
				}
			}
		}
		
		System.out.println(answer);
	}
}
