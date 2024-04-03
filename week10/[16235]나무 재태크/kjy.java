import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder(); 
	static int n, m, k;
	static int[][] a;
	static final int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	static class Tree{
		List<Integer> alive;
		List<Integer> dead;
		int feed;
		Tree(int feed){
			alive = new ArrayList<>();
			dead = new ArrayList<>();
			this.feed = feed;
		}
	}
	static Tree[][] tree;
	
	
	static void input() throws IOException{
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		a = new int[n][n];
		tree = new Tree[n][n];
		for(int i = 0;i < n; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0;j < n; ++j) {
				a[i][j] = Integer.parseInt(st.nextToken());
				tree[i][j] = new Tree(5);
			}
		}
		for(int i = 0;i < m; ++i) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int age = Integer.parseInt(st.nextToken());
			tree[x][y].alive.add(age);
		}
	}
	
	static boolean range(int x, int y) {
		return x >= 0 && y >= 0 && x < n && y < n;
	}
	
	static void spring() {
		for(int i = 0;i < n; ++i) {
			for(int j = 0;j < n; ++j) {
				if(tree[i][j].alive.isEmpty()) continue;
				Collections.sort(tree[i][j].alive);
				List<Integer> newly = new ArrayList<>();
				for(int nxt : tree[i][j].alive) {
					if(tree[i][j].feed < nxt) {
						tree[i][j].dead.add(nxt);
						continue;
					}
					tree[i][j].feed -= nxt;
					newly.add(nxt + 1);
				}
				tree[i][j].alive = newly;
			}
		}
	}
	
	static void summer() {
		for(int i = 0;i < n; ++i) {
			for(int j = 0;j < n; ++j) {
				if(tree[i][j].dead.isEmpty()) continue;
				for(int d : tree[i][j].dead) {
					tree[i][j].feed += (d >> 1);
				}
				tree[i][j].dead.clear();
			}
		}
	}
	
	static void autumn() {
		for(int i = 0;i < n; ++i) {
			for(int j = 0;j < n; ++j) {
				if(tree[i][j].alive.isEmpty()) continue;
				for(int spread : tree[i][j].alive) {
					if(spread % 5 != 0) continue;
					for(int dir = 0;dir < 8; ++dir) {
						int nx = i + dx[dir];
						int ny = j + dy[dir];
						if(!range(nx, ny)) continue;
						tree[nx][ny].alive.add(1);
					}
				}
			}
		}
	}
	
	static void winter() {
		for(int i = 0;i < n; ++i) {
			for(int j = 0;j < n; ++j) {
				tree[i][j].feed += a[i][j];
			}
		}
	}
	
	static void solution() {
		for(int i = 0;i < k; ++i) {
			spring();
			summer();
			autumn();
			winter();
		}
		int answer = 0;
		for(int i = 0;i < n; ++i) {
			for(int j = 0;j < n; ++j) {
				answer += tree[i][j].alive.size();
			}
		}
		System.out.println(answer);
	}
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
}
