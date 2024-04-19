import java.util.*;
import java.io.*;

public class JUN16724_피리부는사나이_김주희 {
	static int N, M, answer, parent[];
	static Square[][] map;
	static Set<Integer> safeZone;
	
	static class Square{
		int dr, dc, idx;
		boolean visited;
		
		public Square(int dr, int dc) {
			this.dr = dr;
			this.dc = dc;
		}
	}
	
	private static void makeParent() {
		parent = new int[N*M];
		
		for (int i = 0; i < N*M; i++) {
			parent[i] = i;
		}
	}
	
	private static void union(int i1, int i2) {
		int parent1 = find(i1);
		int parent2 = find(i2);
		
		if(parent1 != parent2) parent[i2] = parent1;
	}
	
	private static int find(int idx) {
		if(parent[idx] == idx) return idx;
		return parent[idx] = find(parent[idx]);
	}
	
	private static void dfs(int r, int c, int curP) {
		int nr = r + map[r][c].dr;
		int nc = c + map[r][c].dc;

		int nextParent = find(nc + nr*M);
		
		if(!map[nr][nc].visited) { // union
			union(nc+nr*M,c+r*M);
			map[nr][nc].visited = true;
			dfs(nr, nc, curP);
		}
		else if(nextParent == parent[c+r*M]) {
			return;
		}else { // 다른 부모 만나면
			union(nc+nr*M,c+r*M);
			return;
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new Square[N][M];
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				char dir = input.charAt(j);
				if(dir == 'U') map[i][j] = new Square(-1,0);
				else if(dir == 'D') map[i][j] = new Square(1,0);
				else if(dir == 'L') map[i][j] = new Square(0,-1);
				else map[i][j] = new Square(0,1);
			}
		}
		
		makeParent();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!map[i][j].visited) {
					map[i][j].visited = true;
					
					dfs(i,j,parent[j+i*M]);
				}
			}
		}
		
		safeZone = new HashSet<Integer>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				safeZone.add(find(j+i*M));
			}
		}

		System.out.println(safeZone.size());

	}

}
