import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ19237_어른상어_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, k, time;
	static int[][] map;
	static int[][][] directionPriorities;
	static int[][][] smellInfo;
	static int[] sharkDirections;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		smellInfo = new int[N][N][2];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					smellInfo[i][j][0] = map[i][j]; // 0에는 상어 정보
					smellInfo[i][j][1] = k; // 1에는 남은 시간
				}
			}
		}
		sharkDirections = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++)
			sharkDirections[i] = Integer.parseInt(st.nextToken());

		directionPriorities = new int[M + 1][5][5];
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= 4; k++)
					directionPriorities[i][j][k] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (time = 1; time <= 1001; time++) {
			if (time == 1001)
				break;
			move();
			if (M == 1)
				break;
		}
		if (time == 1001)
			time = -1;
		
		System.out.print(time);
	}

	static void move() {
		int[][] tmp = new int[N][N];
		ArrayDeque<int[]> newSmell = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				
				int s = map[i][j];
				int mx = N;
				int my = N;
				int md = 0;
				
				for (int d = 1; d <= 4; d++) {
					int ni = i + dx[directionPriorities[s][sharkDirections[s]][d]];
					int nj = j + dy[directionPriorities[s][sharkDirections[s]][d]];
					
					if (ni < 0 || nj < 0 || ni >= N || nj >= N)
						continue;
					
					if (smellInfo[ni][nj][0] != s && smellInfo[ni][nj][1] >= time)
						continue;
					
					// 최적을 찾은 경우
					if (smellInfo[ni][nj][1] < time) {
						mx = ni;
						my = nj;
						md = directionPriorities[s][sharkDirections[s]][d];
						break;
					}
					
					// mx가 아무 선택도 안된 경우 -> 가장 처음에 선택된 방향이 됨
					else if (smellInfo[ni][nj][0] == s && mx == N) {
						mx = ni;
						my = nj;
						md = directionPriorities[s][sharkDirections[s]][d];
					}
				}
				
				if (tmp[mx][my] == 0) {
					tmp[mx][my] = map[i][j];
					newSmell.offer(new int[] { mx, my, s, time + k });
				}
				
				else if (tmp[mx][my] > map[i][j]) {
					tmp[mx][my] = map[i][j];
					M--;
					newSmell.offer(new int[] { mx, my, s, time + k });
				} 
				
				else
					M--;
				
				
				sharkDirections[s] = md;
			}
		}
		
		while (!newSmell.isEmpty()) {
			int[] n = newSmell.poll();
			smellInfo[n[0]][n[1]][0] = n[2];
			smellInfo[n[0]][n[1]][1] = n[3];
		}
		
		map = tmp;
	}
}
