import java.io.*;
import java.util.*;

public class isayaksh {

    private static int N, M;
    private static int[][] lab;
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];
        for(int y=0; y<N; y++) {
            lab[y] = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        // 0. 변수 선언
        int answer = 0;

        // 1. virus의 위치 찾기
        List<Point> virusList = findVirus();

        // 2. 3개의 벽 세우기
        for(int i=0; i<N*M-2; i++) {
            for(int j=i+1; j<N*M-1; j++) {
                for(int k=j+1; k<N*M; k++) {
                    if(!isPossibleToBuildWall(i, j, k)) continue;

                    // 2-1. 벽 세우기
                    lab[i/M][i%M] = 1;
                    lab[j/M][j%M] = 1;
                    lab[k/M][k%M] = 1;

                    // 2-2. 안전구역 크기 계산
                    answer = Math.max(answer, bfs(virusList));

                    // 2-3. 벽 허물기
                    lab[i/M][i%M] = 0;
                    lab[j/M][j%M] = 0;
                    lab[k/M][k%M] = 0;

                }
            }
        }

        System.out.println(answer);

    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    // virus의 (x, y)를 탐색
    private static List<Point> findVirus() {
        List<Point> virusList = new ArrayList<Point>();
        for(int y=0; y<N; y++) {
            for(int x=0; x<M; x++) {
                if(lab[y][x] == 2)
                    virusList.add(new Point(x, y));
            }
        }
        return virusList;
    }

    // 3개의 Point(i, j, k)에 벽 세우기 가능?
    private static boolean isPossibleToBuildWall(int i, int j, int k) {
        return ((lab[i/M][i%M] == 0) && (lab[j/M][j%M] == 0) && (lab[k/M][k%M] == 0));
    }

    // 너비 우선 탐색(dfs)으로 안전구역 크기 계산
    private static int bfs(List<Point> virusList) {
        int count = 0;
        int nx, ny;

        boolean[][] visited = new boolean[N][M];
        Deque<Point> queue = new ArrayDeque<Point>();

        for(Point virus : virusList) {
            visited[virus.y][virus.x] = true;
            queue.add(new Point(virus.x, virus.y));
        }

        while(!queue.isEmpty()) {
            Point point = queue.poll();
            
            for(int i=0; i<4; i++) {
                nx = point.x + dx[i];
                ny = point.y + dy[i];

                if(0 > nx || nx >= M || 0 > ny || ny >= N || visited[ny][nx] || lab[ny][nx] != 0) continue;

                visited[ny][nx] = true;
                queue.add(new Point(nx, ny));
                
            }

        }

        for(int y=0; y<N; y++) {
            for(int x=0; x<M; x++) {
                if(!visited[y][x] && lab[y][x] == 0) count++;
            }
        }

        return count;
    }

}