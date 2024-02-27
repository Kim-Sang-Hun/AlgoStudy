package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 현재 칸에 적혀있는 수만큼 오른쪽이나 아래로
 * 0,0에서 N-1,N-1까지 경로의 개수 -> DP
 * */
public class JUN1890 {
	static int N, map[][];
	static long answer[][];
	static boolean visited[][];
	static int[] dr = {1,0};
	static int[] dc = {0,1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(read.readLine());
		map = new int[N][N];
		answer = new long[N][N]; // 경로의 개수는 2^63 - 1 보다 작거나 같다
		answer[0][0] = 1; //초기화!
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j] == 0) break;

				int now = map[i][j];
				
				for (int k = 0; k < 2; k++) {
					int br = i + dr[k]*now;
					int bc = j + dc[k]*now;

					if(br >= N || bc >= N) continue;
					
					answer[br][bc] += answer[i][j];
				}
			}
		}
		
		System.out.println(answer[N-1][N-1]);

	}

}
