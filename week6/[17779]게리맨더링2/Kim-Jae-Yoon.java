import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, answer = (int)1e9;
    static int[][] a, team;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        a = new int[n + 1][n + 1];
        team = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; ++j) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean isValidRange(int x, int y, int d1, int d2) {
        if(x + d2 > n || x + d1 > n) return false;
        if(y - d1 < 1 || y + d2 > n) return false;
        if(x + d1 + d2 > n || y + d2 - d1 < 1) return false;
        return true;
    }

    static void findAnswer() {
        int[] sum = new int[6];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                sum[team[i][j]] += a[i][j];
            }
        }
        Arrays.sort(sum);
        int diff = sum[5] - sum[1];
        if(diff < answer) answer = diff;
    }

    //이 수준으로 해도 시간초과 안 남
    //(20^4)해봤자 모든 연산 수행해도 1억은 안 넘으므로 무난
    static void search(int x, int y) {
        for(int d1 = 1;d1 < n; ++d1){
            for(int d2 = 1;d2 < n; ++d2){
                if (!isValidRange(x, y, d1, d2)) continue;
                reset();
                makeEachTeam(x, y, d1, d2);
                findAnswer();
            }
        }
    }

    static void reset(){
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                team[i][j] = 0;
            }
        }
    }

    static void makeEachTeam(int x, int y, int d1, int d2) {
        //5팀 + 경계선
        for (int i = 0; i <= d1; ++i) {
            team[x + i][y - i] = team[x + d2 + i][y + d2 - i] = 5;
        }
        for (int i = 0; i <= d2; ++i) {
            team[x + i][y + i] = team[x + d1 + i][y - d1 + i] = 5;
        }
        //1팀
        for (int i = 1; i < x + d1; ++i) {
            for (int j = 1; j <= y; ++j) {
                if(team[i][j] == 5) break;
                team[i][j] = 1;
            }
        }
        //2팀
        for (int i = 1; i <= x + d2; ++i) {
            for (int j = n; j > y; --j) {
                if(team[i][j] == 5) break;
                team[i][j] = 2;
            }
        }
        //3팀
        for (int i = x + d1; i <= n; ++i) {
            for (int j = 1; j < y - d1 + d2; ++j) {
                if(team[i][j] == 5) break;
                team[i][j] = 3;
            }
        }
        //4팀
        for (int i = x + d2 + 1; i <= n; ++i) {
            for (int j = n; j >= y - d1 + d2; --j) {
                if(team[i][j] == 5) break;
                team[i][j] = 4;
            }
        }
        //경계선 내 5팀
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if(team[i][j] == 0) team[i][j] = 5;
            }
        }
    }

    static void solution() {
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                search(i, j);
            }
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
