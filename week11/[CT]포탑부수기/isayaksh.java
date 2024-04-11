import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {1, 0, -1, 0, -1, 1, -1, 1};
    private static final int[] dy = {0, 1, 0, -1, -1, -1, 1, 1};

    private static int N, M, K;

    private static int[][] towers;
    private static int[][] priority;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        towers = new int[N][M];
        priority = new int[N][M];

        for(int n = 0; n < N; n++) {
            towers[n] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        }
        
        for(int k = 0; k < K; k++) {

            if(countTower() == 1) break;

            visited = new boolean[N][M];

            // 1. 공격자 선정
            Tower attackTower = selectAttackTower(k+1);
            towers[attackTower.y][attackTower.x] += (N+M);
            visited[attackTower.y][attackTower.x] = true;
            priority[attackTower.y][attackTower.x] = k+1;

            // 2. 공격자의 공격
            Tower targetTower = selectTargetTower(attackTower);
            visited[targetTower.y][targetTower.x] = true;

            if(!laserAttack(attackTower, targetTower)) {
                bombAttack(attackTower, targetTower);
            }

            // 3. 포탑 부서짐
            for(int y = 0; y < N; y++) {
                for(int x = 0; x < M; x++) {
                    if(towers[y][x] < 0) towers[y][x] = 0;
                }
            }

            // 4. 포탑 정비
            repairingTower(attackTower, targetTower);

        }

        int answer = 0;

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(answer < towers[y][x]) answer = towers[y][x];
            }
        }

        System.out.println(answer);

    }

    private static boolean laserAttack(Tower attackTower, Tower targetTower) {

        int nx, ny;

        Deque<Tower> deque = new ArrayDeque<Tower>();
        int[][] dir = new int[N][M];

        deque.add(new Tower(attackTower.x, attackTower.y));
        for(int n = 0; n < N; n++) {
            Arrays.fill(dir[n], -1);
        }

        // 레이저 도달 가능한지 확인
        while(!deque.isEmpty()) {
            Tower tower = deque.poll();

            for(int i = 0; i < 4; i++) {
                nx = (tower.x + dx[i] + M)%M;
                ny = (tower.y + dy[i] + N)%N;

                if(dir[ny][nx] != -1 || towers[ny][nx] == 0) continue;

                dir[ny][nx] = (i+2)%4;
                deque.add(new Tower(nx, ny));
            }

        }

        

        // 레이저 도달 불가능
        if(dir[targetTower.y][targetTower.x] == -1) return false;

        int power = towers[attackTower.y][attackTower.x];

        nx = targetTower.x;
        ny = targetTower.y;

        towers[targetTower.y][targetTower.x] -= power;

        while(true) {
            int i = dir[ny][nx];
            nx = (nx + dx[i] + M) % M;
            ny = (ny + dy[i] + N) % N;

            if(nx == attackTower.x && ny == attackTower.y) break;

            towers[ny][nx] -= power/2;
            visited[ny][nx] = true;
        }

        return true;

    }

    private static void bombAttack(Tower attackTower, Tower targetTower) {
        int power = towers[attackTower.y][attackTower.x];
        int nx, ny;

        towers[targetTower.y][targetTower.x] -= power;

        for(int i = 0; i < 8; i++) {
            nx = (targetTower.x + dx[i] + M) % M;
            ny = (targetTower.y + dy[i] + N) % N;

            if(nx == attackTower.x && ny == attackTower.y) continue;

            towers[ny][nx] -= power/2;
            visited[ny][nx] = true;
        }

    }

    private static void repairingTower(Tower attackTower, Tower targetTower) {
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(x == attackTower.x && y == attackTower.y) continue;
                if(x == targetTower.x && y == targetTower.y) continue;
                if(towers[y][x] == 0) continue;
                if(visited[y][x]) continue;
                towers[y][x]++;
            }
        }
    }

    private static Tower selectAttackTower(int k) {
        Tower tower = new Tower(0, 0);
        int power = Integer.MAX_VALUE;

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {

                if(towers[y][x] <= 0) continue;

                // 1-1. 공격력이 가장 낮은 포탑이 가장 약한 포탑입니다.
                if(towers[y][x] < power) {
                    power = towers[y][x];
                    tower.x = x;
                    tower.y = y;
                    continue;
                } else if(towers[y][x] > power) continue;

                // 1-2. 가장 최근에 공격한 포탑이 가장 약한 포탑입니다. 모든 포탑은 시점 0에 모두 공격한 경험이 있다고 가정하겠습니다.
                if(priority[y][x] > priority[tower.y][tower.x]) {
                    tower.x = x;
                    tower.y = y;
                    continue;
                } else if(priority[y][x] < priority[tower.y][tower.x]) continue;

                // 1-3. 각 포탑 위치의 행과 열의 합이 가장 큰 포탑이 가장 약한 포탑입니다.
                if((tower.x + tower.y) < (x + y)) {
                    tower.x = x;
                    tower.y = y;
                    continue;
                } else if((tower.x + tower.y) > (x + y)) continue;

                // 1-4. 각 포탑 위치의 열 값이 가장 큰 포탑이 가장 약한 포탑입니다.
                if(tower.x < x) {
                    tower.x = x;
                    tower.y = y;
                }
            }
        }

        return tower;

    }

    private static Tower selectTargetTower(Tower attackTower) {
        Tower tower = new Tower(M-1, N-1);
        int power = -1;

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {

                if(towers[y][x] <= 0) continue;

                // 선정된 공격자는 자신을 제외한 가장 강한 포탑을 공격합니다.
                if(x == attackTower.x && y == attackTower.y) continue;

                // 1-1. 공격력이 가장 높은 포탑이 가장 강한 포탑입니다.
                if(power < towers[y][x]) {
                    power = towers[y][x];
                    tower.x = x;
                    tower.y = y;
                    continue;
                } else if(power > towers[y][x]) continue;

                // 1-2. 공격한지 가장 오래된 포탑이 가장 강한 포탑입니다. 모든 포탑은 시점 0에 모두 공격한 경험이 있다고 가정하겠습니다.
                if(priority[y][x] < priority[tower.y][tower.x]) {
                    tower.x = x;
                    tower.y = y;
                    continue;
                } else if(priority[y][x] > priority[tower.y][tower.x]) continue;

                // 1-3. 각 포탑 위치의 행과 열의 합이 가장 작은 포탑이 가장 강한 포탑입니다.
                if((x + y) < (tower.x + tower.y)) {
                    tower.x = x;
                    tower.y = y;
                } else if((x + y) > (tower.x + tower.y)) continue;

                // 1-4. 각 포탑 위치의 열 값이 가장 작은 포탑이 가장 강한 포탑입니다.
                if(x < tower.x) {
                    tower.x = x;
                    tower.y = y;
                }
            }
        }

        return tower;
    }

    private static int countTower() {
        int count = 0;
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(0 < towers[y][x]) count++;
            }
        }
        return count;
    }
    
    static class Tower {
        int x, y;
        Tower(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
}
