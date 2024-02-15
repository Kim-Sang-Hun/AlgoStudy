import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, K, min = Integer.MAX_VALUE, map[][], rotateOps[][];
	static int dirs[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int dirsD[][] = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static int order[];
	static boolean selected[];
	
	// 문제의 조건대로 돌리는 함수
	// len값을 count로 써서 len이 0이 될때까지 1씩 빼가면서 돌려준다(바깥부터 안쪽으로)
	public static void rotate(int y, int x, int len) {
		if (len == 0) return;
		int nY = y - len, nX = x - len;
		// 다 돌고 나면 시작점에 넣을 점수가 없으므로 저장해둔다.
		int startPointValue = map[nY + 1][nX];
		int preValue = map[nY][nX];
		int tmpValue;
		// 오른쪽, 아래, 왼쪽, 위 순으로 돌면서 이전 값을 넣어준다.
		for (int i = 0; i < dirs.length; i++) {
			for (int j = 0; j < 2 * len; j++) {
				nY += dirs[i][0];
				nX += dirs[i][1];
				tmpValue = map[nY][nX];
				map[nY][nX] = preValue;
				preValue = tmpValue;
			}
		}
		map[y - len][x - len] = startPointValue;
		rotate(y, x, len - 1);
	}
	
	// 돌렸던 배열을 되돌리는 함수
	// 배열을 저장해두고 복사해서 쓰는 방식은 메모리를 많이 소모하기에 이 방식을 사용했다.
	public static void deRotate(int y, int x, int len) {
		if (len == 0) return;
		int nY = y - len, nX = x - len;
		int startPointValue = map[nY][nX + 1];
		int preValue = map[nY][nX];
		int tmpValue;
		for (int i = 0; i < dirs.length; i++) {
			for (int j = 0; j < 2 * len; j++) {
				nY += dirsD[i][0];
				nX += dirsD[i][1];
				tmpValue = map[nY][nX];
				map[nY][nX] = preValue;
				preValue = tmpValue;
			}
		}
		map[y - len][x - len] = startPointValue;
		deRotate(y, x, len - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		// 돌리는 연산들을 저장한다
		rotateOps = new int[K][3];
		// 연산의 순서를 저장한다
		order = new int[K];
		selected = new boolean[K];
		
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			// 좌표값은 받은 값에서 -1해줘야 실제 좌표값이 된다
			rotateOps[i][0] = Integer.parseInt(st.nextToken()) - 1;
			rotateOps[i][1] = Integer.parseInt(st.nextToken()) - 1;
			rotateOps[i][2] = Integer.parseInt(st.nextToken());
		}
		perpetual(0);
		System.out.println(min);
	}

	public static void perpetual(int count) {
		if (count == K) {
			// order배열의 순서대로 돌려준다
			for (int i = 0; i < K; i++) {
				rotate(rotateOps[order[i]][0], rotateOps[order[i]][1], rotateOps[order[i]][2]);
			}
			// 배열의 값을 확인한다
			for (int i = 0; i < N; i++) {
				int tmp = 0;
				for (int j = 0; j < M; j++) {
					tmp += map[i][j];
				}
				min = Math.min(min, tmp);
			}
			// 원래대로 돌려준다
			for (int i = K - 1; i >= 0; i--) {
				deRotate(rotateOps[order[i]][0], rotateOps[order[i]][1], rotateOps[order[i]][2]);
			}
			return;
		}
		for (int i = 0; i < K; i++) {
			if (selected[i]) continue;
			selected[i] = true;
			order[count] = i;
			perpetual(count + 1);
			selected[i] = false;
		}
	}
}
