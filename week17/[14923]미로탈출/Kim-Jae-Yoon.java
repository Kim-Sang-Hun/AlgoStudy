import java.io.*;
import java.util.*;
//Info class 생성 후 거리, 벽뚫 횟수, 위치를 고려하는 것으로 문제를 풀이했습니다.
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n, m, hx, hy, ex, ey;
	static int[][] a;
	static boolean[][][] vis;
	static final int[] dx = {0, 1, 0, -1};
	static final int[] dy = {1, 0, -1, 0};
	
    static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
    	hx = Integer.parseInt(st.nextToken()) - 1;
    	hy = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
    	ex = Integer.parseInt(st.nextToken()) - 1;
    	ey = Integer.parseInt(st.nextToken()) - 1;
    	a = new int[n][m];
    	vis = new boolean[n][m][2];
    	for(int i = 0;i < n; ++i) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0;j < m; ++j) {
       			a[i][j] = Integer.parseInt(st.nextToken());    			
    		}
    	}
   	}
    
    static class Info{
    	int x, y, cnt, dist;
    	Info(int x, int y, int cnt, int dist){
    		this.x = x;
    		this.y= y;
    		this.cnt = cnt;
    		this.dist = dist;
    	}
    }

    static void solution() {
    	Deque<Info> q = new ArrayDeque<>();
    	q.add(new Info(hx, hy, 0, 0));
    	while(!q.isEmpty()) {
    		Info cur = q.poll();
    		if(cur.x == ex && cur.y == ey) {
    			System.out.println(cur.dist);
    			return;
    		}
    		for(int dir = 0; dir < 4; ++dir) {
    			int nx = cur.x + dx[dir];
    			int ny = cur.y + dy[dir];
    			if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
    			if(a[nx][ny] == 0 && !vis[nx][ny][cur.cnt]) {
    				vis[nx][ny][cur.cnt] = true;
    				q.add(new Info(nx, ny, cur.cnt, cur.dist + 1));
    			} else if(a[nx][ny] == 1 && cur.cnt == 0) {
    				vis[nx][ny][1] = true;
    				q.add(new Info(nx, ny, 1, cur.dist + 1));
    			}
    		}
    	}
    	System.out.println(-1);
    }
    
    public static void main(String[] args) throws IOException {
   		input();
   		solution();
    }
}
