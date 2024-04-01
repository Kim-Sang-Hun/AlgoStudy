import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17779_게리맨더링2_최민혁 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, map[][];

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int answer = Integer.MAX_VALUE;
		
		// x, y, d1, d2 모든 경우 확인
		for (int x = 1; x < N; x++) {
			for (int y = 1; y < N; y++) {
				for (int d1 = 1; d1 < N; d1++) {
					for (int d2 = 1; d2 < N; d2++) {
						if (x + d1 + d2 <= N && 1 <= y - d1 && y + d2 <= N)
								answer = Math.min(answer, getDistrictCountRange(x, y, d1, d2));
					}
				}
			}
		}

		System.out.print(answer);
	}
	
	public static int getDistrictCountRange(int x, int y, int d1, int d2) {
		// 5번 선거구 구역을 표시
		boolean[][] isDistrict5 = new boolean[N + 1][N + 1];
		int startColumn = y; // x행 y열부터 시작
		int columnSize = 1;
		// 5번 선거구의 높이는 x부터 (x + d1 + d2)까지
		for (int i = 0; i <= d1 + d2; i++) {
			for (int j = 0; j < columnSize; j++) {
				isDistrict5[x + i][startColumn + j] = true;
			}
			
			// startColumn, columnSize 조절
			if (i < d1) {
				startColumn--;
				columnSize++;
			}
			else {
				startColumn++;
				columnSize--;
			}
			
			if (i < d2)
				columnSize++;
			else
				columnSize--;
		}

		// 1~5번 선거구의 인구 총합을 저장할 배열
		int[] districtCount = new int[6];
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (isDistrict5[r][c])
					districtCount[5] += map[r][c];
				// 1번 선거구
				else if (1 <= r && r < x + d1 && 1 <= c && c <= y)
					districtCount[1] += map[r][c];
				// 2번 선거구
				else if (1 <= r && r <= x + d2 && y < c && c <= N)
					districtCount[2] += map[r][c];
				// 3번 선거구
				else if (x + d1 <= r && r <= N && 1 <= c && c < y - d1 + d2)
					districtCount[3] += map[r][c];
				// 4번 선거구
				else
					districtCount[4] += map[r][c];
			}
		}
		
		// 정렬 후 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이를 return
		Arrays.sort(districtCount);
		return districtCount[5] - districtCount[1];
	}
}
