import java.util.*;
import java.io.*;

public class isayaksh {

    private static final int MAX_VALUE = 1000*1000;

    private static int N;
    private static int[][] houses;
    private static int[] cost;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        houses = new int[N][3];
        cost = new int[3];
        for(int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            houses[n][0] = Integer.parseInt(st.nextToken());
            houses[n][1] = Integer.parseInt(st.nextToken());
            houses[n][2] = Integer.parseInt(st.nextToken());
        }

        // ==== logic ====
        int answer = MAX_VALUE;

        for(int i = 0; i < 3; i++) {
            Arrays.fill(cost, MAX_VALUE);

            cost[i] = houses[0][i];

            for(int j = 1; j < N; j++) {
                int c1 = houses[j][0] + Math.min(cost[1], cost[2]);
                int c2 = houses[j][1] + Math.min(cost[0], cost[2]);
                int c3 = houses[j][2] + Math.min(cost[0], cost[1]);
                cost[0] = c1;
                cost[1] = c2;
                cost[2] = c3;

            }

            for(int k = 0; k < 3; k++) {
                if(i == k) continue;
                if(cost[k] < answer) answer = cost[k];
            }

        }

        System.out.println(answer);

    }

}
