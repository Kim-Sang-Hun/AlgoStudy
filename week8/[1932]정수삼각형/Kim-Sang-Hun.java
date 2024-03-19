import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* dp로 한번에 풀어주었다.
* int[][] triangle을 int[N][]으로 받고 들어오는 데이터의 길이에 따라 만들어주면 메모리 공간을 아낄 수 있다.
* 입력받으면서 바로 바로 계산해주면 연산을 아낄 수 있다.
* 맨 처음과 맨 끝만 다르게 해주고 중간일 경우 왼쪽과 오른쪽 값 중에 큰 값을 선택해서 더해준다.
*/
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] triangle = new int[N][];
		triangle[0] = new int[1];
		triangle[0][0] = Integer.parseInt(br.readLine());
		for (int i = 1; i < N; i++) {
			triangle[i] = new int[i + 1];
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < i + 1; j++) {
				if (j == i) {
					triangle[i][j] = Integer.parseInt(st.nextToken()) + triangle[i-1][j-1];
				} else if (j == 0) {
					triangle[i][j] = Integer.parseInt(st.nextToken()) + triangle[i-1][j];
				} else {
					triangle[i][j] = Integer.parseInt(st.nextToken()) + Math.max(triangle[i-1][j-1], triangle[i-1][j]);
				}
			}
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, triangle[N-1][i]);
		}
		System.out.println(max);
	}
}
