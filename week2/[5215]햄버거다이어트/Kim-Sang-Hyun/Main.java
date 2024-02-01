import java.util.*;
import java.io.*;

public class Main {

    public static int[][] ingredients;
    public static int N;
    public static int L;
    public static int currentScore = 0;
    public static int answer = 0;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            ingredients = new int[N][2];
            currentScore = 0;
            answer = 0;
            
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ingredients[i][0] = Integer.parseInt(st.nextToken());
                ingredients[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0);
            System.out.println("#" + t + " " + answer);
        }

    }

    public static void dfs(int start) {
        int score, calorie;

        for(int i = start; i < N; i++) {
            score = ingredients[i][0];
            calorie = ingredients[i][1];

            if(L < calorie) continue;

            L -= calorie;
            currentScore += score;
            answer = Math.max(answer, currentScore);
            dfs(i + 1);
            L += calorie;
            currentScore -= score;

        }
        
    }
}
