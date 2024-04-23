import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;
/*
  Title: 제곱수 찾기
  Tier: Gold 5
  Algorithm: Brute Force
  Constraint: 2 Second, 128MB
  Comment: 두통이 안 가셔서 그냥 곧이곧대로 풀었습니다.. 이후 다른 분들 코드 보고 좀 배울게요
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, answer = -1;
    static char[][] area;
    static boolean[] vis;

    static final int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
    static final int[] dy = {1, 0, -1, 0, 1, -1, 1, -1};

    static void dfs(int x, int y, int dir, int xCnt, int yCnt, String num){
        int nx = x + dx[dir] * xCnt;
        int ny = y + dy[dir] * yCnt;
        if(num.isEmpty()) return;
        int number = Integer.parseInt(num);
        if (isComplete(number)) {
            answer = Math.max(answer, number);
        }
        if(range(nx, ny)){
            return;
        }
        dfs(nx, ny, dir, xCnt, yCnt, num + area[nx][ny]);
    }

    static boolean isComplete(int number) {
        int tmp = (int)Math.sqrt(number);
        return tmp * tmp == number;
    }

    static void solution() {
        for(int xCnt = 1; xCnt <= 9; ++xCnt) {
            for (int yCnt = 1; yCnt <= 9; ++yCnt) {
                for (int i = 1; i <= n; ++i) {
                    for (int j = 1; j <= m; ++j) {
                        for (int dir = 0; dir < 8; ++dir) {
                            dfs(i, j, dir, xCnt, yCnt, "" + area[i][j]);
                        }
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static boolean range(int x, int y){
        return x < 1 || y < 1 || x > n || y > m;
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        area = new char[n + 1][m + 1];
        for (int i = 1; i <= n; ++i) {
            String tmp = br.readLine();
            for (int j = 1; j <= m; ++j) {
                area[i][j] = tmp.charAt(j - 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
