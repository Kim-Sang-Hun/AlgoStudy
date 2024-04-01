package problemsolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2048 문제.
 * 처음에는 한번 이동할 때 한 줄에 하나의 블록만 합쳐지는 줄 알고 풀었지만 그렇지 않음을 알고 풀 수 있었다.
 * dfs를 이용해서 4방향 모두 가본다. 4^5 즉 2^10이므로 할만하다.
 * 기존의 맵에 영향이 가면 안되므로 계속 맵을 복사해서 집어넣어줬다. 더 좋은 방법이 있을지는 모르겠다.
 */
public class JUN12100_2048 {

	static StringBuilder sb;
	static int N, maxBlock;
	static int[][] map;
	static int[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		int max = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (max < map[i][j]) max = map[i][j];
			}
		}

		for (int i = 0; i < 4; i++) {
			dfs(map, 0, max);
		}
		
		System.out.println(maxBlock);
	}

	public static void dfs(int[][] map, int depth, int max) {
		if (depth == 5) {
			if (maxBlock < max) {
				maxBlock = max;
			}
			return;
		}
		// 백트래킹 - 만약 남아있는 횟수에 2를 곱한 값보다 현재값이 작으면 더 이상 탐색할 필요가 없다.
		if (max * Math.pow(2, 5 - depth) <= maxBlock) return;

		for (int i = 0; i < 4; i++) {
			int[][] tmp = new int[N][];
			for (int j = 0; j < tmp.length; j++) {
				tmp[j] = map[j].clone();
			}
			int tmpMax = goDir(tmp, i, max);
			dfs(tmp, depth + 1, tmpMax);
		}
	}

	public static int goDir(int[][] map, int dir, int max) {
		/* 이동은 기본적으로 투포인터를 활용했다.
		 * 옮길 값의 위치는 startPoint, 옮겨야 할 위치는 targetPoint로 두었다.
		 * startPoint와 targetPoint가 같거나 startPoint지점의 값이 0이라면 startPoint를 바꾸어준다.
		 * 3가지 경우의 수가 존재한다.
		 * 
		 * targetPoint의 값이 0이라면 startPoint의 값을 targetPoint에 옮겨준다.
		 * targetPoint의 값이 startPoint의 값과 같다면 합쳐주고 startPoint의 값을 0으로 만든다.
		 * targetPoint의 값이 startPoint의 값과 다르다면 targetPoint의 값을 한 칸 옮겨준다.
		 * 
		*/ 
		
		// 오른쪽으로 이동
		if (dir == 0) {
			for (int i = 0; i < N; i++) {
				int startPoint = N - 2;
				int targetPoint = N - 1;

				while (targetPoint > 0) {
					while (startPoint > 0 && map[i][startPoint] == 0 || startPoint == targetPoint) {
						--startPoint;
					}
					if (startPoint < 0 || map[i][startPoint] == 0)
						break;
					
					if (map[i][targetPoint] == 0) {
						map[i][targetPoint] = map[i][startPoint];
						map[i][startPoint] = 0;
					} else {
						if (map[i][startPoint] == map[i][targetPoint]) {
							map[i][startPoint] = 0;
							map[i][targetPoint] *= 2;
							if (max < map[i][targetPoint]) max = map[i][targetPoint];
						}
						--targetPoint;
					}
				}
			}
		}
		// 아래로 이동
		if (dir == 1) {
			for (int i = 0; i < N; i++) {
				int startPoint = N - 2;
				int targetPoint = N - 1;

				while (targetPoint > 0) {
					while (startPoint > 0 && map[startPoint][i] == 0 || startPoint == targetPoint) {
						--startPoint;
					}
					if (startPoint < 0 || map[startPoint][i] == 0)
						break;
					if (max < map[startPoint][i]) max = map[startPoint][i];
					
					if (map[targetPoint][i] == 0) {
						map[targetPoint][i] = map[startPoint][i];
						map[startPoint][i] = 0;
					} else {
						if (map[startPoint][i] == map[targetPoint][i]) {
							map[startPoint][i] = 0;
							map[targetPoint][i] *= 2;
							if (max < map[targetPoint][i]) max = map[targetPoint][i];
						}
						--targetPoint;
					}
				}
			}
		}
		
		// 왼쪽으로 이동
		if (dir == 2) {
			for (int i = 0; i < N; i++) {
				int startPoint = 1;
				int targetPoint = 0;

				while (targetPoint < N - 1) {
					while (startPoint < N - 1 && map[i][startPoint] == 0 || startPoint == targetPoint) {
						++startPoint;
					}
					if (startPoint > N - 1 || map[i][startPoint] == 0)
						break;
					if (max < map[i][startPoint]) max = map[i][startPoint];
					
					if (map[i][targetPoint] == 0) {
						map[i][targetPoint] = map[i][startPoint];
						map[i][startPoint] = 0;
					} else {
						if (map[i][startPoint] == map[i][targetPoint]) {
							map[i][startPoint] = 0;
							map[i][targetPoint] *= 2;
							if (max < map[i][targetPoint]) max = map[i][targetPoint];
						}
						++targetPoint;
					}
				}
			}
		}
		
		// 위로 이동
		if (dir == 3) {
			for (int i = 0; i < N; i++) {
				int startPoint = 1;
				int targetPoint = 0;

				while (targetPoint < N - 1) {
					while (startPoint < N - 1 && map[startPoint][i] == 0 || startPoint == targetPoint) {
						++startPoint;
					}
					if (startPoint > N - 1 || map[startPoint][i] == 0)
						break;
					if (max < map[startPoint][i]) max = map[startPoint][i];
					
					if (map[targetPoint][i] == 0) {
						map[targetPoint][i] = map[startPoint][i];
						map[startPoint][i] = 0;
					} else {
						if (map[startPoint][i] == map[targetPoint][i]) {
							map[startPoint][i] = 0;
							map[targetPoint][i] *= 2;
							if (max < map[targetPoint][i]) max = map[targetPoint][i];
						}
						++targetPoint;
					}
				}
			}
		}

		return max;
	}
}
