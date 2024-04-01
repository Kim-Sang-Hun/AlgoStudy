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
	static int[] dx = { 0, -1, 1, 0, 0 }; // 1: 위, 2: 아래, 3: 왼쪽, 4: 오른쪽
	static int[] dy = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		// 격자 정보
		map = new int[N][N];
		
		// 냄새 정보
		// smellInfo[i][j][0]: 냄새를 남긴 상어
		// smellInfo[i][j][1]: 냄새가 사라지기까지 남은 시간
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
		
		// 각 상어의 방향들
		sharkDirections = new int[M + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++)
			sharkDirections[i] = Integer.parseInt(st.nextToken());
		
		// 각 상어의 방향의 우선순위
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
		// 상어가 이동한 후 격자 상황
		int[][] tmp = new int[N][N];
		// 새로운 냄새 정보 저장
		ArrayDeque<int[]> newSmellInfos = new ArrayDeque<>();
		
		// 모든 칸에서 상어일 경우만 확인
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)
					continue;
				
				int sharkNum = map[i][j];
				int sharkRow = N;
				int sharkCol = N;
				int sharkDirection = 0;
				
				// 4방향 탐색
				for (int direction = 1; direction <= 4; direction++) {
					int ni = i + dx[directionPriorities[sharkNum][sharkDirections[sharkNum]][direction]];
					int nj = j + dy[directionPriorities[sharkNum][sharkDirections[sharkNum]][direction]];
					
					if (ni < 0 || nj < 0 || ni >= N || nj >= N)
						continue;
					
					if (smellInfo[ni][nj][0] != sharkNum && smellInfo[ni][nj][1] >= time)
						continue;
					
					// 최적을 찾은 경우
					if (smellInfo[ni][nj][1] < time) {
						sharkRow = ni;
						sharkCol = nj;
						sharkDirection = directionPriorities[sharkNum][sharkDirections[sharkNum]][direction];
						break;
					}
					
					// sharkCol이 아무 선택도 안된 경우 -> 가장 처음에 선택된 방향이 됨
					else if (smellInfo[ni][nj][0] == sharkNum && sharkRow == N) {
						sharkRow = ni;
						sharkCol = nj;
						sharkDirection = directionPriorities[sharkNum][sharkDirections[sharkNum]][direction];
					}
				}
				
				if (tmp[sharkRow][sharkCol] == 0) {
					tmp[sharkRow][sharkCol] = map[i][j];
					newSmellInfos.offer(new int[] { sharkRow, sharkCol, sharkNum, time + k });
				}
				
				else if (tmp[sharkRow][sharkCol] > map[i][j]) {
					tmp[sharkRow][sharkCol] = map[i][j];
					M--;
					newSmellInfos.offer(new int[] { sharkRow, sharkCol, sharkNum, time + k });
				} 
				
				else
					M--;
				
				
				sharkDirections[sharkNum] = sharkDirection;
			}
		}
		
		while (!newSmellInfos.isEmpty()) {
			int[] newSmellInfo = newSmellInfos.poll();
			smellInfo[newSmellInfo[0]][newSmellInfo[1]][0] = newSmellInfo[2];
			smellInfo[newSmellInfo[0]][newSmellInfo[1]][1] = newSmellInfo[3];
		}
		
		map = tmp;
	}
}
