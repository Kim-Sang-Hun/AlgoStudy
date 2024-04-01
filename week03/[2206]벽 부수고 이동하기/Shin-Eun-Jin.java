import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
	static int N,M;
	static int[][] arr;
	static int[][][] isVisited;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 행 입력
		M = Integer.parseInt(st.nextToken()); // 열 입력
		arr = new int[N][M];
		isVisited = new int[N][M][2];
		
		// 배열 입력
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = input.charAt(j) - 48;
			}
		}
		
		System.out.println(bfs());		
	}
	
	static int bfs() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {0,0,0}); // {행, 열, 벽 부순적 있는지 여부}
		isVisited[0][0][0] = 1;  	// {행, 열, 안부순적/부순적}
		int result = Integer.MAX_VALUE;
		
		while(!queue.isEmpty()) {
			int[] curVertex = queue.poll();
			int curRow = curVertex[0];
			int curCol = curVertex[1];
			int curBreak = curVertex[2];
			
			if(curRow == N-1 && curCol == M-1) {
				result = Math.min(result, isVisited[curRow][curCol][curBreak]);
			}
		
			for(int i = 0 ; i < 4; i++) {
				int nextRow = curRow + dr[i];
				int nextCol = curCol + dc[i];
				int nextBreak = curBreak;
				
				// 범위 체크
				if(nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
					continue;
				}
				
				// 방문 여부 체크
				if(isVisited[nextRow][nextCol][nextBreak] != 0) {
					continue;
				}
				
				// 다음 노드가 벽이 있는 노드인지 체크
				if(arr[nextRow][nextCol] == 1) {
					// 벽을 부순적 없는 경우
					if(curBreak == 0) {
						nextBreak = 1;
					}
					// 벽을 부순적 있는 경우
					else {
						continue;
					}
				}
				
				isVisited[nextRow][nextCol][nextBreak] = isVisited[curRow][curCol][curBreak] + 1;
				queue.add(new int[] {nextRow, nextCol, nextBreak});
			}
		}
		
		return result==Integer.MAX_VALUE ? -1 : result;
	}
}
