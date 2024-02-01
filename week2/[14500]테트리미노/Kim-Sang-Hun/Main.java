```java
import java.util.Scanner;
/*
* 4개의 블록을 모두 놓아보는 것은 사실 원점에서 임의로 4개의 블록을 탐색해보는 것과 같다. 따라서 DFS로 돌아다니면서 최대값을 탐색해본다.
* T-블록의 경우 DFS로 확인할 수 없으므로 checkTetris()를 통해 확인해본다.
*/
public class Main {
	static int N, M, max;
	static int[][] map;
	
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static boolean[][] visited;
	
	public static void dfs(int y, int x, int idx, int sum) {
    // 종료조건 : 4번 탐색하면 종료한다.
		if (idx == 4) {
			max = Math.max(max, sum);
			return;
		}
		for (int i = 0; i < dirs.length; i++) {
			int nY = y + dirs[i][0];
			int nX = x + dirs[i][1];
			
			if (nY < 0 || nY >= N || nX < 0 || nX >= M) continue;
			if (visited[nY][nX]) continue;
			visited[nY][nX] = true;
			dfs(nY, nX, idx + 1, sum + map[nY][nX]);
			visited[nY][nX] = false;
		}
	}
	
	public static void checkTetris(int y, int x) {
		int min = Integer.MAX_VALUE;
		int sum = map[y][x];
		boolean atEdge = false;
    // 4방향 모두 더해보고 가장 낮은 값을 빼준다. 만약 끝에 있다면 빼줄 필요가 없으므로 atEdge를 이용해 확인해준다.
		for (int i = 0; i < dirs.length; i++) {
			int nY = y + dirs[i][0];
			int nX = x + dirs[i][1];
			if (nY < 0 || nY >= N || nX < 0 || nX >= M) {
				atEdge = true;
				continue;
			}
			sum += map[nY][nX];
			min = Math.min(min, map[nY][nX]);
		}
		if (atEdge) {
			max = Math.max(max, sum);
		} else {
			max = Math.max(max, sum - min);
		}
	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		N = s.nextInt();
		M = s.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = s.nextInt();
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j]);
				visited[i][j] = false;
				checkTetris(i, j);
			}
		}
		System.out.println(max);
	}
}
```
