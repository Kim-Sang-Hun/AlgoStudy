import java.io.*;
import java.util.*;
/*
  Title: 문자열 지옥에 빠진 호석
  Tier: Gold 4
  Algorithm: DFS
  Constraint: 1 Second, 512MB
*/
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n, m, k;
	static char[][] area;
	static List<String> list;
	static Map<String, Integer> map;
	static final int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
	static final int[] dy = {1, 0, -1, 0, 1, -1, 1, -1};
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		area = new char[n][m];
		for(int i = 0;i < n; ++i) {
			String tmp = br.readLine();
			for(int j=0;j<m;++j) {
				area[i][j] = tmp.charAt(j);
			}
		}
		map = new HashMap<>();
		list = new ArrayList<>();
		for(int i = 0; i < k; ++i) {
			String tmp = br.readLine();
			list.add(tmp);
		}
	}
	
	static int convertX(int x) {
		if(x == -1) return n - 1;
		else if(x == n) return 0;
		return x;
	}
	
	static int convertY(int y) {
		if(y == -1) return m - 1;
		else if(y == m) return 0;
		return y;
	}
	
	static void dfs(int x, int y, int cnt, String str) {
		if(map.containsKey(str)) {
			map.put(str, map.get(str) + 1);
		} else {
			map.put(str, 1);
		}
		if(cnt == 6) return;
		for(int dir = 0;dir < 8;++dir) {
			int nx = convertX(x + dx[dir]);
			int ny = convertY(y + dy[dir]);
			dfs(nx, ny, cnt + 1, str + area[nx][ny]);
		}
	}
	
	static void solution() {
		for(int i = 0;i < n; ++i) {
			for(int j = 0;j < m; ++j) {
				dfs(i, j, 1, "" + area[i][j]);
			}
		}
		for(String s : list) {
			if(!map.containsKey(s)) sb.append(0).append('\n');
			else sb.append(map.get(s)).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	
}
