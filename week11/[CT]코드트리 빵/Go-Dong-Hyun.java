package week11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class CodeTree {
    
    static int N,M;
    static int[][] arr;
    static boolean[][] visited;    //못지나가는 빨간곳 체크
    static boolean[][] sub_visited;    //basecamp때 쓸놈
    static int time;
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    static int[][] store;    //얘는 안겹친다
    static int[][] man;    //현재 움직이는 위치
    static int[][] base;    //i번째가 i번째 출발한 사람의 basecamp
    static boolean[] finish;//i번째 끝났을때
    static Queue<int[]> baseQ;
    static Queue<int[]> manQ;
    static int m;

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        store = new int[M][2];
        man = new int[M][2];
        finish = new boolean[M];
        
        arr = new int[N][N];
        visited = new boolean[N][N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        base = new int[M][2];
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            arr[a][b] = 2;
            store[i][0] = a;
            store[i][1] = b;
        }
        
        time = 0;
        m = 0;
        
        baseQ = new ArrayDeque<int[]>();
        manQ = new ArrayDeque<>();
        
        while (true) {
            // 1. 본인이 가고싶은 편의점 방향으로 1칸 이동
            for (int i = 0; i < time; i++) {
                if (i >= finish.length ) break;
                if (!finish[i]) {    //도착 못한놈들
                    manToStore(i);
                }
            }
            
            // 2. 편의점에 도착하면 visited처리해서 못지나가게
            checkVisited();
            
            // 3. time<=M이면 t번사람은 가장 가까운 basecamp에 들어가서 방문처리
            if (time < M) {
                findBaseCamp(time);
            }
            
            if (M == m) break;
            time++;
        }
        System.out.println(time+1);
    }


    private static void checkVisited() {
        
        for (int i = 0; i < M; i++) {
            if (i == time) break;
            
            if (man[i][0] == store[i][0] && man[i][1] == store[i][1] && !finish[i]) {
            	visited[man[i][0]][man[i][1]] = true;
            	finish[i] = true;
            	m++;
            }
        }
        
    }


    private static void manToStore(int idx) {
        
        manQ = new ArrayDeque<>();
        sub_visited = new boolean[N][N];
        manQ.add(new int[] {man[idx][0], man[idx][1], -1});
        sub_visited[man[idx][0]][man[idx][1]] = true;
        
        while (!manQ.isEmpty()) {
            int[] cur = manQ.poll();
            
            if (cur[0] == store[idx][0] && cur[1] == store[idx][1]) {
                man[idx][0] += dx[cur[2]];
                man[idx][1] += dy[cur[2]];
                break;
            }
            
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                int dis = cur[2] == -1 ? d : cur[2];
                
                if (!in_range(nx,ny) || visited[nx][ny]) continue;
                
                if (sub_visited[nx][ny]) continue;
                
                manQ.add(new int[] {nx,ny,dis});
                sub_visited[nx][ny] = true;
                
            }
        }
    }

    private static void findBaseCamp(int idx) {
        
        baseQ = new ArrayDeque<>();
        sub_visited = new boolean[N][N];
        baseQ.add(new int[] {store[idx][0], store[idx][1], 0});
        sub_visited[store[idx][0]][store[idx][1]] = true;
        
        int r = Integer.MAX_VALUE;
        int c = Integer.MAX_VALUE;
        int dis = Integer.MAX_VALUE;
        
        while (!baseQ.isEmpty()) {
            int[] cur = baseQ.poll();
            
            if (arr[cur[0]][cur[1]] == 1) { //베이스캠프 도착했을때
                if (dis < cur[2]) break;    //가까운 베이스캠프가 없을때 
                
                if (r > cur[0] || (r == cur[0] && c > cur[1])) {
                    r = cur[0];
                    c = cur[1];
                    dis = cur[2];
                }
            }
            
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                
                if (!in_range(nx,ny) || sub_visited[nx][ny] || visited[nx][ny]) continue;
                
                baseQ.add(new int[] {nx,ny,cur[2]+1});
                sub_visited[nx][ny] = true;
            }
        }
        
        man[idx][0] = r;
        man[idx][1] = c;
        visited[r][c] = true;        
    }

    private static boolean in_range(int a, int b) {
        return 0 <= a && a < N && 0 <= b && b < N;
    }
}