// package
import java.io.*;
import java.util.*;
import java.awt.Point;
/*
 * 제목
 * <토마토>
 * 링크
 * https://www.acmicpc.net/problem/7576
 * 요약
 * M*N 배열 내 모든 토마토가 익기 위해 걸리는 최소 일수
 * 풀이
 * bfs
 */
public class boj_7576 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;

	static int N, M;    // 토마토 상자 크기 N * M
    static int[][] box; // 1 익토, 0 안익토, -1 빈칸
    static int cnt;     // 일수
    static Queue<Point> queue = new ArrayDeque<>(); // BFS queue
    static int[] dx = {-1, 1, 0, 0};    // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};    
    public static void main(String[] args) throws IOException {
		// 입력
		st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
                if(box[i][j]==1) queue.offer(new Point(i, j)); // 루트 삽입
            }
        }
		// 풀이
		bfs();
		// 출력
		bw.write(cnt+"\n");
		bw.flush();
	}

	private static void bfs() {
        int size;
		while(!queue.isEmpty()) {
            size = queue.size();
            while(size-->0) { // size = 현재 레벨 노드 개수
                // poll
                Point p = queue.poll(); 
                // 사방탐색하고 큐에 삽입
                for(int i=0; i<4; i++) {
                    int nx = (int)p.getX()+dx[i];
                    int ny = (int)p.getY()+dy[i];
                    if(nx<0 || nx>=N || ny<0 || ny>=M) continue; // 범위 체크
                    if(box[nx][ny]!=0) continue; // 안익은 토마토인지 체크
                    box[nx][ny] = 1;
                    queue.offer(new Point(nx, ny));
                }
            }
            cnt++; // 일수 + 1
        }
        cnt--; // 큐에서 뽑은 마지막 익은 토마토에서 cnt++ 된 거 조정
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                 if(box[i][j]==0) {  // 익지 않은 토마토가 있으면 -1
                     cnt= -1;        
                     break;
                 }
            }
        }
	}
}

