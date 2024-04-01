package programmers;

/*
 * 제목
 * <파괴되지 않은 건물> Lv3
 * 링크
 * https://school.programmers.co.kr/learn/courses/30/lessons/92344
 * 요약
 * -
 * 풀이
 * 2차원 배열에서의 누적합
 */
class prog_92344 {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        // 입력
        // board : [N][M] 크기 맵
        // skill : {타입[0], 시작n[1], 시작m[2], 종료n[3], 종료m[4], 값[5]}
        // 좌상, 우하 좌표 받아 해당 범위 사각형에 값 만큼 해당 타입으로 연산 (타입=1 : 빼기, 타입=2 : 더하기)
        int skillCnt = skill.length;
        int[][] sum = new int[board.length + 1][board[0].length + 1];
        // 풀이
        for (int i = 0; i < skillCnt; i++)
            doSkill(sum, skill[i][0], skill[i][1], skill[i][2], skill[i][3], skill[i][4], skill[i][5]);
        setboard(board, sum);
        answer = checkBoard(board);
        // 출력
        return answer;
    }

    private void doSkill(int[][] sum, int type, int r1, int c1, int r2, int c2, int degree) {
        if (type == 1)
            degree = -degree;
        sum[r1][c1] += degree;
        sum[r1][c2 + 1] += -degree;
        sum[r2 + 1][c1] += -degree;
        sum[r2 + 1][c2 + 1] += degree;
    }

    private void setboard(int[][] board, int[][] sum) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sum[i + 1][j] += sum[i][j];
            }
        }
        for (int j = 0; j < board[0].length; j++) {
            for (int i = 0; i < board.length; i++) {
                sum[i][j + 1] += sum[i][j];
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] += sum[i][j];
            }
        }
    }

    private int checkBoard(int[][] board) {
        int cnt = 0;
        int N = board.length;
        int M = board[0].length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0)
                    cnt++;
            }
        }
        return cnt;
    }

}
