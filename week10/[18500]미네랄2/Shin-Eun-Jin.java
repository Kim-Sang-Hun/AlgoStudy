import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int R, C;
	static int N;
	static char[][] map;
	static boolean[][] isVisit;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];

		for (int i = 0; i < R; i++) {
			int j = 0;
			for (char c : br.readLine().toCharArray()) {
				map[i][j++] = c;
			}
		}

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			int h = Integer.parseInt(st.nextToken());

			if (n % 2 != 0) { // 왼쪽 -> 오른쪽 던지는 경우
				throwLeft(h);
			} else { // 오른쪽 -> 왼쪽 던지는 경우
				throwRight(h);
			}

			/*---분리된 클러스터가 있다면 바닥으로 떨어지게...---*/
			// 바닥에 있는 클러스터 방문 체크
			isVisit = new boolean[R][C];
			for (int i = 0; i < C; i++) {
				if (map[R - 1][i] == '.' || isVisit[R - 1][i])
					continue;
				dfs(R - 1, i);
			}

			// 방문체크가 안되어 있다면 떠 있는 클러스터
			while (true) {
				// 배열 복사
				char[][] copiedArr = copyArr(map);

				boolean flag = downCluster(copiedArr);
				if (flag) { // 내리기 성공했을 경우
					map = copiedArr;
				} else { // 더이상 내릴 수 없는 경우
					break;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}

	}

	public static boolean downCluster(char[][] arr) {

		int cnt = 0;
		for (int row = R - 1; row >= 0; row--) {
			for (int col = 0; col < C; col++) {
				if (arr[row][col] == '.' || isVisit[row][col]) {
					continue;
				}

				int nextRow = row + 1;
				if (!checkRange(nextRow, col) || arr[nextRow][col] != '.') {
					return false;
				}

				arr[row][col] = '.';
				arr[nextRow][col] = 'x';
				cnt++;
			}
		}

		// 내릴게 더이상 없음
		if (cnt == 0) {
			return false;
		}

		// 내리기 성공
		return true;
	}

	public static void dfs(int row, int col) {
		isVisit[row][col] = true;

		for (int d = 0; d < 4; d++) {
			int nextRow = row + dr[d];
			int nextCol = col + dc[d];

			if (!checkRange(nextRow, nextCol)) {
				continue;
			}
			if (map[nextRow][nextCol] == '.' || isVisit[nextRow][nextCol]) {
				continue;
			}

			dfs(nextRow, nextCol);
		}
	}

	public static void throwLeft(int h) {
		for (int i = 0; i < C; i++) {
			if (map[R - h][i] == 'x') {
				map[R - h][i] = '.';
				break;
			}
		}
	}

	public static void throwRight(int h) {
		for (int i = C - 1; i >= 0; i--) {
			if (map[R - h][i] == 'x') {
				map[R - h][i] = '.';
				break;
			}
		}
	}

	public static char[][] copyArr(char[][] arr) {
		char[][] copiedArr = new char[arr.length][arr[0].length];

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				copiedArr[i][j] = arr[i][j];
			}
		}

		return copiedArr;

	}

	public static boolean checkRange(int row, int col) {
		if (row < 0 || row >= R || col < 0 || col >= C) {
			return false;
		}
		return true;
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}
