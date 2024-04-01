import java.util.Scanner;
// knapsack 문제, bfs로 풀이
public class Solution {
    static int[][] ingredients;
    static int T, N, L, maxScore;

    public static void bfs(int idx, int calorie, int score) {
        if (idx == N) {
            maxScore = Math.max(maxScore, score);
            return;
        }
        // calorie가 여유가 되면 담고 가는 bfs
        if (ingredients[idx][1] <= calorie)
            bfs(idx + 1, calorie - ingredients[idx][1], score + ingredients[idx][0]);
        // 담지 않고 가는 bfs
        bfs(idx + 1, calorie, score);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        T = s.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            N = s.nextInt();
            L = s.nextInt();
            maxScore = 0;
            ingredients = new int[N][2];
            for (int i = 0; i < N; i++) {
                ingredients[i][0] = s.nextInt();
                ingredients[i][1] = s.nextInt();
            }
            bfs(0, L, 0);
            System.out.printf("#%d %d\n", tc, maxScore);
        }
    }
}
