package march4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN1932_정수삼각형_김주희 {
	static int N, nums[][];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N][N+1]; // 제일 왼쪽값 따로 처리 안하려고
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= i; j++) {
				nums[i-1][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= N-1; i++) {
			for (int j = 1; j <= N; j++) {
				nums[i][j] = Math.max(nums[i-1][j-1]+nums[i][j], nums[i-1][j]+nums[i][j]);
			}
		}

		System.out.println(Arrays.stream(nums[N-1]).max().getAsInt());
	}
}
