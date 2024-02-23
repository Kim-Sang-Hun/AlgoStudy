import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        int[][] board = new int[N][N];
        for(int y = 0; y < N; y++) {
            board[y] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        }

        dfs(0, board);

        System.out.println(answer);

    }

    private static void dfs(int depth, int[][] board) {
        
        // termination condition
        if(depth == 5) {
            answer = Math.max(answer, findLargestBlock(board));
            return;
        }

        // recursion
        for(int type = 0; type < 4; type++) {
            switch (type) {
                case 0:
                    int[][] moveBoard1 = moveUp(board);
                    dfs(depth+1, moveBoard1);
                    break;
                case 1:
                    int[][] moveBoard2 = moveDown(board);
                    dfs(depth+1, moveBoard2);
                    break;
                
                case 2:
                    int[][] moveBoard3 = moveLeft(board);
                    dfs(depth+1, moveBoard3);
                    break;

                case 3:
                    int[][] moveBoard4 = moveRight(board);
                    dfs(depth+1, moveBoard4);
                    break;
                default:
                    break;
            }

        }

    }

    private static int findLargestBlock(int[][] board) {
        int block = 0;
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                if(block < board[y][x]) block = board[y][x];
            }
        }
        return block;
    }

    private static int[][] moveUp(int[][] board) {
        int[][] newBoard = deepCopy(board);
        boolean[][] merged = new boolean[N][N];

        int ny;

        for(int y = 1; y < N; y++) {
            for(int x = 0; x < N; x++) {
                ny = y;
                while(ny-1 >= 0 && newBoard[ny-1][x] == 0) ny--;

                // merge
                if(ny-1 >= 0 && newBoard[y][x] == newBoard[ny-1][x] && !merged[ny-1][x]) {
                    merged[ny-1][x] = true;
                    ny--;
                }

                // not move
                if(ny == y) continue;

                // move
                newBoard[ny][x] += newBoard[y][x];
                newBoard[y][x] = 0;

            }
        }

        return newBoard;
    }

    private static int[][] moveDown(int[][] board) {
        int[][] newBoard = deepCopy(board);
        boolean[][] merged = new boolean[N][N];

        int ny;

        for(int y = N-2; y >= 0; y--) {
            for(int x = 0; x < N; x++) {
                ny = y;
                while(ny+1 < N && newBoard[ny+1][x] == 0) ny++;

                // merge
                if(ny+1 < N && newBoard[y][x] == newBoard[ny+1][x] && !merged[ny+1][x]) {
                    merged[ny+1][x] = true;
                    ny++;
                }

                // not move
                if(ny == y) continue;

                // move
                newBoard[ny][x] += newBoard[y][x];
                newBoard[y][x] = 0;
            }
        }
        
        return newBoard;
    }

    private static int[][] moveLeft(int[][] board) {
        int[][] newBoard = deepCopy(board);
        boolean[][] merged = new boolean[N][N];

        int nx;

        for(int y = 0; y < N; y++) {
            for(int x = 1; x < N; x++) {
                nx = x;
                while(nx-1 >= 0 && newBoard[y][nx-1] == 0) nx--;

                // merge
                if(nx-1 >= 0 && newBoard[y][x] == newBoard[y][nx-1] && !merged[y][nx-1]) {
                    merged[y][nx-1] = true;
                    nx--;
                }

                // not move
                if(nx == x) continue;

                // move
                newBoard[y][nx] += newBoard[y][x];
                newBoard[y][x] = 0;
            }
        }

        return newBoard;
    }

    private static int[][] moveRight(int[][] board) {
        int[][] newBoard = deepCopy(board);
        boolean[][] merged = new boolean[N][N];

        int nx;

        for(int y = 0; y < N; y++) {
            for(int x = N-2; x >= 0; x--) {
                nx = x;
                while(nx+1 < N && newBoard[y][nx+1] == 0) nx++;

                // merge
                if(nx+1 < N && newBoard[y][x] == newBoard[y][nx+1] && !merged[y][nx+1]) {
                    merged[y][nx+1] = true;
                    nx++;
                }

                // not move
                if(nx == x) continue;

                // move
                newBoard[y][nx] += newBoard[y][x];
                newBoard[y][x] = 0;
            }
        }

        return newBoard;
    }

    private static int[][] deepCopy(int[][] board) {
        int[][] copy = new int[N][N];

            for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                copy[y][x] = board[y][x];
            }
        }

        return copy;
    }

}
