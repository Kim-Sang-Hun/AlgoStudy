import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12100_2048_Easy_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, board[][], answer;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 위, 아래, 왼쪽, 오른쪽, 4가지 경우로 5번 swipe하는 경우의 수들을 모두 따져봄
		getSwipesResult(0, board);
		System.out.print(answer);
	}
	
	// count번 만큼 swipe한 결과가 arr에 저장되어 parameter로 들어옴
	public static void getSwipesResult(int count, int[][] arr) {
		if (count == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > answer)
						answer = arr[i][j];
				}
			}
			
			return ;
		}
		
		for (int i = 0; i < 4; i++) {
			int[][] tempArray = new int[N][N];
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					tempArray[j][k] = arr[j][k];
				}
			}
			switch (i) {
			case 0:
				swipeDown(tempArray);
				break;
			case 1:
				swipeUp(tempArray);
				break;
			case 2:
				swipeLeft(tempArray);
				break;
			case 3:
				swipeRight(tempArray);
				break;
			default:
				break;
			}
			
			getSwipesResult(count + 1, tempArray);
		}
	}
	
	public static void swipeDown(int arr[][]) {
		// 아래로 swipe하는 경우 각 열끼리는 상호작용하지 않으니 열마다 따로 계산
		for (int col = 0; col < N; col++) {
			// 해당하는 열에서 합치려는 칸이 이미 합쳐진 칸인지 알기 위한 배열
			boolean isAlreadyCombined[] = new boolean[N];
			// 가장 밑에서 두 번째에 있는 칸부터 아래 쪽의 칸들과 합쳐질 가능성이 있는지 판단
			for (int i = N - 2; i >= 0; i--) {
				if (arr[i][col] == 0)
					continue;
				
				// 합쳐질 가능성이 있는 칸의 행의 index 찾기
				int prevValueIndex = i + 1;
				while (prevValueIndex < N - 1) {
					if (arr[prevValueIndex][col] != 0)
						break;
					
					prevValueIndex++;
				}
				
				if (arr[prevValueIndex][col] == arr[i][col]) {
					// 합칠 수 있는 경우
					if (!isAlreadyCombined[prevValueIndex]) {
						isAlreadyCombined[prevValueIndex] = true;
						arr[prevValueIndex][col] *= 2;
						arr[i][col] = 0;
					}
					// 이미 합쳐진 칸인 경우 그 사이가 비어 있으면 칸만 이동
					else if (prevValueIndex - 1 != i) {
						arr[prevValueIndex - 1][col] = arr[i][col];
						arr[i][col] = 0;
					}
				}
				// 비교하는 칸이 0인 경우 그 칸으로 이동
				else if (arr[prevValueIndex][col] == 0) {
					arr[prevValueIndex][col] = arr[i][col];
					arr[i][col] = 0;
				}
				// 합칠 수 없는데 사이가 비어 있으면 칸만 이동
				else if (prevValueIndex - 1 != i) {
					arr[prevValueIndex - 1][col] = arr[i][col];
					arr[i][col] = 0;
				}
			}
		}
	}
	
	public static void swipeUp(int arr[][]) {
		for (int col = 0; col < N; col++) {
			boolean isAlreadyCombined[] = new boolean[N];
			for (int i = 1; i < N; i++) {
				if (arr[i][col] == 0)
					continue;
				
				int prevValueIndex = i - 1;
				while (prevValueIndex > 0) {
					if (arr[prevValueIndex][col] != 0)
						break;
					
					prevValueIndex--;
				}
				
				if (arr[prevValueIndex][col] == arr[i][col]) {
					if (!isAlreadyCombined[prevValueIndex]) {
						isAlreadyCombined[prevValueIndex] = true;
						arr[prevValueIndex][col] *= 2;
						arr[i][col] = 0;
					}
					else if (prevValueIndex + 1 != i) {
						arr[prevValueIndex + 1][col] = arr[i][col];
						arr[i][col] = 0;
					}
				}
				else if (arr[prevValueIndex][col] == 0) {
					arr[prevValueIndex][col] = arr[i][col];
					arr[i][col] = 0;
				}
				else if (prevValueIndex + 1 != i) {
					arr[prevValueIndex + 1][col] = arr[i][col];
					arr[i][col] = 0;
				}
					
			}
		}
	}
	
	public static void swipeRight(int arr[][]) {
		for (int row = 0; row < N; row++) {
			boolean isAlreadyCombined[] = new boolean[N];
			for (int i = N - 2; i >= 0; i--) {
				if (arr[row][i] == 0)
					continue;
				
				int prevValueIndex = i + 1;
				while (prevValueIndex < N - 1) {
					if (arr[row][prevValueIndex] != 0)
						break;
					
					prevValueIndex++;
				}
				
				if (arr[row][prevValueIndex] == arr[row][i]) {
					if (!isAlreadyCombined[prevValueIndex]) {
						isAlreadyCombined[prevValueIndex] = true;
						arr[row][prevValueIndex] *= 2;
						arr[row][i] = 0;
					}
					else if (prevValueIndex - 1 != i) {
						arr[row][prevValueIndex - 1] = arr[row][i];
						arr[row][i] = 0;
					}
				}
				else if (arr[row][prevValueIndex] == 0) {
					arr[row][prevValueIndex] = arr[row][i];
					arr[row][i] = 0;
				}
				else if (prevValueIndex - 1 != i) {
					arr[row][prevValueIndex - 1] = arr[row][i];
					arr[row][i] = 0;
				}
			}
		}
	}
	
	public static void swipeLeft(int arr[][]) {
		for (int row = 0; row < N; row++) {
			boolean[] isAlreadyCombined = new boolean[N];
			for (int i = 1; i < N; i++) {
				if (arr[row][i] == 0)
					continue;
				
				int prevValueIndex = i - 1;
				while (prevValueIndex > 0) {
					if (arr[row][prevValueIndex] != 0)
						break;
					
					prevValueIndex--;
				}
				
				if (arr[row][prevValueIndex] == arr[row][i]) {
					if (!isAlreadyCombined[prevValueIndex]) {
						isAlreadyCombined[prevValueIndex] = true;
						arr[row][prevValueIndex] *= 2;
						arr[row][i] = 0;
					}
					else if (prevValueIndex + 1 != i) {
						arr[row][prevValueIndex + 1] = arr[row][i];
						arr[row][i] = 0;
					}
				}
				else if (arr[row][prevValueIndex] == 0) {
					arr[row][prevValueIndex] = arr[row][i];
					arr[row][i] = 0;
				}
				else if (prevValueIndex + 1 != i) {
					arr[row][prevValueIndex + 1] = arr[row][i];
					arr[row][i] = 0;
				}
			}
		}
	}
}
