package Algo_week04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2931 {
	static int N,M;
	static char[][] arr;
	static boolean[][] visited;
	static int startX, startY, endX, endY;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static char[] block = {'|', '-', '+', '1', '2', '3', '4'};
	static boolean flag;

	public static void main(String[] args) throws Exception {

		//일단 틀렸는데.. 낼게요.. 지송..
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new char[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 'M') {
					startX = i;
					startY = j;
				} else if (arr[i][j] == 'Z') {
					endX = i;
					endY = j;
				}
			}
		}
		
		arr[startX][startY] = '+';
		arr[endX][endY] = '+';
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == '.') {
					for (int k = 0; k < 7; k++) {
						visited[startX][startY] = true;
						arr[i][j] = block[k];
						sol(startX,startY);
						
						if (flag) {
							System.out.println((i+1) + " " + (j+1) + " " + block[k]);
							return;
						}
						
						arr[i][j] = '.';
						visited[startX][startY] = false;
					}
				}
			}
		}		
		
	}

	private static void sol(int x, int y) {
		if (visited[endX][endY]) {
			flag = true;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x+dx[i];
			int ny = y+dy[i];
			
			if (in_range(nx,ny) && !visited[nx][ny]) {
				if (i == 0 && cantop(x,y) && check(nx,ny)) {	//현재위치에서 위로 갈수 있을때
					visited[nx][ny] = true;
					sol(nx,ny);
					visited[nx][ny] = false;
				} else if (i == 1 && canleft(x,y) && check(nx,ny)) {	//현위치에서 왼쪽
					visited[nx][ny] = true;
					sol(nx,ny);
					visited[nx][ny] = false;
				} else if (i == 2 && canbottom(x,y) && check(nx,ny)) {	//현위치에서 아래쪽
					visited[nx][ny] = true;
					sol(nx,ny);
					visited[nx][ny] = false;
				} else if (i == 3 && canright(x,y) && check(nx,ny)) {	//현위치에서 오른쪽
					visited[nx][ny] = true;
					sol(nx,ny);
					visited[nx][ny] = false;
				}
			}
		}
		
	}

	private static boolean check(int a, int b) {	//다음놈꺼 계산
		if (arr[a][b] == '+') {
			for (int i = 0; i < 4; i++) {
				int na = a+dx[i];
				int nb = b+dy[i];
				if (!in_range(na,nb)) return false;
				if (arr[na][nb] == '.') return false;
				
				if (i == 0 && !cantop(na,nb)) return false;
				else if (i == 1 && !canleft(na,nb)) return false;
				else if (i == 2 && !canbottom(na,nb)) return false;
				else if (i == 3 && !canright(na,nb)) return false; 
			}
			return true;
		} else if (arr[a][b] == '|') {
			if (in_range(a-1,b) && cantop(a-1,b) && in_range(a+1,b) && canbottom(a+1,b)) return true;
		} else if (arr[a][b] == '-') {
			if (in_range(a,b-1) && canleft(a,b-1) && in_range(a,b+1) && canright(a,b+1)) return true;
		} else if (arr[a][b] == '1') {
			if (in_range(a+1,b) && canbottom(a+1,b) && in_range(a,b+1) && canright(a,b+1)) return true;
		} else if (arr[a][b] == '2') {
			if (in_range(a-1,b) && cantop(a-1,b) && in_range(a,b+1) && canright(a,b+1)) return true;
		} else if (arr[a][b] == '3') {
			if (in_range(a-1,b) && cantop(a-1,b) && in_range(a,b-1) && canleft(a,b-1)) return true;
		} else if (arr[a][b] == '4') {
			if (in_range(a,b-1) && canleft(a,b-1) && in_range(a+1,b) && canbottom(a+1,b)) return true;
		}
		
		return false;
	}

	private static boolean canright(int a, int b) {
		if (arr[a][b] == '-' || arr[a][b] == '+' || arr[a][b] == '1'|| arr[a][b] == '2') return true;
		return false;
	}

	private static boolean canleft(int a, int b) {
		if (arr[a][b] == '-' || arr[a][b] == '+' || arr[a][b] == '3' || arr[a][b] == '4') return true;
		return false;
	}

	private static boolean canbottom(int a, int b) {
		if (arr[a][b] == '|' || arr[a][b] == '+' || arr[a][b] == '1' || arr[a][b] == '4') return true;
		return false;
	}

	private static boolean cantop(int a, int b) {
		if (arr[a][b] == '|' || arr[a][b] == '+' || arr[a][b] == '2' || arr[a][b] == '3') return true;
		return false;
	}


	private static boolean in_range(int a, int b) {
		return 0 <= a && a < N && 0 <= b && b < M;
	}

}
