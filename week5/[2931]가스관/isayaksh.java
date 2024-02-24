import java.io.*;
import java.util.*;

public class isayaksh {

    // pipeConnection[사방][파이프모양]
    private static final boolean[][] isPossible = {
        {false, true, false, false, true, true, false, true, false, false},
        {false, false, false, true, true, false, true, true, false, false},
        {false, false, true, true, false, true, false, true, false, false},
        {false, true, true, false, false, false, true, true, false, false}
    };

    private static int R, C;
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new int[R][C];

        for(int r = 0; r < R; r++) {
            String[] input = br.readLine().split("");

            for(int c = 0; c < C; c++) {
                if(input[c].equals(".")) graph[r][c] = 0;
                if(input[c].equals("|")) graph[r][c] = 5;
                if(input[c].equals("-")) graph[r][c] = 6;
                if(input[c].equals("+")) graph[r][c] = 7;
                if(input[c].equals("M")) graph[r][c] = 8;
                if(input[c].equals("Z")) graph[r][c] = 9;
                if(input[c].matches("[1-4]")) graph[r][c] = Integer.parseInt(input[c]);
            }

        }

        for(int y = 0; y < R; y++) {
            for(int x = 0; x < C; x++) {

                if(graph[y][x] != 0) continue;

                // +
                if(0 <= y-1 && x+1 < C && y+1 < R && 0 <= x-1) {
                    if(isPossible[0][graph[y-1][x]] && isPossible[1][graph[y][x+1]] && isPossible[2][graph[y+1][x]] && isPossible[3][graph[y][x-1]]) {
                        System.out.println((y+1) + " " + (x+1) + " +");
                        return;
                    }
                }

                // -
                if(x+1 < C && 0 <= x-1) {
                    if(isPossible[1][graph[y][x+1]] && isPossible[3][graph[y][x-1]]) {
                        System.out.println((y+1) + " " + (x+1) + " -");
                        return;
                    }
                }

                // |
                if(0 <= y-1 && y+1 < R) {
                    if(isPossible[0][graph[y-1][x]] && isPossible[2][graph[y+1][x]]) {
                        System.out.println((y+1) + " " + (x+1) + " |");
                        return;
                    }
                }

                // 4
                if(y+1 < R && 0 <= x-1) { // 2, 3
                    if(isPossible[2][graph[y+1][x]] && isPossible[3][graph[y][x-1]]) {
                        System.out.println((y+1) + " " + (x+1) + " 4");
                        return;
                    }
                }

                // 3
                if(0 <= y-1 && 0 <= x-1) { // 0, 3
                    if(isPossible[0][graph[y-1][x]] && isPossible[3][graph[y][x-1]]) {
                        System.out.println((y+1) + " " + (x+1) + " 3");
                        return;
                    }
                }

                // 2
                if(0 <= y-1 && x+1 < R) { // 0, 1
                    if(isPossible[0][graph[y-1][x]] && isPossible[1][graph[y][x+1]]) {
                        System.out.println((y+1) + " " + (x+1) + " 2");
                        return;
                    }
                }

                // 1
                if(x+1 < C && y+1 < R) { // 1, 2
                    if(isPossible[1][graph[y][x+1]] && isPossible[2][graph[y+1][x]]) {
                        System.out.println((y+1) + " " + (x+1) + " 1");
                        return;
                    }
                }

            }
        }
    }
}
