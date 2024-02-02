
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN14500 {
	static int N, M, ans;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx;
	static int[] dy;

	public static void main(String[] args) throws Exception {
		//전체적으로 탐색하는 dfs와 
		//뻐큐 탐색하는 dfs1,dfs2를 만들어서 했습니다.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		dx = new int[] {-1,0,1,0};
		dy = new int[] {0,-1,0,1};
		
		arr = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		ans = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				//기본 완탐
				visited[i][j] = true;
				dfs(i,j,1, arr[i][j]);
				visited[i][j] = false;
				
				//뻐큐
				dfs1(i,j,arr[i][j]);
				dfs2(i,j,arr[i][j]);
			}
		}
		
		System.out.println(ans);
		
	}

	private static void dfs2(int x, int y, int cnt) {
		for (int i = 1; i <= 2; i++) {
			int nx = x+i;
			if(in_range(nx,y)) {
				cnt += arr[nx][y];
			} else {
				return;
			}
		}
		
		int a = 0;
		int b = 0;
		
		if (in_range(x+1,y-1)) {
			a = arr[x+1][y-1];
		} 
		
		if(in_range(x+1,y+1)) {
			b = arr[x+1][y+1];
		}
		
		cnt += (a>b ? a : b);
		
		if (cnt > ans) {
			ans = cnt;
			return;
		}
	}

	private static void dfs1(int x, int y, int cnt) {
		for (int i = 1; i <= 2; i++) {
			int ny = y + i;
			if(in_range(x,ny)) {
				cnt += arr[x][ny];
			} else {
				return;
			}
		}
		
		int a = 0;
		int b = 0;
		
		if (in_range(x-1, y+1)) {
			a = arr[x-1][y+1];
		} 
		if(in_range(x+1,y+1)) {
			b = arr[x+1][y+1];
		} 
		
		cnt += (a>b ? a : b);
		
		if (cnt > ans) {
			ans = cnt;
			return;
		}
	}
	

	private static void dfs(int x, int y, int depth, int cnt) {
		if (depth == 4) {
			if (cnt > ans) {
				ans = cnt;
			}
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i]; 
			
			if (in_range(nx,ny) && !visited[nx][ny] ) {
				visited[nx][ny] = true;
				dfs(nx,ny,depth+1, cnt+arr[nx][ny]);
				visited[nx][ny] = false;
			}
		}
		return;
	}
	
	

	private static boolean in_range(int nx, int ny) {
		return 0 <= nx && nx < N && 0 <= ny && ny < M;
	}
}
