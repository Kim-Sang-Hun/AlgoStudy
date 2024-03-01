import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, k;

    //남은 시간, 지나간 상어의 번호를 남긴다.
    static class Smell {
        int time, number;

        public Smell(int time, int number) {
            this.time = time;
            this.number = number;
        }
    }

    //현 위치와 가리키는 방향을 남긴다.
    //한편으로는 방향 별 우선순위를 가지기도 한다.
    static class Shark {
        int x, y, dir;
        int[][] pDir = new int[5][5];

        public Shark(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static Smell[][] smell;
    static Shark[] shark;
    static int[][] area, tmp;
    static boolean[] isDead;
    static final int[] dx = {0, -1, 1, 0, 0};
    static final int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }

    //시간이 지났으니 모든 향을 1초씩 감소시킨다.
    //이미 쫓겨난 놈은 무시하고 다른 도착한 상어들의 시간을 새로 채워준다.
    static void smellProcess() {
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (--smell[i][j].time == 0) smell[i][j].number = 0;
            }
        }
        for (int i = 1; i <= m; ++i) {
            if (isDead[i]) continue;
            smell[shark[i].x][shark[i].y] = new Smell(k, i);
        }
    }

    //방향을 찾고, 해당 방향으로 이동한다.
    //해당 메서드를 통해 방향 지정 및 이동을 실행한다.
    static void move(int i) {
        Shark s = shark[i];
        //1. 인접 칸 중에 아무 냄새가 없는 칸의 방향을 탐색한다.
        for (int dir = 1; dir <= 4; ++dir) {
            int nd = s.pDir[s.dir][dir];
            int nx = s.x + dx[nd];
            int ny = s.y + dy[nd];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (smell[nx][ny].number > 0) continue;
            s.dir = nd;
            s.x = nx;
            s.y = ny;
            return;
        }
        //2. 그런 칸이 없을 경우 자신의 냄새가 있는 칸의 방향을 잡는다.
        for (int dir = 1; dir <= 4; ++dir) {
            int nd = s.pDir[s.dir][dir];
            int nx = s.x + dx[nd];
            int ny = s.y + dy[nd];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (smell[nx][ny].number != i) continue;
            s.dir = nd;
            s.x = nx;
            s.y = ny;
            return;
        }
    }

    //해당 방향으로 이동했으니, 조건에 따라 킬각 확실하게 본다.
    static void afterMove(int i) {
        Shark s = shark[i];
        int nx = s.x;
        int ny = s.y;
        //1. 아무도 없으니까 들어가자~
        if(tmp[nx][ny] == 0){
            tmp[nx][ny] = i;
        }
        //2. 기존에 상어 있는데 더 센 놈이다.
        //그럼 괜히 가서 죽는거지 뭐
        else if(tmp[nx][ny] > i){
            isDead[tmp[nx][ny]] = true;
            tmp[nx][ny] = i;
        }
        //3. 야 가서 잡아먹어
        else{
            isDead[i] = true;
        }
        for (int j = 0; j < n; ++j) {
            for (int k = 0; k < n; ++k) {
                area[j][k] = tmp[j][k];
            }
        }
    }

    //1번 상어만 살아있는지 확인하는 메서드
    static boolean onlyOneServive(){
        for (int i = 2; i <= m; ++i) {
            if(!isDead[i]) return false;
        }
        return true;
    }

    //어차피 1000만 넘지 않으면 그 순간 바로 리턴해버려도 되므로 for문으로 직관적이게 작성
    //1000을 넘으면 for문을 탈출하고 -1을 리턴한다.
    static void solution() {
        for(int time = 1;time <= 1000; ++time) {
            tmp = new int[n][n];
            for (int i = 1; i <= m; ++i) {
                if(isDead[i]) continue;
                move(i);
                afterMove(i);
            }
            smellProcess();
            if(onlyOneServive()) {
                System.out.println(time);
                return;
            }
        }
        System.out.println(-1);
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        area = new int[n][n];
        smell = new Smell[n][n];
        isDead = new boolean[m + 1];
        shark = new Shark[m + 1];
        for (int i = 0; i < n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; ++j) {
                smell[i][j] = new Smell(0, 0);
                area[i][j] = Integer.parseInt(st.nextToken());
                if (area[i][j] > 0) {
                    shark[area[i][j]] = new Shark(i, j, 0);
                    smell[i][j].time = k;
                    smell[i][j].number = area[i][j];
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; ++i) {
            shark[i].dir = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= 4; ++j) {
                st = new StringTokenizer(br.readLine());
                for (int k = 1; k <= 4; ++k) {
                    shark[i].pDir[j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }
}
