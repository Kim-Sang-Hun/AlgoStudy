import java.util.*;
import java.io.*;

public class isayaksh {

    private static int N;
    private static int[][] graph;

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

        for(int y = 1; y <= N; y++) {
            for(int x = 1; x <= N; x++) {
                for(int d1 = 1; d1 < N - 1; d1++) {
                    for(int d2 = 1; d2 < N - d1; d2++) {
                        if(isPossible(x, y, d1, d2)) {
                            System.out.println("[x : " + x + ", y : " + y + ", d1 : " + d1 + ", d2 : " + d2 + "]");
                            answer = Math.max(answer, seperate(x, y, d1, d2));
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
        int[] population = new int[5];
        boolean[][] visited = new boolean[N+1][N+1];

        for(int dx = 0; dx <= d1+d2; dx++) {
            
            if(dx <= (d1+d2)/2) {
                for(int dy = 0 - dx; dy < dx + 1; dy++) {
                    visited[y+dy][x+dx] = true;
                    population[4] += graph[y+dy][x+dx];
                }
            }

            if((d1+d2)/2 < dx) {
                for(int dy = dx - (d1+d2) - 1; dy < (d1+d2) - dx; dy++) {
                    System.out.println("dy : " + dy +  ", dx : " + dx);
                    visited[y+dy][x+dx] = true;
                    population[4] += graph[y+dy][x+dx];
                }
            }

        }
        
        for(int r = 1; r < N+1; r++) {
            for(int c = 1; c < N+1; c++) {

                if(visited[r][c]) continue;
                
                // 1번 선거구
                else if(1 <= r && r < x + d1 && 1 <= c && c <= y) {
                    population[0] += graph[r][c];
                }

                // 2번 선거구
                else if(1 <= r && r <= x+d2 && y < c && c <= N) {
                    population[1] += graph[r][c];
                }

                // 3번 선거구
                else if(x+d1 <= r && r <= N && 1 <= c && c < y-d1+d2) {
                    population[2] += graph[r][c];
                }

                // 4번 선거구
                else if(x+d2 < r && r <= N && y-d1+d2 <= c && c <= N) {
                    population[3] += graph[r][c];
                }

            }
        }

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for(int p : population) {
            if(max < p) max = p;
            if(min > p) min = p;
        }

        return max - min;

    }

}