import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ16234_인구이동_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, L, R;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static boolean movementPossibility; // 이동 가능성을 체크하기 위한 변수
	static int countryCount = 0;
	static int populationSum = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		int time = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			movementPossibility = false;
			int[][] unionMap = new int[N][N]; // 어디끼리 국경을 열어 연합이 되었는지 확인하기 위한 map
			ArrayList<Integer> population = new ArrayList<>();
			int num = 1;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (unionMap[i][j] == 0) {
						countryCount = 0;
						populationSum = 0;
						
						// DFS로 연결된 지역 찾아서 
						open(i, j, unionMap, num);
						population.add(populationSum / countryCount);
						num++;
					}
				}
			}
			
			// 이동 가능성이 없으면
			if (!movementPossibility)
				break;
			
			// 인구 이동
			move(unionMap, population);
			time++;
		}
		
		System.out.print(time);
	}

	public static void open(int x, int y, int[][] unionMap, int num) {
		unionMap[x][y] = num;
		countryCount++;
		populationSum += map[x][y];

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (0 <= nx && 0 <= ny && nx < N && ny < N && unionMap[nx][ny] == 0) {
				int populationGap = Math.abs(map[nx][ny] - map[x][y]);
				if (L <= populationGap && populationGap <= R) {
					movementPossibility = true;
					open(nx, ny, unionMap, num);
				}
			}
		}
	}

	public static void move(int[][] unionMap, ArrayList<Integer> population) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = population.get(unionMap[i][j] - 1);
			}
		}
	}
}
