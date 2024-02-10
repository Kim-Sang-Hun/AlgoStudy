import java.io.*;
import java.util.*;
/*
 * 제목
 * <벽 부수고 이동하기>
 * 링크
 * https://www.acmicpc.net/problem/2206
 * 요약
 * N * M 행렬에서 (1, 1) -> (N, M) 최단경로 구하는데, 벽 만나면 한 번 부수기 가능
 * 풀이
 * BFS + 벽 부쉈는지 판단
 */
public class boj_2206_2 {
    static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] dx = {0, 1, 0, -1};    // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};

    static char[][] map; // 맵
    static int[][] dist; // 거리 측정용
    static boolean[][][] visit;    // 벽을 부순 여부에 따라 방문 여부 기록
    static Queue<int[]> q = new ArrayDeque<>(); // bfs용
    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 시작==도착
        if(N-1 == 0 && M-1 == 0){
            System.out.print(1);
            System.exit(0);
        }
        map = new char[N][M];   
        dist = new int[N][M];  
        visit = new boolean[2][N][M];    // 벽을 부순 여부에 따라 방문 여부 기록
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        // 풀이
        q.offer(new int[]{0, 0, 0});   // 루트 (x, y, 벽부수기)
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            // 3시부터 시계방향으로 탐색
            for(int i=0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue; // 인덱스 범위 밖이면 continue

                if (map[nx][ny] == '1') {   // [nx][ny] 벽일 때
                    if(cur[2] == 0 && !visit[1][nx][ny]){ // cur[x][y] 까지 오면서 벽 부순적 있고 && [nx][ny] 벽 부순 적 없으면
                        visit[cur[2]][nx][ny] = true;    // 방문 처리
                        dist[nx][ny] = dist[cur[0]][cur[1]] + 1; // [nx][ny] 까지 거리 (직전+1)
                        q.offer(new int[]{nx, ny, 1}); 
                    }
                }
                else{ // [nx][ny] 벽 아닐 때
                    if(!visit[cur[2]][nx][ny]){ // [nx][ny] 방문한 적 없을 때
                        visit[cur[2]][nx][ny] = true;    // 방문 처리
                        dist[nx][ny] = dist[cur[0]][cur[1]] + 1; // [nx][ny] 까지 거리 (직전+1)
                        q.offer(new int[]{nx, ny, cur[2]}); 
                    }
                }
            
                if(nx == N-1 && ny == M-1){ // 도착 시
                    bw.write(""+(dist[nx][ny] + 1));
                    bw.flush();
                    return;
                }
            }
        }
        // 도달x
        bw.write("-1");
        bw.flush();
    }
}