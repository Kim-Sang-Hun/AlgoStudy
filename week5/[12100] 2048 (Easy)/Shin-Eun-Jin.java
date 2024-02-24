import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Shin-Eun-Jin {
    static int N;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 보드 크기 입력
        N = Integer.parseInt(br.readLine());

        // 보드 상태 입력
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxBlock = 0;

        // 최대 5번 이동
        for (int move = 0; move < 5; move++) {
            for (int direction = 0; direction < 4; direction++) {
                int[][] newBoard = moveBoard(direction);
                maxBlock = Math.max(maxBlock, getMaxBlock(newBoard));
                board = newBoard;
            }
        }

        System.out.println(maxBlock);
    }

    // 현재 방향으로 보드 이동
    static int[][] moveBoard(int direction) {
        int[][] newBoard = new int[N][N];

        if (direction == 0) { // 위로 이동
            for (int col = 0; col < N; col++) {
                int idx = 0;
                for (int row = 0; row < N; row++) {
                    if (board[row][col] != 0) {
                        if (newBoard[idx][col] == 0) {
                            newBoard[idx][col] = board[row][col];
                        } else if (newBoard[idx][col] == board[row][col]) {
                            newBoard[idx][col] *= 2;
                            idx++;
                        } else {
                            idx++;
                            newBoard[idx][col] = board[row][col];
                        }
                    }
                }
            }
        } else if (direction == 1) { // 아래로 이동
            for (int col = 0; col < N; col++) {
                int idx = N - 1;
                for (int row = N - 1; row >= 0; row--) {
                    if (board[row][col] != 0) {
                        if (newBoard[idx][col] == 0) {
                            newBoard[idx][col] = board[row][col];
                        } else if (newBoard[idx][col] == board[row][col]) {
                            newBoard[idx][col] *= 2;
                            idx--;
                        } else {
                            idx--;
                            newBoard[idx][col] = board[row][col];
                        }
                    }
                }
            }
        } else if (direction == 2) { // 왼쪽으로 이동
            for (int row = 0; row < N; row++) {
                int idx = 0;
                for (int col = 0; col < N; col++) {
                    if (board[row][col] != 0) {
                        if (newBoard[row][idx] == 0) {
                            newBoard[row][idx] = board[row][col];
                        } else if (newBoard[row][idx] == board[row][col]) {
                            newBoard[row][idx] *= 2;
                            idx++;
                        } else {
                            idx++;
                            newBoard[row][idx] = board[row][col];
                        }
                    }
                }
            }
        } else if (direction == 3) { // 오른쪽으로 이동
            for (int row = 0; row < N; row++) {
                int idx = N - 1;
                for (int col = N - 1; col >= 0; col--) {
                    if (board[row][col] != 0) {
                        if (newBoard[row][idx] == 0) {
                            newBoard[row][idx] = board[row][col];
                        } else if (newBoard[row][idx] == board[row][col]) {
                            newBoard[row][idx] *= 2;
                            idx--;
                        } else {
                            idx--;
                            newBoard[row][idx] = board[row][col];
                        }
                    }
                }
            }
        }

        return newBoard;
    }

    // 보드에서 최대 블록 값 찾기
    static int getMaxBlock(int[][] board) {
        int maxBlock = 0;
        for (int[] row : board) {
            for (int value : row) {
                maxBlock = Math.max(maxBlock, value);
            }
        }
        return maxBlock;
    }
}
