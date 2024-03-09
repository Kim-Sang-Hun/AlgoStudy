import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN16234_인구이동_김주희 {
	static int N, L, R, map[][];
	static boolean visited[][], flag;
	static int dr[] = new int[] {1,0,-1,0};
	static int dc[] = new int[] {0,1,0,-1};
	
	private static void bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> change = new LinkedList<>(); // 인구이동될 칸들 저장
		
		q.add(new int[] {r,c});
		change.add(new int[] {r,c});
		visited[r][c] = true;
		int cnt = 1;
		int sum = map[r][c];
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int i = 0; i < 4; i++) {
				int nr = cur[0] + dr[i];
				int nc = cur[1] + dc[i];
				
				if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc]) continue;
				
				int diff = Math.abs(map[nr][nc] - map[cur[0]][cur[1]]);
				if(diff >= L && diff <= R) {
					q.add(new int[] {nr,nc}); // 다음 탐색
					change.add(new int[] {nr,nc}); // 인구 이동
					visited[nr][nc] = true;
					cnt++;
					sum += map[nr][nc];
				}
			}
		}
		
		if(change.size() > 1) {
			int value = sum/cnt;
			while(!change.isEmpty()) {
				int[] C = change.poll();
				map[C[0]][C[1]] = value;
			}
			
			flag = true;
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		flag = true;
		int answer = -1;
		
		while(flag) {
			answer++;
			flag = false;
			visited = new boolean[N][N];
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visited[i][j]) continue;
					
					bfs(i,j);
				}
			}
		}
		System.out.println(answer);
	}

}
