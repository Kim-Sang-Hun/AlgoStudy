/*
*  누적합으로 풀어야 시간초과가 나지 않는다. skill이 30만이고 최대 크기가 100만이므로
*  모두 더해주는 방식으로 하면 무조건 시간초과가 나게 된다.
*  따라서 acc배열을 따로 두고 이벤트처리를 해주면 된다. 이벤트 시작점에 더해주고 이벤트가 끝나는점 + 1의 좌표에
*  그 값을 빼준다. [y + 1][x + 1] 좌표에는 두번 빼준 셈이므로 다시 더해준다.
*  그런 후에 누적합 방식으로 처리해주면 된다.
*
*/

class Solution {
    public int solution(int[][] board, int[][] skill) {
        
        int N = board.length;
        int M = board[0].length;
        int[][] acc = new int[N + 1][M + 1];
        
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][5];
            
            if (type == 1) degree *= -1;
            acc[r1][c1] += degree;
	          acc[r1][c2 + 1] -= degree;
	          acc[r2 + 1][c1] -= degree;
	          acc[r2 + 1][c2 + 1] += degree;
        }
        
        int cnt = 0;
        // 상하
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                acc[i][j] += acc[i - 1][j];
            }
        }
        // 좌우
        for (int j = 1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                acc[i][j] += acc[i][j - 1];
            }
        }      
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] + acc[i][j] > 0) ++cnt;
            }
        }

        return cnt;
    }
}
