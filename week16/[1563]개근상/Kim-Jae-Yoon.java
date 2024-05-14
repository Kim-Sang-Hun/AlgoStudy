import java.util.*;
import java.io.*;
/*
  Title: 개근상
  Tier: Gold 4
  Algorithm: Dynamic Programming
  Constraint: 2 Second, 128MB
*/
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n;
    static int[][][] dp;
    static final int mod = (int)1e6;

    static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        //n일 동안 3연속 결석, 2번 지각을 체크하기 위한 3차원 배열
        dp = new int[n + 1][4][3];
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 3; ++k) {
                    dp[i][j][k] = -1;
                }
            }
        }
    }
  
    //DP + DFS
    static int dfs(int idx, int absent, int late){
        //이미 연산된 케이스는 다시 반환해준다.
        if (dp[idx][absent][late] != -1) return dp[idx][absent][late];
        //총합 2번 지각 또는 3번 연속 지각 시 0 할당
        if (late == 2 || absent == 3) return 0;
        if(idx == n) return 1;
        dp[idx][absent][late] = 0;
        //O, L, A에 대한 경우를 전부 합해줘야 한다.
        return dp[idx][absent][late] = (
                    dfs(idx + 1, 0, late) +
                    dfs(idx + 1, 0, late + 1) +
                    dfs(idx + 1, absent + 1, late)
                ) % mod;
    }

    static void solution() {
        System.out.println(dfs(0, 0, 0));
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
    }
}
