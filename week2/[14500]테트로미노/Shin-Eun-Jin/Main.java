import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M; // 세로(행), 가로(열) 크기
	static int maxSum; // 최대 합 저장
	static int[][] arr;
	static boolean[][] isVisited;
	static int[] dr = {0,0,-1,1};
	static int[] dc = {1,-1,0,0};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());  
		M = Integer.parseInt(st.nextToken()); 
		maxSum = 0;
		
		arr = new int[N][M];
		isVisited = new boolean[N][M];
		
		// 배열 입력
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dfs 탐색
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				isVisited[i][j] = true;
				dfs(i, j, 1, arr[i][j]);
				isVisited[i][j] = false;
			}
		}
		
		System.out.println(maxSum);
	}
	
	static void dfs(int row, int col, int count, int sum) {
		if(count == 4) {
			maxSum = Math.max(maxSum, sum);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nextRow = row + dr[i];
			int nextCol = col + dc[i];
			
			if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M || isVisited[nextRow][nextCol]) {
				continue;
			}
			
			if(count == 2) {
				isVisited[nextRow][nextCol] = true;
				dfs(row, col, count + 1, sum + arr[nextRow][nextCol]);
				isVisited[nextRow][nextCol] = false;
			}
			
			isVisited[nextRow][nextCol] = true;
			dfs(nextRow, nextCol, count + 1, sum + arr[nextRow][nextCol]);
			isVisited[nextRow][nextCol] = false;
			
		}
	}
}
