import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        int[][] graph = new int[N][N];
        long[][] dp = new long[N][N];
        dp[0][0] = 1;

        for(int y = 0; y < N; y++) {
            graph[y] = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
        }

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {

                if(graph[y][x] == 0 || dp[y][x] == 0) continue;

                int move = graph[y][x];
                
                if(x + move < N) dp[y][x + move] += dp[y][x]; // 오른쪽
                if(y + move < N) dp[y + move][x] += dp[y][x]; // 아래쪽

            }
        }

        System.out.println(dp[N-1][N-1]);

    }
}
