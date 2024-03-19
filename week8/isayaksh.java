import java.io.*;
import java.util.*;

public class isayaksh {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] numbers = new int[n][n];
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < i+1; j++) {
                numbers[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = numbers[0][0];

        // ======================= input end =======================
        
        Deque<Point> deque = new ArrayDeque<Point>();
        deque.add(new Point(0, 0));

        while(!deque.isEmpty()) {
            Point p = deque.poll();

            if(p.y == n-1) continue;

            int x = p.x;
            int y = p.y;

            if(dp[y+1][x] < dp[y][x] + numbers[y+1][x]) {
                dp[y+1][x] = dp[y][x] + numbers[y+1][x];
                deque.add(new Point(x, y+1));
            }

            if(dp[y+1][x+1] < dp[y][x] + numbers[y+1][x+1]) {
                dp[y+1][x+1] = dp[y][x] + numbers[y+1][x+1];
                deque.add(new Point(x+1, y+1));
            }

        }

        int answer = 0;
        for(int i = 0; i < n; i++) {
            if(answer < dp[n-1][i]) answer = dp[n-1][i];
        }

        System.out.println(answer);

    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
