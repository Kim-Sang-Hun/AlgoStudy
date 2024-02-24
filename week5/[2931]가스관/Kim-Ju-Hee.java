package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static HashMap<Character, Pipe> pipes;
	static int R, C, answerR, answerC;
	static char map[][], answerChar, recordMap[][];
	static boolean visited[][], flag;
	
	static class Pipe{
		char value;
		int nextLength; // 가스 흐르는 다음 {dr, dc}가 들어있는 배열
		int next[][];
		Set<Character> dir = new HashSet<>();
		
		public Pipe(char value, int nextLength, int[][] next) {
			this.value = value;
			this.nextLength = nextLength;
			this.next = next;
			makeMarkSet();
		}
		
		private void makeMarkSet() {
			for (int i = 0; i < nextLength; i++) {
				dir.add(changeToMark(next[i][0], next[i][1]));
			}
		}
	}
	
	private static void makePipes() {
		pipes = new HashMap<Character, Pipe>();
		pipes.put('|', new Pipe('|', 2, new int[][] {{1,0}, {-1,0}}) );
		pipes.put('-', new Pipe('-', 2, new int[][] {{0,1}, {0,-1}}));
		pipes.put('+', new Pipe('+', 4, new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}}));
		pipes.put('1', new Pipe('1', 2, new int[][] {{1,0}, {0,1}})); // 아래, 오른쪽
		pipes.put('2', new Pipe('2', 2, new int[][] {{-1,0}, {0,1}})); // 위, 오른쪽
		pipes.put('3', new Pipe('3', 2, new int[][] {{-1,0}, {0,-1}})); // 위, 왼쪽
		pipes.put('4', new Pipe('4', 2, new int[][] {{1,0}, {0,-1}})); // 아래, 왼쪽
		pipes.put('M', new Pipe('M', 4, new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}})); // 처음꺼도 사방
		pipes.put('Z', new Pipe('Z', 4, new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}})); // Z
		pipes.put('.', new Pipe('.', 4, new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}})); // .도 사방
	}
	
	private static char changeToMark(int r, int c) {
		if(r == 1 && c == 0) return 'D'; // 아래
		else if(r == -1 && c == 0) return 'U'; // 위
		else if(r == 0 && c == 1) return 'R'; // 오른쪽
		else if(r == 0 && c == -1) return 'L'; // 왼쪽
		else return '0';
	}
	
	private static void searchPipe() {
		Set<Character> tempDir = new HashSet<>();
		
		for (int j = 0; j < pipes.get('+').nextLength; j++) { // 다시 보면서 어떤 파이프인지 탐색
			int dr = pipes.get('+').next[j][0];
			int dc = pipes.get('+').next[j][1];
	
			int nnr = answerR - 1 + dr;
			int nnc = answerC - 1 + dc;
			
			if (nnr < 0 || nnr >= R || nnc < 0 || nnc >= C ) continue;
			
			// 방문했을 시, 방문해온 방향과 탐색 방향이 마주보면 저장
			// 방문 안 했을 시, 탐색 방향과 뚫려있는 방향이 마주보면 저장 (오-왼 / 위-아래)
			if(visited[nnr][nnc] && changeToMark(dr,dc) == 'R' && recordMap[nnr][nnc] == 'L')
				tempDir.add(changeToMark(dr, dc));
			else if(!visited[nnr][nnc] && map[nnr][nnc] != '.' &&
					pipes.get(map[nnr][nnc]).dir.contains('L') && changeToMark(dr, dc) == 'R')
				tempDir.add(changeToMark(dr, dc));
			
			if(visited[nnr][nnc] && changeToMark(dr,dc) == 'L' && recordMap[nnr][nnc] == 'R')
				tempDir.add(changeToMark(dr, dc));
			else if(!visited[nnr][nnc] && map[nnr][nnc] != '.' &&
					pipes.get(map[nnr][nnc]).dir.contains('R') && changeToMark(dr, dc) == 'L')
				tempDir.add(changeToMark(dr, dc));
			
			if(visited[nnr][nnc] && changeToMark(dr,dc) == 'U' && recordMap[nnr][nnc] == 'D')
				tempDir.add(changeToMark(dr, dc));
			else if(!visited[nnr][nnc] && map[nnr][nnc] != '.' &&
					pipes.get(map[nnr][nnc]).dir.contains('D') && changeToMark(dr, dc) == 'U')
				tempDir.add(changeToMark(dr, dc));
			
			if(visited[nnr][nnc] && changeToMark(dr,dc) == 'D' && recordMap[nnr][nnc] == 'U')
				tempDir.add(changeToMark(dr, dc));
			else if(!visited[nnr][nnc] && map[nnr][nnc] != '.' &&
					pipes.get(map[nnr][nnc]).dir.contains('U') && changeToMark(dr, dc) == 'D')
				tempDir.add(changeToMark(dr, dc));
		
		}
		
		for (char key : pipes.keySet()) {
			if(key == 'M' || key == '.' || key == 'Z') continue;

			if(pipes.get(key).dir.equals(tempDir)) {
				answerChar = pipes.get(key).value;
			}
		}
	}
	
	private static void dfs(int r, int c, int[] dr) {

		visited[r][c] = true;
		Pipe curPipe = pipes.get(map[r][c]);
		
		if (map[r][c] == '.') { // 내가 점이면
			
			for (int i = 0; i < pipes.get('+').nextLength; i++) { // 사방 볼거임
				int nr = r + pipes.get('+').next[i][0];
				int nc = c + pipes.get('+').next[i][1];
				
				if (nr < 0 || nr >= R || nc < 0 || nc >= C ) continue;
				
				// 다음꺼가 점이 아니고, 방문 안 했으면서, 탐색방향과 마주보게 뚫려있으면 끊어진 부분
				if(map[nr][nc] != '.' && !visited[nr][nc] && 
						pipes.get(map[nr][nc]).dir.contains('R') && changeToMark(nr-r, nc-c) == 'L') {
					answerR = r + 1;
					answerC = c + 1;
					recordMap[r - dr[0]][c - dr[1]] = changeToMark(dr[0], dr[1]); //탐색하던 방향 저장
				}
				else if(map[nr][nc] != '.' && !visited[nr][nc] && 
						pipes.get(map[nr][nc]).dir.contains('L') && changeToMark(nr-r, nc-c) == 'R') {
					answerR = r + 1;
					answerC = c + 1;
					recordMap[r - dr[0]][c - dr[1]] = changeToMark(dr[0], dr[1]);
				}
				else if(map[nr][nc] != '.' && !visited[nr][nc] && 
						pipes.get(map[nr][nc]).dir.contains('D') && changeToMark(nr-r, nc-c) == 'U') {
					answerR = r + 1;
					answerC = c + 1;
					recordMap[r - dr[0]][c - dr[1]] = changeToMark(dr[0], dr[1]);
				}
				else if(map[nr][nc] != '.' && !visited[nr][nc] && 
						pipes.get(map[nr][nc]).dir.contains('U') && changeToMark(nr-r, nc-c) == 'D') {
					answerR = r + 1;
					answerC = c + 1;
					recordMap[r - dr[0]][c - dr[1]] = changeToMark(dr[0], dr[1]);
				}
				
			}
			searchPipe();
			return;
		}

		for (int i = 0; i < curPipe.nextLength; i++) {
			int nr = r + curPipe.next[i][0];
			int nc = c + curPipe.next[i][1];
			
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc]) continue;
			
			recordMap[r - dr[0]][c - dr[1]] = changeToMark(dr[0], dr[1]); // 다음으로 들어갈 수 있을 때만 진행했던 방향 저장
			dfs(nr, nc, curPipe.next[i]);
			visited[nr][nc] = false;
		}
		

	}

	public static void main(String[] args) throws IOException {
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int startR = 0;
		int startC = 0;
		int destR = 0;
		int destC = 0;
		
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			String input = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = input.charAt(j);
				
				if(map[i][j] == 'M') {
					startR = i;
					startC = j;
				}else if(map[i][j] == 'Z') {
					destR = i;
					destC = j;
				}
			}
		}
		
		makePipes();
		visited = new boolean[R][C];
		recordMap = new char[R][C];
		
		// 끊어진 부분 파이프 모양 탐색에 뚫려있는 방향 정보 필요해서 M, Z 둘 다에서부터 dfs
		dfs(startR, startC, new int[] {0,0});
		dfs(destR, destC, new int[] {0,0});
		
		System.out.println(answerR + " " + answerC + " " + answerChar);
	}
}
