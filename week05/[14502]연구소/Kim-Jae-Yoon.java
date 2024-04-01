import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, minVirus = (int)1e9;
	static int [][]map;
	static int dx[] = new int[] {0,1,0,-1};
	static int dy[] = new int[] {1,0,-1,0};
	static class Point{
		int x, y;
		Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static void copyMap(int [][]a) {
		for(int i = 0;i < n; ++i) {
			for(int j = 0;j < m; ++j) {
				a[i][j] = map[i][j];
			}
		}
	}

  //백덤블링 돌리면서 임의의 벽 3개 설치된 상태에서의 최대 바이러스 전파 정도를 파악한다.
	static void bt(int a, int b, int c) {
		int tmp[][] = new int[n][m];
		boolean vis[][] = new boolean[n][m];
		copyMap(tmp);
		int virusCnt = 0;
		for(int i = 0;i < n; ++i) {
			for(int j = 0;j < m; ++j) {
				if(tmp[i][j] != 2 || vis[i][j]) continue;
				++virusCnt;
				Queue<Point> q = new ArrayDeque<>();
				q.add(new Point(i, j));
				vis[i][j] = true;
				while(!q.isEmpty()) {
					Point cur = q.poll();
					for(int dir = 0; dir < 4; ++dir) {
						int nx = cur.x + dx[dir];
						int ny = cur.y + dy[dir];
						if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
						if (vis[nx][ny] || tmp[nx][ny] == 1) continue;
						tmp[nx][ny] = 2;
						q.add(new Point(nx, ny));
						vis[nx][ny] = true;
						++virusCnt;
					}
				}
			}
		}
		if(virusCnt < minVirus) minVirus = virusCnt;
	}
	
	static void solution() throws IOException {
		int wall = 3;
		for(int i = 0;i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j < m; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) ++wall;
			}
		}

    //조합 없다! 3중 for문이다!
		for (int i = 0; i < n * m; ++i) {
			if (map[i / m][i % m] != 0) continue;
			for (int j = i + 1; j < n * m; ++j) {
				if (map[j / m][j % m] != 0) continue;
				for (int k = j + 1; k < n * m; ++k) {
					if (map[k / m][k % m] != 0) continue;
					map[i / m][i % m] = map[j / m][j % m] = map[k / m][k % m] = 1;
					bt(i, j, k);
					map[i / m][i % m] = map[j / m][j % m] = map[k / m][k % m] = 0;
				}
			}
		}
		System.out.println(n * m - (wall + minVirus));
	}
	
	public static void main(String[] args) throws Exception{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		solution();
	}
}
