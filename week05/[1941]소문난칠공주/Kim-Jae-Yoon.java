import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int answer = 0;
	static final int r = 5;
	static char[][] area = new char[5][5];
	static int[] princess = new int[7];
	static final int[] dx = {0, 1, 0, -1};
	static final int[] dy = {1, 0, -1, 0};
	
	static void input() throws IOException{
		for(int i = 0;i < r; ++i) {
			String tmp = br.readLine();
			for(int j = 0;j < r; ++j) {
				area[i][j] = tmp.charAt(j);
			}
		}
	}

  //공주로 뽑힌 7명의 목록 중에 포함되어 있는지 확인하는 메서드
	static boolean isInRange(int cur) {
		for(int i = 0;i < 7; ++i) {
			if(princess[i] == cur) return true;
		}
		return false;
	}

  //7명을 고른 후 조건에 부합하는지 확인한다.
  //I. 7명이 모두 연결되어 있는가
  //II. 4명 이상이 S로 구성되어 있는가
	static void bfs() {
		boolean[] vis = new boolean[25];
		Deque<Integer> q = new ArrayDeque<>();
		q.add(princess[0]);
		vis[princess[0]] = true;
		int connect = 1, s = area[princess[0] / 5][princess[0] % 5] == 'S' ? 1 : 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int dir = 0;dir < 4; ++dir) {
				int nx = cur / 5 + dx[dir];
				int ny = cur % 5 + dy[dir];
				int nxt = nx * 5 + ny;
				if(nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
				if(vis[nxt]) continue;
				if(!isInRange(nxt)) continue;
				++connect;
				if(area[nx][ny] == 'S') ++s;
				q.add(nxt);
				vis[nxt] = true;
			}
		}
		if(connect == 7 && s >= 4) ++answer;
	}

  //7명을 선택하고 연결되어 있는지 여부를 bfs 메서드로 체크한다.
	static void permutation(int depth, int index) {
		if(depth == 7) {
			bfs();
			return;
		}
		for(int i = index; i < 25; ++i) {
			princess[depth] = i;
			permutation(depth + 1, i + 1);
		}
	}
	
	static void solution() {
		permutation(0, 0);
		System.out.println(answer);
	}
	
	
	public static void main(String[] args) throws IOException{
		input();
		solution();
	}
}
