import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ3190_뱀_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, K, L;
	static int[][] map;
	static List<int[]> snakeLocations = new ArrayList<>();
	static HashMap<Integer, String> rotationInfo = new HashMap<>();
	
	// 오른쪽부터 0
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			map[a][b] = 1;

		}

		L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			String C = st.nextToken();
			rotationInfo.put(X, C);
		}

		int cx = 0, cy = 0;
		int time = 0, direction = 0;
		snakeLocations.add(new int[] { 0, 0 });

		while (true) {
			time++;

			// 다음 뱀의 머리가 갈 위치
			int nx = cx + dx[direction];
			int ny = cy + dy[direction];

			// 벽이나 자기자신의 몸과 부딪히는 경우
			if (isFinish(nx, ny))
				break;
			
			snakeLocations.add(new int[] { nx, ny });
			
			// 그 자리에 사과가 있는 경우
			if (map[nx][ny] == 1)
				map[nx][ny] = 0;
			
			// 사과가 없는 경우 꼬리쪽 위치 삭제
			else
				snakeLocations.remove(0);

			// 방향 변경
			if (rotationInfo.containsKey(time)) {
				if (rotationInfo.get(time).equals("D"))
					direction = (direction + 1) % 4;
				else
					direction = (direction + 3) % 4;
			}

			cx = nx;
			cy = ny;
		}

		System.out.print(time);
	}

	public static boolean isFinish(int x, int y) {
		if (x < 0 || y < 0 || x >= N || y >= N) {
			return true;
		}

		for (int i = 0; i < snakeLocations.size(); i++) {
			int[] location = snakeLocations.get(i);
			
			// 뱀이 자기자신의 몸과 부딪히는 경우
			if (x == location[0] && y == location[1])
				return true;
		}
		
		return false;
	}
}
