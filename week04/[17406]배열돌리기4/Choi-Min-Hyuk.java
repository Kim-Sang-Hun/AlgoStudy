import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17406_배열_돌리기4_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M, K, arr[][], rotation[][], permutationOrder[], answer = Integer.MAX_VALUE;
	static boolean visited[];
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		rotation = new int[K][3];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			rotation[i][0] = Integer.parseInt(st.nextToken()) - 1;
			rotation[i][1] = Integer.parseInt(st.nextToken()) - 1;
			rotation[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// 회전 연산의 순서를 순열로 모든 경우를 따져보면서 최솟값 계산
		visited = new boolean[K];
		permutationOrder = new int[K];
		permutation(0);

		System.out.print(answer);
	}

	public static void permutation(int count) {
		if (count == K) {
			rotateMatrix();
			return ;
		}
		
		for (int i = 0; i < K; i++) {
			if (visited[i])
				continue;
			
			visited[i] = true;
			permutationOrder[count] = i;
			permutation(count + 1);
			visited[i] = false;
		}
	}

	public static void rotateMatrix() {
		int[][] tempArray = copyOriginArray();

		for (int i = 0; i < K; i++) {
			int r = rotation[permutationOrder[i]][0];
			int c = rotation[permutationOrder[i]][1];
			int s = rotation[permutationOrder[i]][2];

			for (int j = 1; j <= s; j++) {
				// 위
				int upTmp = tempArray[r - j][c + j];
				for (int y = c + j; y > c - j; y--) {
					tempArray[r - j][y] = tempArray[r - j][y - 1];
				}
				
				// 오른쪽
				int rightTmp = tempArray[r + j][c + j];
				for (int x = r + j; x > r - j; x--) {
					tempArray[x][c + j] = tempArray[x - 1][c + j];
				}
				tempArray[r - j + 1][c + j] = upTmp;
				
				// 아래
				int leftTmp = tempArray[r + j][c - j];
				for (int y = c - j; y < c + j; y++) {
					tempArray[r + j][y] = tempArray[r + j][y + 1];
				}
				tempArray[r + j][c + j - 1] = rightTmp;
				
				// 왼쪽
				for (int x = r - j; x < r + j; x++) {
					tempArray[x][c - j] = tempArray[x + 1][c - j];
				}
				tempArray[r + j - 1][c - j] = leftTmp;
			}
		}

		calculateMatrix(tempArray);
	}

	public static int[][] copyOriginArray() {
		int[][] tempArray = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempArray[i][j] = arr[i][j];
			}
		}

		return tempArray;
	}

	public static void calculateMatrix(int[][] array) {
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = 0; j < M; j++) {
				sum += array[i][j];
			}
			
			if (answer > sum)
				answer = sum;
		}
	}
}
