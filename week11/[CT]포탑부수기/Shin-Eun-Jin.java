import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 포탑부수기 {
    static int N, M, K;
    static int attackerRow, attackerCol, targetRow, targetCol;
    static boolean isAttack;
    static int[][] turret;
    static int[][] turretAfterAttack;
    static int[][] attackTime;
    static int[] dr = {0, 1, 0, -1, -1, -1, 1, 1};
    static int[] dc = {1, 0, -1, 0, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 행 개수
        M = Integer.parseInt(st.nextToken()); // 열 개수
        K = Integer.parseInt(st.nextToken()); // 턴 횟수
        turret = new int[N][M];
        attackTime = new int[N][M]; // 공격 정보 입력

        // 격자 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                turret[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 턴 시작
        for (int k = 1; k <= K; k++) {

            // 부서지지 않은 탑이 1개일 경우 중지
            if (isClose()) {
                break;
            }

            // 1. 공격자 선정
            selectAttacker();
            attackTime[attackerRow][attackerCol] = k; // 공격 시간 업데이트

            // 2. 공격자의 공격
            attackTurret();


            // 3. 포탑 부서짐
            breakTurret();


            // 4. 포탑 정비
            fixTurret();

        }

        // 남아있는 포탑 중 가장 강한 포탑의 공격력을 출력
        int maxPower = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maxPower = Math.max(turret[i][j], maxPower);
            }
        }

        System.out.println(maxPower);
    }

    public static void selectAttacker() {

        int power = Integer.MAX_VALUE;
        int time = -1;
        int row = -1;
        int col = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 부서진 포탑 공격 못함
                if (turret[i][j] == 0)
                    continue;

                // 가장 약한 포탑 선정
                if (turret[i][j] < power ||                                                                               // 공격력 낮을 경우
                        ((turret[i][j] == power) && (attackTime[i][j] > time)) ||                                         // 가장 최근에 공격 했을 경우
                        ((turret[i][j] == power) && (attackTime[i][j] == time) && (i + j > row + col)) ||                 // 행과 열의 합이 큰 포탑일 경우
                        ((turret[i][j] == power) && (attackTime[i][j] == time) && (i + j == row + col) && (j > col))) {   // 열 값이 큰 포탑일 경우

                    power = turret[i][j];
                    time = attackTime[i][j];
                    row = i;
                    col = j;
                }
            }
        }
        attackerRow = row;
        attackerCol = col;
        turret[attackerRow][attackerCol] += (N + M); // 공격자 공격력 증가
    }

    public static void selectTarget() {

        int power = Integer.MIN_VALUE;
        int time = 1001;
        int row = 11;
        int col = 11;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 부서진 포탑 공격 못함
                if (turret[i][j] == 0)
                    continue;
                
                // 공격자로 선정된 포탑 제외
                if(i == attackerRow && j == attackerCol) {
                	continue;
                }

                // 가장 강한 포탑 선정
                if (turret[i][j] > power ||                                                                               // 공격력 클 경우
                        ((turret[i][j] == power) && (attackTime[i][j] < time)) ||                                         // 공격한지 가장 오래됐을 경우
                        ((turret[i][j] == power) && (attackTime[i][j] == time) && (i + j < row + col)) ||                 // 행과 열의 합이 작은 포탑일 경우
                        ((turret[i][j] == power) && (attackTime[i][j] == time) && (i + j == row + col) && (j < col))) {   // 열 값이 작은 포탑일 경우

                    power = turret[i][j];
                    time = attackTime[i][j];
                    row = i;
                    col = j;
                }
            }
        }

        targetRow = row;
        targetCol = col;
    }

    public static void attackTurret() {

        // 공격자 선정
        selectTarget();

        // 레이저 공격
        isAttack = false;
        turretAfterAttack = copyArr(turret);
        goLaserAttack();

        // 레이저 공격 성공 시 공격 종료
        if (isAttack) {
            return;
        }

        // 포탄 공격
        goFireAttack();
    }

    public static void goLaserAttack() {
        Queue<Node> queue = new ArrayDeque<>();
        boolean[][] isVisit = new boolean[N][M];
        queue.offer(new Node(attackerRow, attackerCol, new ArrayList<>()));
        isVisit[attackerRow][attackerCol] = true;

        while(!queue.isEmpty()) {

            Node cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nextRow = cur.r + dr[d];
                int nextCol = cur.c + dc[d];

                if(nextRow == -1) {
                	nextRow += N;
                }else if(nextRow == N) {
                	nextRow -= N;
                }
                
                if(nextCol == -1) {
                	nextCol += M;
                }else if(nextCol == M) {
                	nextCol -= M;
                }

                if (turretAfterAttack[nextRow][nextCol] <= 0 || isVisit[nextRow][nextCol]) {
                    continue;
                }

                // 타켓인 경우
                if (nextRow == targetRow && nextCol == targetCol) {
                    // 타켓 공격
                    turretAfterAttack[nextRow][nextCol] -= turretAfterAttack[attackerRow][attackerCol];

                    // 지나온 나머지 애들 공격
                    for(int nodeIdx : cur.preNodes) {
                        turretAfterAttack[nodeIdx / M][nodeIdx % M] -= turretAfterAttack[attackerRow][attackerCol]/2;
                    }

                    isAttack = true;
                    return;
                }

                ArrayList<Integer> nextNodes = copylist(cur.preNodes);
                nextNodes.add(nextRow * M + nextCol);
                isVisit[nextRow][nextCol] = true;
                queue.offer(new Node(nextRow, nextCol, nextNodes));

            }
        }
    }

    public static void goFireAttack() {
        // 타켓 공격
        turretAfterAttack[targetRow][targetCol] -= turretAfterAttack[attackerRow][attackerCol];

        for (int d = 0; d < 8; d++) {
            int nextRow = targetRow + dr[d];
            int nextCol = targetCol + dc[d];

//            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
//                continue;
//            }
            if(nextRow == -1) {
            	nextRow += N;
            }else if(nextRow == N) {
            	nextRow -= N;
            }
            
            if(nextCol == -1) {
            	nextCol += M;
            }else if(nextCol == M) {
            	nextCol -= M;
            }

            if (nextRow == attackerRow && nextCol == attackerCol) {
                continue;
            }

            if (turretAfterAttack[nextRow][nextCol] <= 0) {
                continue;
            }

            turretAfterAttack[nextRow][nextCol] -= turretAfterAttack[attackerRow][attackerCol] / 2;
        }
    }

    public static void breakTurret() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (turretAfterAttack[i][j] < 0) {
                    turretAfterAttack[i][j] = 0;
                }
            }
        }
    }

    public static void fixTurret() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ((i == attackerRow && j == attackerCol) || (i == targetRow && j == targetCol)) {
                    continue;
                }

                if (turretAfterAttack[i][j] == 0) {
                    continue;
                }

                if (turret[i][j] == turretAfterAttack[i][j]) {
                    turretAfterAttack[i][j] += 1;
                }
            }
        }

        turret = turretAfterAttack;
    }

    public static boolean isClose() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (turret[i][j] != 0) {
                    cnt++;
                }
            }
        }

        return cnt <= 1;
    }

    public static int[][] copyArr(int[][] arr) {
        int[][] copiedArr = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copiedArr[i][j] = arr[i][j];
            }
        }
        return copiedArr;
    }

    public static ArrayList<Integer> copylist(ArrayList<Integer> list) {
        ArrayList<Integer> copiedList = new ArrayList<>();

        for(Integer n : list) {
            copiedList.add(n);
        }

        return copiedList;
    }

    static class Node {
        int r;
        int c;
        ArrayList<Integer> preNodes;

        public Node(int r, int c, ArrayList<Integer> preNodes) {
            this.r = r;
            this.c = c;
            this.preNodes = preNodes;
        }
    }

}
