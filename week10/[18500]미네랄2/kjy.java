import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder(); 
	static int r, c, n;
	static int[] cmd;
	static char[][] area;
	static final int[] dx = {0, 0, 1, -1};
	static final int[] dy = {-1, 1, 0, 0};
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		area = new char[r][c];
		for(int i = 0;i < r; ++i) {
			String tmp = br.readLine();
			for(int j = 0;j < c; ++j) {
				area[i][j] = tmp.charAt(j);
			}
		}
		n = Integer.parseInt(br.readLine());
		cmd = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0;i < n; ++i) {
			cmd[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static boolean range(int x, int y) {
		return x >= 0 && y >= 0 && x < r && y < c;
	}
	
	static int throwStick(int idx) {
		//idx % 2 == 0이면 left
		int h = r - cmd[idx];
		if(idx % 2 == 0) {
			for(int i = 0;i < c; ++i) {
				if(area[h][i] == 'x') {
					area[h][i] = '.';
					return i;
				}
			}
		}
		//idx % 2 == 1이면 right
		else {
			for(int i = c - 1;i >= 0; --i) {
				if(area[h][i] == 'x') {
					area[h][i] = '.';
					return i;
				}
			}
		}
		return -1;
	}
	
	static Point mineralWillDown() {
		//
		Deque<Point> q = new ArrayDeque<>();
		boolean[][] vis = new boolean[r][c];
		for(int i = 0;i < r; ++i) {
			for(int j = 0;j < c; ++j) {
				boolean noProblem = false;
				if(area[i][j] == '.' || vis[i][j]) continue;
				vis[i][j] = true;
				q.add(new Point(i, j));
				while(!q.isEmpty()) {
					Point cur = q.poll();
					if(cur.x == r - 1) noProblem = true;
					for(int dir = 0;dir < 4; ++dir) {
						int nx = cur.x + dx[dir];
						int ny = cur.y + dy[dir];
						if(!range(nx, ny)) continue;
						if(area[nx][ny] == '.' || vis[nx][ny]) continue;
						vis[nx][ny] = true;
						q.add(new Point(nx, ny));
					}
				}
				if(!noProblem) return new Point(i, j);
			}
		}
		return null;
	}
	
	static void mineralDown(Point start) {
		char[][] copy = new char[r][c];
		for(int i = 0;i < r; ++i) {
			for(int j = 0;j < c; ++j) {
				copy[i][j] = area[i][j];
			}
		}
		//맵 전체를 복사하되,
		//낙하할 미네랄은 미리 위치를 비워둔다.
		List<Point> minerals = new ArrayList<>();
		boolean[][] vis = new boolean[r][c];
		Deque<Point> q = new ArrayDeque<>();
		q.add(start);
		vis[start.x][start.y] = true; 
		while(!q.isEmpty()) {
			Point cur = q.poll();
			minerals.add(cur);
			copy[cur.x][cur.y] = '.'; 
			for(int dir = 0;dir < 4; ++dir) {
				int nx = cur.x + dx[dir];
				int ny = cur.y + dy[dir];
				if(!range(nx, ny)) continue;
				if(copy[nx][ny] == '.' || vis[nx][ny]) continue;
				vis[nx][ny] = true;
				q.add(new Point(nx, ny));
			}
		}
		//한 칸 씩 밀어내도 문제 없지 않나..?
		int h = -1;
		for(int i = 0; i <= r - 1; ++i) {
			boolean canDown = true;
			for(Point nxt : minerals) {
				int next = nxt.x + i;
				if(!range(next, nxt.y) || copy[next][nxt.y] == 'x') {
					canDown = false;
					break;
				}
			}
			if(!canDown) {
				break;
			}
			h = i;
		}
		
		for(Point cur : minerals) {			
			copy[cur.x + h][cur.y]= 'x'; 
		}
		
		for(int i = 0;i < r; ++i) {
			for(int j = 0;j < c; ++j) {
				area[i][j] = copy[i][j];
			}
		}
	}

	static void solution() {
		for(int i = 0;i < n; ++i) {
			//1. 막대 던져서 미네랄 하나 박살
			if(throwStick(i) == -1) continue;
			//2. 낙하할 미네랄이 있는지 확인
			//끽해봐야 하나 낙하하는 정도일 테니, 하나만 찾으면 된다.
			Point start = mineralWillDown();
			//낙하할 미네랄이 없으면 당연히 다음 과정 안하겠지
			if(start == null) continue;
			//3. 미네랄 낙하
			mineralDown(start);
		}
		//print result
		for(int i = 0;i < r; ++i) {
			for(int j = 0;j < c; ++j) {
				sb.append(area[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
}
