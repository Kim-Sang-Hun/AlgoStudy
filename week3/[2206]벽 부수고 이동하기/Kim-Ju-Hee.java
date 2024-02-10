package week3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class JUN2206 {
    static int N, M, answer, map[][], distance[][][];
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static Queue<int[]> queue;

    private static void bfs() {
  
    	while(!queue.isEmpty()) {
    		int[] now = queue.poll();
    		int r = now[0];
    		int c = now[1];
    		int breakWall = now[2];
    		
    		for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
				
				if(map[nr][nc] == 0 && distance[nr][nc][breakWall] == 0) { // 벽 안부숨
					distance[nr][nc][breakWall] = distance[r][c][breakWall] + 1;
					queue.add(new int[] {nr, nc, breakWall});
				}else if(map[nr][nc] == 1 && breakWall == 0) { // 벽 부숨
					distance[nr][nc][1] = distance[r][c][breakWall] + 1;
					queue.add(new int[] {nr, nc, 1}); 
				}	
			}
    	} 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        distance = new int[N][M][2]; // 벽 부쉈을 때[0] 안 부쉈을 때[1] 상태를 함께 저장

        for (int i = 0; i < N; i++) {
            String input = sc.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }
        
        distance[0][0] = new int[] {1,1};
        queue = new LinkedList<>();
        queue.add(new int[] {0,0,0});
        bfs();
        
        if(distance[N-1][M-1][0] == 0 && distance[N-1][M-1][1] == 0) {
        	System.out.println(-1);
        }else if(distance[N-1][M-1][0] == 0 || distance[N-1][M-1][1] == 0) { // 둘 중에 하나가 0이면 0이 아닌 값
        	System.out.println(distance[N-1][M-1][0] != 0 ? distance[N-1][M-1][0]:distance[N-1][M-1][1]);
        }else {
        	System.out.println(Math.min(distance[N-1][M-1][0], distance[N-1][M-1][1]));
        }
    }
}
