import java.util.*;
import java.io.*;

public class isayaksh {

    private static int N;
    private static int totalPeople;
    private static int[][] graph;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        graph = new int[N+1][N+1];

        for(int r = 1; r < N+1; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c < N+1; c++) {
                graph[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        totalPeople = countTotalPeople();

        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                for(int d1 = 1; d1 < N - 1; d1++) {
                    for(int d2 = 1; d2 < N - d1; d2++) {
                        if(isPossible(x, y, d1, d2)) {
                            answer = Math.min(answer, seperate(x, y, d1, d2));
                        }
                    }
                }
            }
        }

        System.out.println(answer);

    }

    private static boolean isPossible(int x, int y, int d1, int d2) {
        return (x+d1+d2 <= N) && (0 < y-d1) && (y+d2 <= N);
    }

    private static int seperate(int x, int y, int d1, int d2) {
        int[] population = new int[6];
        visited = new boolean[N+1][N+1];

        // 5번 선거구 경계
        for(int dd1 = 0; dd1 <= d1; dd1++) {
            if(!visited[y-dd1][x+dd1]) visited[y-dd1][x+dd1] = true;
            if(!visited[y+d2-dd1][x+d2+dd1]) visited[y+d2-dd1][x+d2+dd1] = true;
        }
        for(int dd2 = 0; dd2 <= d2; dd2++) {
            if(!visited[y+dd2][x+dd2]) visited[y+dd2][x+dd2] = true;
            if(!visited[y-d1+dd2][x+d1+dd2]) visited[y-d1+dd2][x+d1+dd2] = true;
        }

        // 1번 선거구
        for(int r = 1; r < y; r++) {
            for(int c = 1; c <= x+d1; c++) {
                if(visited[r][c]) break;
                population[1] += graph[r][c];
            }
        }

        // 2번 선거구
        for(int r = 1; r <= y-d1+d2; r++) {
            for(int c = N; c > x+d1; c--) {
                if(visited[r][c]) break;
                population[2] += graph[r][c];
            }
        }

        // 3번 선거구
        for(int r = y; r <= N; r++) {
            for(int c = 1; c < x+d2; c++) {
                if(visited[r][c]) break;
                population[3] += graph[r][c];
            }
        }

        // 4번 선거구
        for(int r = y-d1+d2+1; r <= N; r++) {
            for(int c = N; c >= x+d2; c--) {
                if(visited[r][c]) break;
                population[4] += graph[r][c];
            }
        }

        // 5번 선거구
        population[5] = totalPeople - population[1] - population[2] - population[3] - population[4];
        
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for(int i = 1; i <= 5; i++) {
            if(max < population[i]) max = population[i];
            if(min > population[i]) min = population[i];
        }

        return max - min;

    }

    private static int countTotalPeople() {
        int count = 0;
        for(int y = 1; y < N+1; y++) {
            for(int x = 1; x < N+1; x++) {
                count += graph[y][x];
            }
        }
        return count;
    }

}
