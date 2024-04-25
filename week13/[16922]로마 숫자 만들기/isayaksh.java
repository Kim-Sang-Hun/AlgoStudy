import java.util.*;
import java.io.*;

public class isayaksh {

    private static final int[] coins = {1, 5, 10, 50};

    private static int N;
    private static boolean[][] used;

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        used = new boolean[N+1][1001];

        int sum = dfs(0, 0);
        System.out.println(sum);
        
    }

    private static int dfs(int n, int sum) {
        int cnt = 0;

        if(n == N) return 1;
        
        for(int coin : coins) {
            if(used[n+1][sum+coin]) continue;
            used[n+1][sum+coin] = true;
            cnt += dfs(n+1, sum+coin);
        }

        return cnt;
    }

}
