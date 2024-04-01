package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN14502 {
	static int N, M, map[][], zeros[][], zeroCnt, result[][], virusCnt, virus[][], answer, temp;
	static boolean[][] visited;
	static int[] dr = {0,1,0,-1};
	static int[] dc = {1,0,-1,0};
	
	private static void combi(int depth, int start) {
 		if (depth == 3) {
 			for (int i = 0; i < 3; i++) { // 벽 세움
 				map[result[i][0]][result[i][1]] = 1;
 			}
 			visited = new boolean[N][M];
 			temp = 0; 
 			for (int i = 0; i < virusCnt; i++) { // 바이러스 세기
				count(virus[i][0], virus[i][1]);
			}
 			answer = Math.max(answer, zeroCnt - 3 - temp + virusCnt); // 3은 세운 벽, virusCnt는 중복으로 세어져서
 			
			for (int i = 0; i < 3; i++) map[result[i][0]][result[i][1]] = 0; // 벽 원상복구
			return;
		}
		
		for (int i = start; i < zeroCnt; i++) {
			result[depth] = zeros[i];
			combi(depth + 1, i+1);
		}
	}

	private static void count(int r, int c) {
		visited[r][c] = true; // 원래 2로 바꿔서 방문체크 안 하게 했었는데 그러면 원상복구해야해서 방문체크 하기로 함.
		temp++;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] != 0 || visited[nr][nc]) continue;
	
			count(nr, nc);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		zeros = new int[N*M][2];
		zeroCnt = 0;
		virus = new int[N*M][2];
		virusCnt = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st1.nextToken());
				
				if (map[i][j] == 0) {
					zeros[zeroCnt] = new int[] {i,j};
					zeroCnt++;
				}else if(map[i][j] == 2) {
					virus[virusCnt] = new int[] {i,j};
					virusCnt++;
				}
			}
		}
		
		result = new int[3][2];
		combi(0, 0);

		System.out.println(answer);
	}
}
