package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

//노트북 배터리가 없어서.. 통과안되는데 일단 낼게요
//ㄹㅇ 거의다한거같은데.. 밤에 수정해서 다시 낼게용..
//사랑합니다 싸피여러분
public class BOJ18500 {
    
    static int N,M,B;
    static char[][] arr;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static boolean[][] visited;
    static Queue<int[]> q;
    static Queue<int[]> sub_q;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        arr = new char[N][];
        
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine().toCharArray();
        }
        
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            int x = Integer.parseInt(st.nextToken());
            if (i%2 == 0) {
                left(N-x);
            } else {
                right(N-x);
            }
            
            for (int j = 0; j < N; j++) {
            	System.out.println(Arrays.toString(arr[j]));
            }
            System.out.println("-------------------");
        }
        
        
    }

    private static void right(int idx) {
    	
        for (int j = M-1; j >= 0; j--) {
            if (arr[idx][j] == 'x') {
                arr[idx][j] = '.';
                
                bfs();
                
                break;
            }
        }
    }

    private static void left(int idx) {
    	
        for (int j = 0; j < M; j++) {
            if (arr[idx][j] == 'x') {
                arr[idx][j] = '.';
                
                bfs();
                
                break;
            }
        }
    }

    private static void bfs() {
    	
    	visited = new boolean[N][M];
        q = new ArrayDeque<int[]>();
        sub_q = new ArrayDeque<int[]>();
        
        for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 'x' && !visited[i][j]) {
					int down = Integer.MAX_VALUE;
					
					visited[i][j] = true;
					q.add(new int[] {i,j});
					sub_q.add(new int[] {i,j});
					
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						
						for (int k = 0; k < 4; k++) {
							int nx = cur[0] + dx[k];
							int ny = cur[1] + dy[k];
							
							if (!in_range(nx,ny)) continue;
							if (arr[nx][ny] == '.' || visited[nx][ny]) continue;
							
							down = Math.min(down, downLength(nx,ny));
							System.out.print(down + ", ");
							
							visited[nx][ny] = true;
							q.add(new int[] {nx,ny});
							sub_q.add(new int[] {nx,ny});
						}
					}
					
					if (down == Integer.MAX_VALUE || down == 0) continue;
					
					while (!sub_q.isEmpty()) {
						int[] cur = sub_q.poll();
						int nx = cur[0];
						int ny = cur[1];
						
						arr[nx][ny] = '.';
						arr[nx+down][ny] = 'x';
						System.out.println(nx + "," + ny);
					}
					
				}
			}
		}
        
    }

	private static int downLength(int x, int y) {
		if (x == N-1) return 0;
		
		for (int i = x+1; i < N; i++) {
			if (arr[i][y] == 'x')
				return (i-x);
		}
		return N-1-x;
	}

	private static boolean in_range(int a, int b) {
		return 0 <= a && a < N && 0 <= b && b < M;
	}
}